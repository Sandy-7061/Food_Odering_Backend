package sandeep.Food.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sandeep.Food.Models.Restaurant;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE lower(concat('%', :query , '%')) " +
            "OR lower(r.cuisine_type) LIKE lower(concat('%', :query , '%'))")
    List<Restaurant> findBySearchQuery(String query);

    Optional<Restaurant> findByOwner_Userid(Long ownerId);  // Assuming owner is a User object
}
