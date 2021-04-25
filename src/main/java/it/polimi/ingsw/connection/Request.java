package it.polimi.ingsw.connection;

import it.polimi.ingsw.model.Player.Player;

public interface Request {
    /**
     * Activate the request calling the right methods in the controller classes
     */

    //The player trying to request
    Player player = null;
    public void handle();
}