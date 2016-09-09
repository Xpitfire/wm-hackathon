package wm.lib.model;

import java.io.Serializable;

public class Team implements Serializable {
	private String country;
	private String name;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
