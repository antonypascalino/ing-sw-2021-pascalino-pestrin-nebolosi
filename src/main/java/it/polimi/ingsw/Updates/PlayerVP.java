package it.polimi.ingsw.Updates;

import it.polimi.ingsw.model.Player.Player;

/**
 * A type containing a {@link Player}'s ID and his victory points.
 */
public class PlayerVP {
    private String playerID;
    private int victoryPoints;

    /**
     * Instantiates a new {@link PlayerVP}.
     *
     * @param playerID      the {@link Player}'s ID.
     * @param victoryPoints the {@link Player}'s victory points.
     */
    public PlayerVP(String playerID, int victoryPoints) {
        this.playerID = playerID;
        this.victoryPoints = victoryPoints;
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
     * Gets {@link Player}'s Victory points.
     *
     * @return the {@link Player}'s Victory points.
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }
}
