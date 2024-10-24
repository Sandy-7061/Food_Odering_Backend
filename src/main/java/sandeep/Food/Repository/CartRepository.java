package sandeep.Food.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sandeep.Food.Models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    public Cart findByCustomerUserid(Long userId);
}
