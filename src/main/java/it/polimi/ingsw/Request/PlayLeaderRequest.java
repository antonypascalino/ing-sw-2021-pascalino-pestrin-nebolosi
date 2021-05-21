package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

//ABBIAMO IMPLEMENTATO SOLO NEXTTURNSTATE
public class PlayLeaderRequest implements Request {

    private String cardID;
    private final String className;

    public PlayLeaderRequest(String cardID) {
        this.className = this.getClass().getName();
        this.cardID = cardID;
    }

    @Override
    public TurnState handle(Player player, Game gane) {
        player.getLeaderFromID(cardID).playCard();
        return TurnState.PLAY_LEADER_CARD;
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
    public String getClassName() {
        return className;
    }
}
