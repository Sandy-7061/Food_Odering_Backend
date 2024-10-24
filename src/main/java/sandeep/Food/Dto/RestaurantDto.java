package sandeep.Food.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RestaurantDto {

    private String title;
    private String description;

    @Column(length = 1000)
    private List<String> images;

    private long restaurantDto_id;
}
