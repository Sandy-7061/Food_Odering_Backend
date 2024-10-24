package sandeep.Food.Service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandeep.Food.Models.*;
import sandeep.Food.Repository.AddressRepository;
import sandeep.Food.Repository.OrderItemRepository;
import sandeep.Food.Repository.OrderRepository;
import sandeep.Food.Repository.UserRepository;
import sandeep.Food.Request.OrderRequest;
import sandeep.Food.Service.CartService.CartService;
import sandeep.Food.Service.Restaurant.RestaurantService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceIMP implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
        Address shippingAddress = order.getDeliveryAddress();

        Address savedAddress  = addressRepository.save(shippingAddress);

        if (!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }

        Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());

        Order createOrder = new Order();
        createOrder.setCustomer(user);
        createOrder.setCreated_at(new Date());
        createOrder.setOrder_status("PENDING");
        createOrder.setDelivery_address(savedAddress);
        createOrder.setRestaurant(restaurant);

        Cart cart = cartService.findCartById(user.getUserid());

        List<OrderItems> orderItems = new ArrayList<>();

        for (Cart_Item cartItem : cart.getItems()){
            OrderItems orderItem = new OrderItems();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngrediants());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotal_price(cartItem.getTotalPrice());

            OrderItems savedOrder = orderItemRepository.save(orderItem);
            orderItems.add(savedOrder);
        }

        Long totalprice = cartService.calculateCartTotals(cart);
        createOrder.setOrder_Items(orderItems);
        createOrder.setTotal_price(totalprice);

        Order savedorder = orderRepository.save(createOrder);
        restaurant.getOrders().add(savedorder);
        return createOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {

        Order order = findOrderById(orderId);
        if (orderStatus.equals("OUT_FOR_DELIVERY")
            || orderStatus.equals("PENDING")
                ||orderStatus.equals("DELIVERED")
                ||orderStatus.equals("COMPLETED")
        ){
            order.setOrder_status(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("Please Select valid Order Status");
    }

    @Override
    public void cancleORder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUserOrder(Long orderId) throws Exception {
        return orderRepository.findByCustomerUserid(orderId);
    }

    @Override
    public List<Order> getRestaurentsOrder(Long restaurentId, String orderStatus) throws Exception {
        List<Order> orders = orderRepository.findByRestaurantId(restaurentId);

        if (orderStatus!= null){
            orders= orders.stream().filter(order -> order.getOrder_status().equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optional = orderRepository.findById(orderId);
        if (optional.isEmpty())throw new Exception("Order Not Found ! ");
        return optional.get();
    }
}
