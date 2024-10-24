package sandeep.Food.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sandeep.Food.Models.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    public List<Order> findByCustomerUserid(Long userid);
    public List<Order> findByRestaurantId(Long restaurentId);
}
