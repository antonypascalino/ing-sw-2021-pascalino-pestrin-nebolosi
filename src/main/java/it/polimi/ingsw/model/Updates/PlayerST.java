package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Table.Resource;

/**
 * Used in the {@link NewGameUpdate} during the initial phase of the {@link Game}
 * A type containing a {@link Player}'s ID, the faith points he obtains at the start game
 * and the numbers of {@link Resource} he can choose.
 */
public class PlayerST {
    private String playerID;
    private int choices;
    private int faithPoint;

    /**
     * Instantiates a new {@link PlayerST}.
     *
     * @param playerID   the {@link Player}'s ID.
     * @param choices    the number of {@link Resource} of his choice.
     * @param faithPoint the number of Faith points.
     */
    public PlayerST(String playerID, int choices, int faithPoint) {
        this.playerID = playerID;
        this.choices = choices;
        this.faithPoint = faithPoint;
    }

    /**
     * Gets {@link Player}'s ID.
     *
     * @return the {@link Player}'s ID.
     */
    public String getPlayerID() {
        return playerID;
    }

    /**
     * Gets the numbers of {@link Resource} to choose.
     *
     * @return the numbers of {@link Resource} to choose.
     */
    public int getChoices() {
        return choices;
    }

    /**
     * Gets the numbers of Faith points.
     *
     * @return the numbers of Faith Points the {@link Player} obtains at start game..
     */
    public int getFaithPoint() {
        return faithPoint;
    }
}
