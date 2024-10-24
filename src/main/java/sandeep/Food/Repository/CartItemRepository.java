package sandeep.Food.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sandeep.Food.Models.Cart_Item;

@Repository
public interface CartItemRepository extends JpaRepository<Cart_Item,Long> {
}
