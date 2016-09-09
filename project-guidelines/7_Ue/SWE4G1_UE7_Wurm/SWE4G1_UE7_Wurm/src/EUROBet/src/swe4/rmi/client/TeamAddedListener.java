package swe4.rmi.client;

import swe4.models.Team;

public interface TeamAddedListener {
	public void onTeamAdded(Team team);
}
