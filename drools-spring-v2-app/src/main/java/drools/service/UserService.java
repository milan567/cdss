package drools.service;




import drools.model.User;
import drools.model.dto.LoginRequestDTO;

import java.util.List;


public interface UserService {
    User save(User user);


    User getUserById(Integer userId);

    User getUserByUsername(String username);

    User validateUser(LoginRequestDTO user);

    void deleteAll();

    User findDoctorByUsername(String username);

    List<User> getAllDoctors();

    List<User> deleteDoctor(Integer id);

    User editDoctor(User doctor);
}
