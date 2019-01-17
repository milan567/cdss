package drools.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" , unique = true, nullable = false)
    private Integer id;

    @Column(name = "username")
    private String username;

    private String password;

    private String doctorName;

    private String surname;

    private String specialist;

    @Column(name= "authority")
    private Authority userAuthority;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public User() {

    }

    public User(String username, String password, String doctorName, String surname,
                String specialist, Authority userAuthority) {
        this.username = username;
        this.password = password;
        this.doctorName = doctorName;
        this.surname = surname;
        this.specialist = specialist;
        this.userAuthority = userAuthority;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Authority getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(Authority userAuthority) {
        this.userAuthority = userAuthority;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() { return this.id; }

    public void setId(Integer id) { this.id = id; }

}
