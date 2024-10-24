package sandeep.Food.Service.Food_Service;
import org.springframework.stereotype.Service;
import sandeep.Food.Models.Category;
import sandeep.Food.Models.Food;
import sandeep.Food.Models.Restaurant;
import sandeep.Food.Request.CreateFoodRequest;
import java.util.List;

@Service
public interface FoodService {

    public Food createFood(CreateFoodRequest foodRequest, Category category, Restaurant restaurant);

    public void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurentsFood(Long restaurentId,
                                         boolean isVegitarian,
                                         boolean isSeasonal,
                                         boolean isNonveg,
                                         String foodCategory

    );

    public List<Food> searchFood(String Keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvaliabilityStatus(Long foodId) throws Exception;


}
