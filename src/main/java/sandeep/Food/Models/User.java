package sandeep.Food.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sandeep.Food.Dto.RestaurantDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userid;

    private String fullname;
    private String  email ;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private USER_ROLE role;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    public List<Order> orders = new ArrayList<>();

    @ElementCollection
    private List<RestaurantDto> favourite = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();
}
