package sandeep.Food.Service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sandeep.Food.Models.Cart;
import sandeep.Food.Models.Cart_Item;
import sandeep.Food.Models.Food;
import sandeep.Food.Models.User;
import sandeep.Food.Repository.CartItemRepository;
import sandeep.Food.Repository.CartRepository;
import sandeep.Food.Repository.FoodRepository;
import sandeep.Food.Request.AddCartItemRequest;
import sandeep.Food.Service.Food_Service.FoodService;
import sandeep.Food.Service.UserSevice;

import java.util.Optional;


@Service
public class CartServiceIMP implements CartService{
    @Autowired
    private UserSevice userSevice;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodService foodService;

    @Override
    public Cart_Item addItemToCart(AddCartItemRequest request, String jwt) throws Exception {

        User user = userSevice.findUserByJwtToken(jwt);

        Food food = foodService.findFoodById(request.getFoodId());
        Cart cart = cartRepository.findByCustomerUserid(user.getUserid());

        for(Cart_Item cartItem : cart.getItems()){
            if (cartItem.getFood().equals(food)){
                int newquantity = cartItem.getQuantity()+ request.getQuantity();
                return updateCartItemQuantity(cartItem.getCart_item_id(),newquantity);
            }
        }

        Cart_Item new_cart_item = new Cart_Item();
        new_cart_item.setCart(cart);
        new_cart_item.setFood(food);
        new_cart_item.setQuantity(request.getQuantity());
        new_cart_item.setIngrediants(request.getIngredients());
        new_cart_item.setTotalPrice(request.getQuantity() * food.getPrice());

        Cart_Item saveCartItem = cartItemRepository.save(new_cart_item);

        cart.getItems().add(saveCartItem);

        return saveCartItem;
    }

    @Override
    public Cart_Item updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<Cart_Item> optional = cartItemRepository.findById(cartItemId);
        if (optional.isEmpty()) throw new Exception("Cart Item Not Found");

        Cart_Item item = optional.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getFood().getPrice() * quantity);
        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {

        User user = userSevice.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerUserid(user.getUserid());

        Optional<Cart_Item> optional = cartItemRepository.findById(cartItemId);
        if (optional.isEmpty()) throw new Exception("Cart Item Not Found");

        Cart_Item item = optional.get();

        cart.getItems().remove(item);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {

        Long total = 0L;

        for (Cart_Item cartItem : cart.getItems()){
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {

        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isEmpty())throw new Exception("Cart Not Found With Id :  " + id);


        return cartOptional.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {

        Cart cart = cartRepository.findByCustomerUserid(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {

        Cart cart = findCartByUserId(userId);

        cart.getItems().clear();
        return cartRepository.save(cart);

    }
}
