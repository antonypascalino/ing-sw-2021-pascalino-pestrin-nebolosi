package it.polimi.ingsw.view.data;

import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.clientCards.AllGameCards;

import java.util.ArrayList;

/**
 * The type Other player data.
 */
public class OtherPlayerData {

    private String playerID;
    private ArrayList<Resource> wareHouse;
    private ArrayList<Resource> strongBox;
    private ArrayList<String> slotFrontCards;
    private ArrayList<String> playedLeadersID;
    private int faithPoints;
    private int victoryPoints;

    /**
     * Instantiates a new Other player data.
     *
     * @param playerID the player id
     */
    public OtherPlayerData(String playerID) {
        this.playerID = playerID;
        wareHouse = new ArrayList<Resource>();
        strongBox = new ArrayList<Resource>();
        slotFrontCards = new ArrayList<String>();
        playedLeadersID = new ArrayList<String>();
        faithPoints = 0;
        victoryPoints = 0;
    }

    /**
     * Sets player id.
     *
     * @param playerID the player id
     */
    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    /**
     * Sets ware house.
     *
     * @param wareHouse the ware house
     */
    public void setWareHouse(ArrayList<Resource> wareHouse) {
        this.wareHouse = wareHouse;
    }

    /**
     * Sets strong box.
     *
     * @param strongBox the strong box
     */
    public void setStrongBox(ArrayList<Resource> strongBox) {
        this.strongBox = strongBox;
    }

    /**
     * Sets slot front cards.
     *
     * @param slotFrontCards the slot front cards
     */
    public void setSlotFrontCards(ArrayList<String> slotFrontCards) {
        this.slotFrontCards = slotFrontCards;
    }

    /**
     * Sets played leaders id.
     *
     * @param playedLeadersID the played leaders id
     */
    public void setPlayedLeadersID(ArrayList<String> playedLeadersID) {
        this.playedLeadersID = playedLeadersID;
    }

    /**
     * Sets faith points.
     *
     * @param faithPoints the faith points
     */
    public void setFaithPoints(int faithPoints) {
        this.faithPoints = faithPoints;
    }

    /**
     * Sets victory points.
     *
     * @param victoryPoints the victory points
     */
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    /**
     * Gets player id.
     *
     * @return the player id
     */
    public String getPlayerID() {
        return playerID;
    }

    /**
     * Gets ware house.
     *
     * @return the ware house
     */
    public ArrayList<Resource> getWareHouse() {
        return wareHouse;
    }

    /**
     * Gets strong box.
     *
     * @return the strong box
     */
    public ArrayList<Resource> getStrongBox() {
        return strongBox;
    }

    /**
     * Gets slot front cards.
     *
     * @return the slot front cards
     */
    public ArrayList<String> getSlotFrontCards() {
        return slotFrontCards;
    }

    /**
     * Gets played leaders id.
     *
     * @return the played leaders id
     */
    public ArrayList<String> getPlayedLeadersID() {
        return playedLeadersID;
    }

    /**
     * Gets faith points.
     *
     * @return the faith points
     */
    public int getFaithPoints() {
        return faithPoints;
    }

    /**
     * Gets victory points.
     *
     * @return the victory points
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

}
