package drools.service;




import drools.model.User;
import drools.model.dto.LoginRequestDTO;


public interface UserService {
    User save(User user);


    User getUserById(Integer userId);

    User getUserByUsername(String username);

    User validateUser(LoginRequestDTO user);

    void deleteAll();
}
