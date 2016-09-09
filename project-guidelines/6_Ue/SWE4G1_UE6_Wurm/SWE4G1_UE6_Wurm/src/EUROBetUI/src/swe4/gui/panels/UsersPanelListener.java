package swe4.gui.panels;

import swe4.models.User;

public interface UsersPanelListener {
    void onUserSelected(User user);
    void onAddNewUser();
}

