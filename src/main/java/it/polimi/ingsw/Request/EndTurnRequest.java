package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;

public class EndTurnRequest implements Request {
    @Override
    public void handle(Player player) {

    }

    @Override
    public boolean validRequest(TurnState turnState) {
        return false;
    }

    @Override
    public boolean canBePlayed(Player player) {
        return false;
    }

    @Override
    public TurnState nextTurnState() {
        return null;
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
        return null;
    }
}
