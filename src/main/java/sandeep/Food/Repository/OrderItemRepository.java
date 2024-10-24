package sandeep.Food.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sandeep.Food.Models.OrderItems;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems,Long> {
}
