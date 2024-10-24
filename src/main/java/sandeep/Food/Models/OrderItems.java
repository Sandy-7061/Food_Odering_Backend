package sandeep.Food.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long order_items_id;

    @ManyToOne
    private Food food;

    private int quantity;

    private long total_price;

    private List<String> ingredients;
}
