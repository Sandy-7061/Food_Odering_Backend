package sandeep.Food.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sandeep.Food.Models.IngrediantsItem;

import java.util.List;

@Repository
public interface IngredientItemRepository extends JpaRepository<IngrediantsItem,Long> {
    public List<IngrediantsItem> findByRestaurantId(Long id);

}
