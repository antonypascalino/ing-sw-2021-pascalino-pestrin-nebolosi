package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class EndTurnUpdate implements Update{
    private ArrayList<TurnState> turnStates;
    private String nextPlayer;
    private final String className;


    public EndTurnUpdate(ArrayList<TurnState> turnStates, String nextPlayer) {
        this.nextPlayer = nextPlayer;
        this.turnStates = turnStates;
        className = this.getClass().getName();
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        if (data.getPlayerID().equals(nextPlayer)) {
            data.getMenu().menuMaker();
        }
    }
}
