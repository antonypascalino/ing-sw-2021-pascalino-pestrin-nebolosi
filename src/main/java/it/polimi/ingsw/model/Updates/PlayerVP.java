package it.polimi.ingsw.model.Updates;

public class PlayerVP {
    private String playerID;
    private int victoryPoints;

    public PlayerVP(String playerID, int victoryPoints) {
        this.playerID = playerID;
        this.victoryPoints = victoryPoints;
    }

    public String getPlayerID() {
        return playerID;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }
}
