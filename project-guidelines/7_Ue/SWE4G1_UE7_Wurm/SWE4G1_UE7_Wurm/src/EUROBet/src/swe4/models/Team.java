package swe4.models;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Team implements Externalizable {
	
	private IntegerProperty id;
	private StringProperty name;
	private StringProperty group;
	
	
	public Team() {
		this.id = new SimpleIntegerProperty(-1);
		this.name = new SimpleStringProperty("");
		this.group = new SimpleStringProperty("");
	}
	
	public Team(Integer id, String name, String group) {
		this();
		
		this.id.set(id);
		this.name.set(name);
		this.group.set(group);
	}
	
	
	public void clone(Team team) {
		if(team != null) {
			this.setId(team.getId());
			this.setName(team.getName());
			this.setGroup(team.getGroup());
		} else {
			this.setId(-1);
			this.setName("");
			this.setGroup("");
		}
	}
	
	
	
	public IntegerProperty getIdProperty() {
		return id;
	}
	
	public final Integer getId() {
		return id.get();
	}
	
	public final void setId(Integer id) {
		this.id.set(id);
	}
	
	
	
	public StringProperty getNameProperty() {
		return name;
	}
	
    public final String getName() {
        return name.get();
    }

    public final void setName(String name) {
        this.name.set(name);
    }
    
    
    
    public StringProperty getGroupProperty() {
		return group;
	}
	
    public final String getGroup() {
        return group.get();
    }

    public final void setGroup(String group) {
        this.group.set(group);
    }
    
    
    @Override
	public String toString() {
		
		return "id:" + id.getValue() +
				" name: " + name.getValue() + 
				" group: " + group.getValue();
	}
	
	

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(getId());
		out.writeObject(getName());
		out.writeObject(getGroup());
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readInt());
		setName((String) in.readObject());
		setGroup((String) in.readObject());
	}
}
