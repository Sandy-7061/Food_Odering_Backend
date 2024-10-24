package sandeep.Food.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sandeep.Food.Models.Cart_Item;
import sandeep.Food.Models.Order;
import sandeep.Food.Models.User;
import sandeep.Food.Request.AddCartItemRequest;
import sandeep.Food.Request.OrderRequest;
import sandeep.Food.Service.OrderService.OrderService;
import sandeep.Food.Service.UserSevice;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private UserSevice userSevice;

    @Autowired
    private OrderService orderService;

    @PutMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request,
                                             @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userSevice.findUserByJwtToken(jwt);
        Order  order = orderService.createOrder(request,user);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(
            @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userSevice.findUserByJwtToken(jwt);
        List<Order>  orders = orderService.getUserOrder(user.getUserid());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
