package it.polimi.ingsw.Updates;

import it.polimi.ingsw.model.Player.Player;

/**
 * A type containing a {@link Player}'s ID and his faith points.
 */
public class PlayerFP {
    private String playerID;
    private int faithPoints;

    /**
     * Instantiates a new {@link PlayerFP}.
     *
     * @param playerID    the {@link Player}'s ID.
     * @param faithPoints the {@link Player}'s faith points.
     */
    public PlayerFP(String playerID, int faithPoints) {
        this.playerID = playerID;
        this.faithPoints = faithPoints;
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
     * Gets {@link Player}'s faith points.
     *
     * @return the {@link Player}'s faith points.
     */
    public int getFaithPoints() {
        return faithPoints;
    }
}
