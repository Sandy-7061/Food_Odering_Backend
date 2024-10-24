package sandeep.Food.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long food_id;

    private String name;
    private String description;
    private Long price;

    @ManyToOne
    @JoinColumn(name = "food_category_category_id") // Ensure this matches your schema
    private Category food_category;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

    @ManyToOne
    @JoinColumn(name = "restaurant_id") // Ensure this matches your schema
    private Restaurant restaurant;

    private boolean isVegetarian;
    private boolean isSeasonal;
    private boolean isAvaliable;

    @ManyToMany
    private List<IngrediantsItem> ingrediantsItems = new ArrayList<>();

    private Date creation_date;
}
