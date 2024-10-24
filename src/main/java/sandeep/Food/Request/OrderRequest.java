package sandeep.Food.Request;

import lombok.Data;
import sandeep.Food.Models.Address;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;
}
