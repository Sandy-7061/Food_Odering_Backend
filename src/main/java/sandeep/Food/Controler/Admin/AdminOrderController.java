package sandeep.Food.Controler.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sandeep.Food.Models.Order;
import sandeep.Food.Models.User;
import sandeep.Food.Request.OrderRequest;
import sandeep.Food.Service.OrderService.OrderService;
import sandeep.Food.Service.UserSevice;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
    @Autowired
    private UserSevice userSevice;

    @Autowired
    private OrderService orderService;


    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getorder(
            @RequestParam Long id,
            @RequestParam(required = false) String order_status,
            @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userSevice.findUserByJwtToken(jwt);
        List<Order>  orders = orderService.getRestaurentsOrder(id,order_status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}/{orderstatus}")
    public ResponseEntity<Order> updateOrder(
            @RequestParam Long orderId,
            @RequestParam String orderstatus,
            @RequestHeader("Authorization")String jwt) throws Exception{
        Order  orders = orderService.updateOrder(orderId,orderstatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
