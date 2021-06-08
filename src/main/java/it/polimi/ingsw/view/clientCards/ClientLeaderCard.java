package it.polimi.ingsw.view.clientCards;

import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The type Client leader card.
 */
public abstract class ClientLeaderCard {

    private String leaderID;
    private Resource powerResource; //A resource that change by the type of leaderCard: for ExtraDep is the placeable, for Change is the changeable, for Discount is the discount and for ExtraProd is the required Resource for produce
    /**
     * The Victory points.
     */
    protected int victoryPoints;
//    public ArrayList<Resource> price;
//    public Resource placeable;
//    public int level;
//    private Resource changeRes;
//    public ArrayList<Resource> required;

    /**
     * Instantiates a new Client leader card.
     *
     * @param leaderID      the leader id
     * @param victoryPoints the victory points
     */
    public ClientLeaderCard(String leaderID, int victoryPoints, Resource powerResource) {
        this.powerResource = powerResource;
        this.leaderID = leaderID;
        this.victoryPoints = victoryPoints;
    }

    /**
     * Gets leader id.
     *
     * @return the leader id
     */
    public String getLeaderID() {
        return leaderID;
    }

    /**
     * Can be played boolean.
     *
     * @param data the data
     * @return the boolean
     */
    public abstract boolean canBePlayed(PlayerData data);

    public Resource getPowerResource() {
        return powerResource;
    }


}
