package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.card.LeaderCard;

import java.util.ArrayList;

/**
 * A type containing a {@link Player}'s ID and 4 {@link LeaderCard} he will have to choose.
 */
public class PlayerLC {
    private String playerID;
    private ArrayList<String> leadersToChoose;

    /**
     * Instantiates a new {@link PlayerLC}.
     *
     * @param playerID        the {@link Player}'s ID.
     * @param leadersToChoose the {@link LeaderCard}s to choose.
     */
    public PlayerLC(String playerID, ArrayList<String> leadersToChoose) {
        this.playerID = playerID;
        this.leadersToChoose = leadersToChoose;
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
     * Sets {@link Player}'s ID.
     */
    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    /**
     * Gets the {@link LeaderCard}s to choose.
     *
     * @return the ArrayList containing 4 {@link LeaderCard}s.
     */
    public ArrayList<String> getLeadersToChoose() {
        return leadersToChoose;
    }

}
