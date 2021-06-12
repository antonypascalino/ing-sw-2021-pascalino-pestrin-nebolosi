package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.Convertible;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.view.data.PlayerData;
import it.polimi.ingsw.model.Player.Player;

/**
 * The interface implemented by every update.
 * <p>
 * An update is an object created by the {@link Game} and sent by the {@link Player}s, server side,
 * to the {@link PlayerData}s, client side.
 * The Update contains the information about of what happened on the server; once it is received by the players,
 * it modifies their status according to the information in itself. Some updates bring with them even a message to show.
 */
public interface Update extends Convertible {

    /**
     * Called by all the {@link PlayerData} on the client, so that it can modifies
     * themself according to wha was modified on the server.
     * <p>
     * The {@link Update} is handled in two different ways by the players based on
     * whether the update is relative to an action the player did himself or if the update involves an other player.
     */
    public void handleUpdate(PlayerData data);

}