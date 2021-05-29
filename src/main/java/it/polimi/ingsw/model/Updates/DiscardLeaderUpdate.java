package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;

import java.util.ArrayList;

public class DiscardLeaderUpdate implements Update {
    private ArrayList<TurnState> turnStates;
    private int faithPoints;
    private ArrayList<String> leadersID;
    private ArrayList<PlayerVP> playersVP;

    public DiscardLeaderUpdate(ArrayList<TurnState> turnStates, int faithPoints, ArrayList<String> leadersID, ArrayList<PlayerVP> playersVP) {
        this.turnStates = turnStates;
        this.faithPoints = faithPoints;
        this.leadersID = leadersID;
        this.playersVP = playersVP;
    }

    @Override
    public void handleUpdate(Game game) {

    }
}
