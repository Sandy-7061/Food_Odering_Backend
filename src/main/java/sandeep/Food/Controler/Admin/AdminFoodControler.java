package sandeep.Food.Controler.Admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sandeep.Food.Models.Food;
import sandeep.Food.Models.Restaurant;
import sandeep.Food.Models.User;
import sandeep.Food.Request.CreateFoodRequest;
import sandeep.Food.Response.MessageResponse;
import sandeep.Food.Service.Food_Service.FoodService;
import sandeep.Food.Service.Restaurant.RestaurantService;
import sandeep.Food.Service.UserSevice;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodControler {

    private static final Logger logger = LoggerFactory.getLogger(AdminFoodControler.class);

    @Autowired
    private FoodService foodService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserSevice userSevice;

    @PostMapping()
    public ResponseEntity<String> createFood(@RequestBody CreateFoodRequest createFoodRequest, Authentication authentication) {
        String email = authentication.getName(); // Get authenticated user's email
        Long restaurantId = createFoodRequest.getRestaurentId(); // Assuming you are sending restaurantId in the request

        // Check for valid restaurant ID
        if (restaurantId == null || restaurantId.equals("null")) {
            return ResponseEntity.badRequest().body("Restaurant ID cannot be null or empty.");
        }

        // Fetch user details
        User user;
        try {
            user = userSevice.findUserByEmail(email);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated.");
        }

        // Fetch restaurant associated with user
        Restaurant restaurant;
        try {
            restaurant = restaurantService.getRestaurantByUserId(user.getUserid());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant Not Found For this Owner Id: " + user.getUserid());
        }

        // Create Food item
        Food food = new Food();
        food.setName(createFoodRequest.getName());
        food.setDescription(createFoodRequest.getDescription());
        food.setPrice(createFoodRequest.getPrice());
        food.setRestaurant(restaurant); // Associate food item with the restaurant

        // Save the food item
        foodService.createFood(createFoodRequest, createFoodRequest.getCategory(), restaurant); // Make sure to pass category if needed

        return ResponseEntity.ok("Food item created successfully.");
    }


    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {
        logger.info("Received deleteFood request for foodId: {}", id);

        User user = null;

        try {
            user = userSevice.findUserByJwtToken(jwt);
            logger.info("User authenticated: {}", user.getUserid());

            foodService.deleteFood(id);
            logger.info("Food deleted successfully for id: {}", id);

        } catch (Exception e) {
            logger.error("Error deleting food for user: {}, foodId: {}, error: {}", user != null ? user.getUserid() : "unknown", id, e.getMessage());
            throw new Exception("Error deleting food: " + e.getMessage());
        }

        MessageResponse message = new MessageResponse();
        message.setMessage("Food Deleted Successfully");
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id,
                                                             @RequestHeader("Authorization") String jwt) throws Exception {
        logger.info("Received updateFoodAvailabilityStatus request for foodId: {}", id);

        Food food = null;

        try {
            food = foodService.updateAvaliabilityStatus(id);
            logger.info("Food availability status updated for id: {}", id);

        } catch (Exception e) {
            logger.error("Error updating availability for foodId: {}, error: {}", id, e.getMessage());
            throw new Exception("Error updating food availability: " + e.getMessage());
        }

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
}
