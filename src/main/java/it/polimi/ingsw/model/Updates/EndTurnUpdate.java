package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class EndTurnUpdate implements Update{
    private ArrayList<TurnState> turnStates;
    private String nextPlayer;

    public EndTurnUpdate(ArrayList<TurnState> turnStates, String nextPlayer) {
        this.nextPlayer = nextPlayer;
        this.turnStates = turnStates;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        if (data.getPlayerID().equals(nextPlayer)) {
            data.getMenu().menuMaker();
        }
    }
}
