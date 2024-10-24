package sandeep.Food.Request;

import lombok.Data;
import sandeep.Food.Models.Category;
import sandeep.Food.Models.IngrediantsItem;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;

    private Category category;
    private List<String> images;

    private Long restaurentId;
    private boolean vegetarin;
    private boolean seasional;

    private List<IngrediantsItem> ingrediantsItems;
}
