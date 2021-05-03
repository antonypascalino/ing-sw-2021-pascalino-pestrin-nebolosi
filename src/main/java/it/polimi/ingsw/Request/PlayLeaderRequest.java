package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;

//ABBIAMO IMPLEMENTATO SOLO NEXTTURNSTATE
public class PlayLeaderRequest implements Request {


    boolean startTurn;

    public void setStartTurn(boolean startTurn) {
        this.startTurn = startTurn;
    }

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
        if(startTurn){
            return TurnState.PLAY_LEADER_CARD_START;

        }
        else
        {
            return TurnState.PLAY_LEADER_CARD_END;
        }

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
