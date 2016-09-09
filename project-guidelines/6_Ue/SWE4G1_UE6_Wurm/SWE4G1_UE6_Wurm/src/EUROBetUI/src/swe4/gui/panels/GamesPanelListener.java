package swe4.gui.panels;

import swe4.models.Game;

public interface GamesPanelListener {
    void onGameSelected(Game game);
    void onAddNewGame();
}

