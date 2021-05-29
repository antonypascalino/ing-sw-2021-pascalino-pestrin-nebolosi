package it.polimi.ingsw.model.Updates;

public class PlayerFP {
    private String playerID;
    private int faithPoints;

    public PlayerFP(String playerID, int faithPoints) {
        this.playerID = playerID;
        this.faithPoints = faithPoints;
    }

    public String getPlayerID() {
        return playerID;
    }

    public int getFaithPoints() {
        return faithPoints;
    }
}
