package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

public class EndTurnRequest implements Request {
    @Override
    public void handle(Player player) {
     // chiama un qualcosa per far inviare i dati dal model alla view                                                                        
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return (turnStates.contains(TurnState.BUY_DEV_CARD) || turnStates.contains(TurnState.PRODUCE) || turnStates.contains(TurnState.GET_FROM_MARKET));
    }

    @Override
    public boolean canBePlayed(Player player) {
        return true;
    }

    @Override
    public TurnState nextTurnState() {
        return TurnState.END_TURN;
    }

    @Override
    public int getMyFPSteps() {
        return 0;
    }

    @Override
    public int getDiscardedSteps() {
        return 0;
    }

    @Override
    public String getClassName() {
        return "EndTurnRequest";
    }
}
