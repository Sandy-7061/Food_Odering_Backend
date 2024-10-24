package sandeep.Food.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sandeep.Food.Models.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    public List<Category> findByRestaurantId(Long id);

}
