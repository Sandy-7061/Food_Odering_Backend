package sandeep.Food.Request;

import lombok.Data;
import sandeep.Food.Models.Address;
import sandeep.Food.Models.ContactInformation;

import java.util.List;

@Data
public class CreateRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private List<String> images;
    private ContactInformation contactInformation;
    private String openingHours;
}
