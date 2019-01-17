package drools.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import drools.model.dto.RegisterUserDTO;


@Entity
@Table(name = "admin1")
public class Admin extends User {

    public Admin(User user)
    {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setUserAuthority(user.getUserAuthority());
    }

    public Admin(RegisterUserDTO registerUserDTO)
    {
        this.setUsername(registerUserDTO.getUsername());
        this.setPassword(registerUserDTO.getPassword());
    }
}
