package sandeep.Food.Service.Food_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandeep.Food.Models.Category;
import sandeep.Food.Models.Food;
import sandeep.Food.Models.Restaurant;
import sandeep.Food.Repository.FoodRepository;
import sandeep.Food.Request.CreateFoodRequest;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceIMP implements FoodService{

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest foodRequest, Category category, Restaurant restaurant) {

        Food food = new Food();
        food.setFood_category(category);
        food.setRestaurant(restaurant);
        food.setDescription(foodRequest.getDescription());
        food.setImages(foodRequest.getImages());
        food.setName(foodRequest.getName());
        food.setPrice(foodRequest.getPrice());
        food.setIngrediantsItems(foodRequest.getIngrediantsItems());
        food.setVegetarian(foodRequest.isVegetarin());
        food.setCreation_date(new Date());
        food.setSeasonal(foodRequest.isSeasional());

        Food saveFood = foodRepository.save(food);
        restaurant.getFoods().add(saveFood);
        return saveFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);

    }

    @Override
    public List<Food> getRestaurentsFood(Long restaurentId,
                                         boolean isVegitarian,
                                         boolean isSeasonal,
                                         boolean isNonveg, String foodCategory) {

        List<Food> food = foodRepository.findByRestaurantId(restaurentId);

        if(isVegitarian) {
            food =  filterByVegitarian(food, isVegitarian);
        }
        if(isSeasonal) {
            food =  filterBySeasoonal(food, isSeasonal);
        }
        if(isNonveg) {
            food =  filterByNonveg(food, isNonveg);
        }
        if (foodCategory!=null && !foodCategory.equals(""))food = filterByCategory(food,foodCategory);
        
        return food;
    }

    private List<Food> filterByNonveg(List<Food> food, boolean isNonveg) {
        return food.stream().filter(foods -> foods.isVegetarian()==false).collect(Collectors.toList());
    }

    private List<Food> filterBySeasoonal(List<Food> food, boolean isSeasonal) {
        return food.stream().filter(foods -> foods.isVegetarian()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByCategory(List<Food> food, String foodCategory) {
        return food.stream().filter(foods -> {
            if(foods.getFood_category() != null){
                return foods.getFood_category().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());

    }



    private List<Food> filterByVegitarian(List<Food> food, boolean isVegitarian) {
        return food.stream().filter(foods -> foods.isVegetarian()==isVegitarian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String Keyword) {
        return foodRepository.searchFood(Keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalfood = foodRepository.findById(foodId);

        if (optionalfood.isEmpty())throw new Exception("Food not Exists");

    return optionalfood.get();
    }

    @Override
    public Food updateAvaliabilityStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvaliable(!food.isAvaliable());
        return foodRepository.save(food);
    }
}
