package sandeep.Food.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sandeep.Food.Models.User;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String username);
}
