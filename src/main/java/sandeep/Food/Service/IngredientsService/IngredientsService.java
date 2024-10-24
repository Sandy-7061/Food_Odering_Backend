package sandeep.Food.Service.IngredientsService;

import sandeep.Food.Models.IngrediantsCategory;
import sandeep.Food.Models.IngrediantsItem;

import java.util.List;

public interface IngredientsService {
    public IngrediantsCategory createIngrediantsCategory(String name,Long restaurantId) throws Exception;

    public IngrediantsCategory findIngrediantsCategoryById(Long id) throws Exception;


    public List<IngrediantsCategory> findIngrediantsCategoryByRestaurantId(Long id) throws  Exception;

    public IngrediantsItem createIngrediantsItem(Long resataurantId,String ingrediantName,Long categoryId) throws Exception;

    public List<IngrediantsItem> findRestaurantsIngredients(Long restaurantId) throws Exception;

    public IngrediantsItem updateStock(Long id) throws Exception;
}
