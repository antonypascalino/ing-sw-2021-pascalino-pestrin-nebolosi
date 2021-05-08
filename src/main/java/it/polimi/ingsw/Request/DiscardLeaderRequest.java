package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

public class DiscardLeaderRequest implements Request{

    private int myFPSteps;
    private String cardID;

    public DiscardLeaderRequest(String cardID) {
        this.myFPSteps = 0;
        this.cardID = cardID;
    }

    @Override
    public void handle(Player player) {
        myFPSteps = 1;
        player.getLeaderCards().remove(player.getLeaderFromID(cardID));
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return !(turnStates.contains(TurnState.PLAY_LEADER_CARD) || turnStates.contains(TurnState.DISCARD_LEADER_CARD));
    }

    @Override
    public boolean canBePlayed(Player player) {
        return !player.getLeaderFromID(cardID).isEnable();
    }

    @Override
    public TurnState nextTurnState() {
        return TurnState.DISCARD_LEADER_CARD;
    }

    @Override
    public int getMyFPSteps() {
        return myFPSteps;
    }

    @Override
    public int getDiscardedSteps() {
        return 0;
    }

    @Override
    public int getPlayerChoices() {
        return 0;
    }

    @Override
    public String getClassName() {
        return "DiscardLeaderRequest";
    }
}
