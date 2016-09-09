package swe4.models;

public class Team {
	
	private String name;
	private Character group;
	
	
	public Team() {
		
	}
	
	public Team(String name, Character group) {
		this.setName(name);
		this.setGroup(group);
	}

	public Character getGroup() {
		return group;
	}

	public void setGroup(Character group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
