package it.polimi.ingsw.view.clientCards;

import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The type Client leader card.
 */
public abstract class ClientLeaderCard {

    private String leaderID;
    private String power;
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
     * @param power         the power
     * @param victoryPoints the victory points
     */
    public ClientLeaderCard(String leaderID, String power, int victoryPoints) {
        this.power = power;
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

//    public ArrayList<Resource> getRequired(){
//        return required;
//    }
//
//    public ArrayList<Resource> getPrice(){
//        return price;
//    }
//
//    public Resource getPlaceable(){
//        return placeable;
//    }
//
//    public int getLevel(){
//        return levl;
//    }
//
//    public Resource getChangeRes(){return changeRes;}

}
