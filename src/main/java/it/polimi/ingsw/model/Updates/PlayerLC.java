package it.polimi.ingsw.model.Updates;

import java.util.ArrayList;

public class PlayerLC {
    private String playerID;
    private ArrayList<String> leadersToChoose;

    public PlayerLC(String playerID, ArrayList<String> leadersToChoose) {
        this.playerID = playerID;
        this.leadersToChoose = leadersToChoose;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public ArrayList<String> getLeadersToChoose() {
        return leadersToChoose;
    }

    public void setLeadersToChoose(ArrayList<String> leadersToChoose) {
        this.leadersToChoose = leadersToChoose;
    }
}
