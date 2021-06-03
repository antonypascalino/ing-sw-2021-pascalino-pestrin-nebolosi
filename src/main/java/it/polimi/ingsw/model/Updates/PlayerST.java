package it.polimi.ingsw.model.Updates;

public class PlayerST {
    private String playerID;
    private int choices;
    private int faithPoint;

    public PlayerST(String playerID, int choices, int faithPoint) {
        this.playerID = playerID;
        this.choices = choices;
        this.faithPoint = faithPoint;
    }
}
