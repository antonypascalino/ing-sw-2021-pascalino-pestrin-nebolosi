package it.polimi.ingsw.Request;

import it.polimi.ingsw.Convertible;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Updates.Update;

import java.util.ArrayList;

/**
 * The interface used by all type of request the player could send.
 * A request contains the information about of the action a player would like to do.
 */
public interface Request extends Convertible {

    /**
     * Handle the request sent by the {@link Player} modifying his statuts on the server according to
     * what he said wants to do.
     *
     * @param curr the curr
     * @param game the game
     * @return the action just done. Used by the {@link Game} so can have all the information  about of what every Player did in his turn.
     */
    public TurnState handle(Player curr, Game game);

    /**
     * Check if the {@link Player} who sent the request can do the actions he wants in this moment of his turn
     * and in this moment of the {@link Game}.
     *
     * @param turnStates the turn states
     * @return true if he can, false otherwise.
     */
    public boolean validRequest(ArrayList<TurnState> turnStates);

    /**
     * Check if the {@link Player}'s status satisfied all the requirements needed to handle the request he sent.
     *
     * @param player the {@link Player} who sent the request.
     * @return true if the player satisfied all the requirements, false otherwise.
     */
    public boolean canBePlayed(Player player);

    /**
     * According to the modifies bring to the model by the request, this method create the relative {@link Update}
     * that will send to the players client side.
     *
     * @param player the {@link Player} who sent this request.
     * @param game   the {@link Game} who received the request reference.
     * @return the update
     */
    public Update createUpdate(Player player, Game game);

    /**
     * Gets the {@link Player}'s ID who sent the request.
     *
     * @return the {@link Player's ID.
     */
    public String getPlayerID();

    /**
     * Gets the {@link Game}'IdD which received the request.
     *
     * @return the game's ID.
     */
    public int getGameID();
}
