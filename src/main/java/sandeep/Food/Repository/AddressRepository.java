package sandeep.Food.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sandeep.Food.Models.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
