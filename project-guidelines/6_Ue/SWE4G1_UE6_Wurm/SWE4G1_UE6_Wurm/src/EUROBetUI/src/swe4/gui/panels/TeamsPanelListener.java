package swe4.gui.panels;

import swe4.models.Team;

public interface TeamsPanelListener {
    void onTeamSelected(Team team);
    void onAddNewTeam();
}

