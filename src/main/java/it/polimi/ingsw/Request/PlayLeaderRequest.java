package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Cards.LeaderCard;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

//ABBIAMO IMPLEMENTATO SOLO NEXTTURNSTATE
public class PlayLeaderRequest implements Request {

    private String cardID;

    public PlayLeaderRequest(String cardID) {
        this.cardID = cardID;
    }

    @Override
    public void handle(Player player) {

    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return !(turnStates.contains(TurnState.PLAY_LEADER_CARD) || turnStates.contains(TurnState.DISCARD_LEADER_CARD));
    }

    @Override
    public boolean canBePlayed(Player player) {
        return player.getLeaderFromID(cardID).canBePlayed();
    }

    @Override
    public TurnState nextTurnState() {
        return TurnState.PLAY_LEADER_CARD;
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
    public int getPlayerChoices() {
        return 0;
    }

    @Override
    public String getClassName() {
        return "PlayLeaderRequest";
    }
}
