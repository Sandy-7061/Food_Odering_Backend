package sandeep.Food.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandeep.Food.Config.JwtProvider;
import sandeep.Food.Models.User;
import sandeep.Food.Repository.UserRepository;

@Service
public class UserServiceIMP implements UserSevice{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {

        String email = jwtProvider.getEmailJwtToken(jwt);
        User user = findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);

        if (user == null)throw new Exception("User Not Found");
        return user;
    }
}
