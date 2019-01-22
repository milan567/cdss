package drools.service.implementation;

import drools.model.Authority;
import drools.model.User;
import drools.model.dto.LoginRequestDTO;
import drools.repository.UserRepository;
import drools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import drools.service.UserService;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public User save(User user) {
        this.userRepository.save(user);
        return user;
    }



    @Override
    public User getUserById(Integer userId)
    {
        User user = this.userRepository.getOne(userId);
        return user;
    }

    @Override
    public User getUserByUsername(String username)
    {
        User user = null;
        user = this.userRepository.findByUsername(username);
        return user;
    }

    @Override
    public User validateUser(LoginRequestDTO user) {
        User u = null;
        u = this.userRepository.findByUsername(user.getUsername());
        System.out.println(u);
        if (user.getPassword().equals(u.getPassword())){
            return  u;
        }
        return null;
    }

    @Override
    public void deleteAll() {
        this.userRepository.deleteAll();
    }

    @Override
    public User findDoctorByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllDoctors() {

        return userRepository.findByUserAuthority(Authority.DOCTOR);
    }

    @Override
    public List<User> deleteDoctor(Integer id) {
        userRepository.deleteById(id);
        return userRepository.findByUserAuthority(Authority.DOCTOR);
    }

    @Override
    public User editDoctor(User doctor) {
        User u = userRepository.getOne(doctor.getId());
        if (u == null){
            return null;
        }
        u.setUsername(doctor.getUsername());
        u.setDoctorName(doctor.getDoctorName());
        u.setSpecialist(doctor.getSpecialist());
        u.setSurname(doctor.getSurname());
        return userRepository.save(u);
    }

}
