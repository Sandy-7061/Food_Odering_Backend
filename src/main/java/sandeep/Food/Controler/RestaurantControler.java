package sandeep.Food.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sandeep.Food.Dto.RestaurantDto;
import sandeep.Food.Models.Restaurant;
import sandeep.Food.Models.User;
import sandeep.Food.Service.Restaurant.RestaurantService;
import sandeep.Food.Service.UserSevice;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantControler {

    @Autowired
    private UserSevice userSevice;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurent(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword
    ) throws Exception{

        User user = userSevice.findUserByJwtToken(jwt);

        List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getallRestaurant(
            @RequestHeader("Authorization") String jwt
    ) throws Exception{

        User user = userSevice.findUserByJwtToken(jwt);

        List<Restaurant> restaurant = restaurantService.getallRestaurant();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findrestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception{

        User user = userSevice.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurantDto> addtoFavorites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception{

        User user = userSevice.findUserByJwtToken(jwt);

        RestaurantDto restaurant = restaurantService.addToFavorites(id,user);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

}
