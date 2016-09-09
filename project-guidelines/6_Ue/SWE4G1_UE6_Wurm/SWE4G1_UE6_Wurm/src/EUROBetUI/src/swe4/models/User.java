package swe4.models;

public class User {
	
	private Boolean active;
	private String name;
	private String password;
	
	
	public User() {
		
	}
	
	public User(Boolean active, String name, String password) {
		this.active = active;
		this.name = name;
		this.password = password;
	}
	
	
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
