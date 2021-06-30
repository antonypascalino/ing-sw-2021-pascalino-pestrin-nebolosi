package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.view.data.PlayerData;
import it.polimi.ingsw.controller.Request.Request;

/**
 * A Selection guides the player through the choices he has to take when he choose the actions to do in his turn.
 * For every type of action the player can do there's one type of Selection, each of them implements this interface.
 */
public abstract class Selection {

    /**
     * The method of the {@link Selection}s which guides the player through his choices. Different for every type o action.
     * <p>
     * At the end of all the choices the player had to do, the method create a {@link Request} with all the information
     * about the action just done and send him, through the connection, to the server.
     *
     * @param data the player to guide.
     */
    public abstract void handleSelection(PlayerData data);

}
