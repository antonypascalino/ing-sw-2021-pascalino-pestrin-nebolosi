package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class EndgameUpdate implements Update {

    private String winner;
    private ArrayList<PlayerVP> finalPlayersVPS;
    private final String className;

    public EndgameUpdate(String winner) {
        className = this.getClass().getName();
        this.winner = winner;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        data.getPrinter().endgame(this);
    }

    public String getWinner() {
        return winner;
    }

    public ArrayList<PlayerVP> getFinalPlayersVPS() {
        return finalPlayersVPS;
    }

    @Override
    public String getClassName() {
        return className;
    }
}
