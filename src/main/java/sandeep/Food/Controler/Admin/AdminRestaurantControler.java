package sandeep.Food.Controler.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sandeep.Food.Models.Restaurant;
import sandeep.Food.Models.User;
import sandeep.Food.Request.CreateRestaurantRequest;
import sandeep.Food.Response.MessageResponse;
import sandeep.Food.Service.Restaurant.RestaurantService;
import sandeep.Food.Service.UserSevice;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantControler {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserSevice userSevice;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody CreateRestaurantRequest request,
            @RequestHeader("Authorization") String jwt
            ) throws Exception{

        User user = userSevice.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.createRestaurant(request,user);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @RequestBody CreateRestaurantRequest request,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception{
        Restaurant restaurant = restaurantService.updateRestaurant(id,request);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception{
        restaurantService.deleteRestaurant(id);
        MessageResponse message = new MessageResponse();
        message.setMessage("Restaurant Deleted");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(
            @PathVariable Long id
    ) throws Exception{
        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> findrestaurantByUserId(
            @RequestHeader("Authorization") String jwt
    ) throws Exception{

        User user = userSevice.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getUserid());

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

}
