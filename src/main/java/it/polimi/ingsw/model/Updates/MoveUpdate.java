package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

public class MoveUpdate implements Update {
    private ArrayList<TurnState> turnStates;
    private ArrayList<Resource[]> wareHouse;

    public MoveUpdate(ArrayList<TurnState> turnStates, ArrayList<Resource[]> wareHouse) {
        this.turnStates = turnStates;
        this.wareHouse = wareHouse;
    }

    @Override
    public void handleUpdate(Game game) {

    }
}
