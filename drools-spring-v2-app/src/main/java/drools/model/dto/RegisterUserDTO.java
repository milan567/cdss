package drools.model.dto;


public class RegisterUserDTO
{
	private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String specialist;
    
    
    
    
	public RegisterUserDTO() {
		super();
	}
	public RegisterUserDTO(String username, String password, String firstname, String lastname,
                           String specialist) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.specialist = specialist;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSpecialist() {
		return specialist;
	}

	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}
}
