package sandeep.Food.Service.IngredientsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandeep.Food.Models.IngrediantsCategory;
import sandeep.Food.Models.IngrediantsItem;
import sandeep.Food.Models.Restaurant;
import sandeep.Food.Repository.IngredientCategoryRepository;
import sandeep.Food.Repository.IngredientItemRepository;
import sandeep.Food.Service.Restaurant.RestaurantService;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceIMP implements IngredientsService {
    @Autowired
    private IngredientItemRepository ingredientItemRepository;
    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;


    @Override
    public IngrediantsCategory createIngrediantsCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngrediantsCategory ingrediantsCategory = new IngrediantsCategory();
        ingrediantsCategory.setName(name);
        ingrediantsCategory.setRestaurant(restaurant);
        return ingredientCategoryRepository.save(ingrediantsCategory);

    }

    @Override
    public IngrediantsCategory findIngrediantsCategoryById(Long id) throws Exception {

        Optional<IngrediantsCategory> optional = ingredientCategoryRepository.findById(id);
        if (optional.isEmpty())throw new Exception("Ingrediant Category Not Founnd !");

        return optional.get();
    }

    @Override
    public List<IngrediantsCategory> findIngrediantsCategoryByRestaurantId(Long id) throws Exception {
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngrediantsItem createIngrediantsItem(Long resataurantId, String ingrediantName, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(resataurantId);
        IngrediantsCategory ingrediantsCategory = findIngrediantsCategoryById(categoryId);

        IngrediantsItem item = new IngrediantsItem();
        item.setName(ingrediantName);
        item.setRestaurant(restaurant);
        item.setCategory(ingrediantsCategory);

        IngrediantsItem ingrediantsItem = ingredientItemRepository.save(item);
        ingrediantsCategory.getIngrediantsItems().add(ingrediantsItem);
    return ingrediantsItem;
    }

    @Override
    public List<IngrediantsItem> findRestaurantsIngredients(Long restaurantId) throws Exception {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngrediantsItem updateStock(Long id) throws Exception {

        Optional<IngrediantsItem> optional = ingredientItemRepository.findById(id);
        if (optional.isEmpty())throw new Exception("Ingredient Not Found");

        IngrediantsItem item = optional.get();
        item.setInStock(!item.isInStock());
        return ingredientItemRepository.save(item);
    }
}