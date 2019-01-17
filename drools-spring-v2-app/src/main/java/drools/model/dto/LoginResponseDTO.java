package drools.model.dto;


import drools.model.User;

public class LoginResponseDTO
{
    private String token;
    private Integer id;
    private String username;
    private String role;

    public LoginResponseDTO() {
		super();
	}

	public LoginResponseDTO(String token, Integer id, String username, String role) {
		super();
		this.token = token;
		this.id = id;
		this.username = username;
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LoginResponseDTO(User user)
    {
        this.token = user.getPassword();
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getUserAuthority().toString();
    }
}
