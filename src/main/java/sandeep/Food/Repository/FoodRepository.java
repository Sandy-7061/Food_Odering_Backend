package sandeep.Food.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sandeep.Food.Models.Food;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    // Correct method for retrieving Food by restaurant ID
    List<Food> findByRestaurantId(Long restaurantId);

    // Additional search method
    @Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR f.food_category.name LIKE %:keyword%")
    List<Food> searchFood(@Param("keyword") String keyword);
}
