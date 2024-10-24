package sandeep.Food.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sandeep.Food.Models.Food;
import sandeep.Food.Models.Restaurant;
import sandeep.Food.Models.User;
import sandeep.Food.Request.CreateFoodRequest;
import sandeep.Food.Service.Food_Service.FoodService;
import sandeep.Food.Service.Restaurant.RestaurantService;
import sandeep.Food.Service.UserSevice;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodControler {
    @Autowired
    private FoodService foodService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserSevice userSevice;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                                @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userSevice.findUserByJwtToken(jwt);
        List<Food> foods = foodService.searchFood(name);

        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam boolean vegetarian,
                                                 @RequestParam boolean seasonal,
                                                 @RequestParam boolean nonveg,
                                                 @RequestParam(required = false) String food_category,
                                                 @RequestParam Long restaurantId,
                                                 @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userSevice.findUserByJwtToken(jwt);
        List<Food> foods = foodService.getRestaurentsFood(restaurantId,vegetarian,seasonal,nonveg,food_category);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
