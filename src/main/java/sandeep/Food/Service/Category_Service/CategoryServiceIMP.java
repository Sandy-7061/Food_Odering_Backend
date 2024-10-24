package sandeep.Food.Service.Category_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandeep.Food.Models.Category;
import sandeep.Food.Models.Restaurant;
import sandeep.Food.Repository.CategoryRepository;
import sandeep.Food.Service.Restaurant.RestaurantService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceIMP implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RestaurantService restaurantService;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);

        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())throw new Exception("Category Not Found");
        return optionalCategory.get();
    }
}
