package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.view.GameHub;

public interface Update {

    public void handleUpdate(GameHub game);

}