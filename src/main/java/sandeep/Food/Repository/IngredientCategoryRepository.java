package sandeep.Food.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sandeep.Food.Models.IngrediantsCategory;

import java.util.List;

@Repository
public interface IngredientCategoryRepository extends JpaRepository<IngrediantsCategory,Long> {

    public List<IngrediantsCategory> findByRestaurantId(Long id);

}
