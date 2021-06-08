package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class PlayLeaderUpdate implements Update {

    private final String className;
    private ArrayList<String> leadersPlayed;
    private ArrayList<String> leadersNOTPlayed;
    private String leaderPlayedID;
    private String playerID;

    public PlayLeaderUpdate() {
        className = this.getClass().getName();
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void handleUpdate(PlayerData data) {

    }
}
