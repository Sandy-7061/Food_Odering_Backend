package sandeep.Food.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngrediantsCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ingrediants_category_id;

    private String name;

    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    @JsonManagedReference  // Manage the serialization of the child items
    private List<IngrediantsItem> ingrediantsItems = new ArrayList<>();
}

