package sandeep.Food.Response;

import lombok.Data;
import sandeep.Food.Models.USER_ROLE;
@Data
public class AuthResponse {
    private String Jwt;

    private String message;

    private USER_ROLE role;
}
