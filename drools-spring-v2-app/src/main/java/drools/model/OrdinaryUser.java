package drools.model;


import drools.model.dto.RegisterUserDTO;

import javax.persistence.*;


@Entity
@Table(name = "ordinary_user")
public class OrdinaryUser extends User {

    public OrdinaryUser(User user)
    {
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setUserAuthority(user.getUserAuthority());
    }

    public OrdinaryUser(RegisterUserDTO registerUserDTO)
    {
        this.setUsername(registerUserDTO.getUsername());
        this.setPassword(registerUserDTO.getPassword());
    }
    
    
}
