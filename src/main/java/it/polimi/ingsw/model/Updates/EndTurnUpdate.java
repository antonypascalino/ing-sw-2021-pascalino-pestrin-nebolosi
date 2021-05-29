package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;

import java.util.ArrayList;

public class EndTurnUpdate implements Update{
    private ArrayList<TurnState> turnStates;

    public EndTurnUpdate(ArrayList<TurnState> turnStates) {
        this.turnStates = turnStates;
    }

    @Override
    public void handleUpdate(Game game) {

    }
}
