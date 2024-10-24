package sandeep.Food.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cart_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cart_item_id;

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    private Food food;

    private int quantity;

    private List<String> ingrediants = new ArrayList<>();

    private Long totalPrice;

}
