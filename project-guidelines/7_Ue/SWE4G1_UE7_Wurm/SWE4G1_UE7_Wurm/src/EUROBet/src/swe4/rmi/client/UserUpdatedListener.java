package swe4.rmi.client;

import swe4.models.User;

public interface UserUpdatedListener {
	public void onUserUpdated(User user);

}
