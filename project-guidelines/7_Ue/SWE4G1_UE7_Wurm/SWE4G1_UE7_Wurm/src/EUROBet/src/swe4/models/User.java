package swe4.models;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User implements Externalizable {


	private IntegerProperty id;
	private BooleanProperty active;
	private StringProperty name;
	private StringProperty password;


	public User() {
		this.id = new SimpleIntegerProperty(-1);
		this.active = new SimpleBooleanProperty(false);
		this.name = new SimpleStringProperty("");
		this.password = new SimpleStringProperty("");
	}

	public User(Integer id, Boolean active, String name, String password) {
		this();

		this.id.set(id);
		this.active.set(active);
		this.name.set(name);
		this.password.set(password);
	}


	public void clone(User user) {
		if(user != null) {
			this.setId(user.getId());
			this.setActive(user.getActive());
			this.setName(user.getName());
			this.setPassword(user.getPassword());
		} else {
			this.setId(-1);
			this.setActive(false);
			this.setName("");
			this.setPassword("");
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



	public BooleanProperty getActiveProperty() {
		return active;
	}

	public final Boolean getActive() {
		return active.get();
	}

	public final void setActive(Boolean active) {
		this.active.set(active);
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




    public StringProperty getPasswordProperty() {
		return password;
	}

    public final String getPassword() {
        return password.get();
    }

    public final void setPassword(String password) {
        this.password.set(password);
    }


	@Override
	public String toString() {

		return "id:" + id.getValue() +
				" name: " + name.getValue() +
				" password: " + password.getValue() +
				" active: " + active.getValue();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(getId());
		out.writeBoolean(getActive());
		out.writeObject(getName());
		out.writeObject(getPassword());
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setId(in.readInt());
		setActive(in.readBoolean());
		setName((String) in.readObject());
		setPassword((String) in.readObject());
	}
}
