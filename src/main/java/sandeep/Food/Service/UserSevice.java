package sandeep.Food.Service;

import sandeep.Food.Models.User;

public interface UserSevice {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;
}
