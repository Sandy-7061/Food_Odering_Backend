package sandeep.Food.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngrediantsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ingrediants_item_id;

    private String name;

    @ManyToOne
    @JsonBackReference  // Prevents recursion by ignoring this field during serialization
    private IngrediantsCategory category;

    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    private boolean inStock;
}
