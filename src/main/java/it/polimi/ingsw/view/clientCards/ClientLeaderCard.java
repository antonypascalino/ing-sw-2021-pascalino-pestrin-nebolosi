package it.polimi.ingsw.view.clientCards;

import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

/**
 * A Client Leader card is a light version of the Leader Card on the server.
 * This abstract class is extended by every Leader card client side.
 */
public abstract class ClientLeaderCard {

    private String leaderID;
    private Resource powerResource; //A resource that change by the type of leaderCard: for ExtraDep is the placeable, for Change is the changeable, for Discount is the discount and for ExtraProd is the required Resource for produce
    protected int victoryPoints;

    /**
     * Called by the classes who extends a {@link ClientLeaderCard}.
     *
     * @param leaderID      the leader id
     * @param victoryPoints the victory points
     * @param powerResource the power resource
     */
    public ClientLeaderCard(String leaderID, int victoryPoints, Resource powerResource) {
        this.powerResource = powerResource;
        this.leaderID = leaderID;
        this.victoryPoints = victoryPoints;
    }

    public String getLeaderID() {
        return leaderID;
    }

    /**
     * Check if the {@link ClientLeaderCard} can be played by the player according to its requirements.
     *
     * @param data the {@link PlayerData} who wants to play the card.
     * @return true if the card can be played, false otherwise.
     */
    public abstract boolean canBePlayed(PlayerData data);

    public Resource getPowerResource() {
        return powerResource;
    }


}
