package it.polimi.ingsw.view.data;

import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

/**
 * The type used by every {@link PlayerData} to save and check the status of the other players in the game
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
     * Instantiates a new {@link OtherPlayerData}.
     *
     * @param playerID the other player's nickname.
     */
    public OtherPlayerData(String playerID) {
        this.playerID = playerID;
        wareHouse = new ArrayList<>();
        strongBox = new ArrayList<>();
        slotFrontCards = new ArrayList<>();
        playedLeadersID = new ArrayList<>();
        faithPoints = 0;
        victoryPoints = 0;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setWareHouse(ArrayList<Resource> wareHouse) {
        this.wareHouse = wareHouse;
    }

    public void setStrongBox(ArrayList<Resource> strongBox) {
        this.strongBox = strongBox;
    }

    public void setSlotFrontCards(ArrayList<String> slotFrontCards) {
        this.slotFrontCards = slotFrontCards;
    }

    public void setPlayedLeadersID(ArrayList<String> playedLeadersID) {
        this.playedLeadersID = playedLeadersID;
    }

    public void setFaithPoints(int faithPoints) {
        this.faithPoints = faithPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public String getPlayerID() {
        return playerID;
    }

    public ArrayList<Resource> getWareHouse() {
        return wareHouse;
    }

    public ArrayList<Resource> getStrongBox() {
        return strongBox;
    }

    public ArrayList<String> getSlotFrontCards() {
        return slotFrontCards;
    }

    public ArrayList<String> getPlayedLeadersID() {
        return playedLeadersID;
    }

    public int getFaithPoints() {
        return faithPoints;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

}
