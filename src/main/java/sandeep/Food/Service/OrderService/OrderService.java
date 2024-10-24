package sandeep.Food.Service.OrderService;

import sandeep.Food.Models.Order;
import sandeep.Food.Models.User;
import sandeep.Food.Request.OrderRequest;

import java.util.List;

public interface OrderService {
    public Order createOrder(OrderRequest order, User user) throws Exception;

    public Order updateOrder(Long orderId,String orderStatus) throws Exception;

    public void cancleORder(Long orderId) throws Exception;

    public List<Order> getUserOrder(Long orderId) throws Exception;

    public List<Order> getRestaurentsOrder(Long restaurentId,String orderStatus) throws Exception;

    public Order findOrderById(Long orderId) throws Exception;

}
