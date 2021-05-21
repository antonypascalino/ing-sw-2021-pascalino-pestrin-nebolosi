package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

public class DiscardLeaderRequest implements Request{

    private final String className;
    private String cardID;

    public DiscardLeaderRequest(String cardID) {
        className = this.getClass().getName();
        this.cardID = cardID;
    }

    @Override
    public boolean canBePlayed(Player player) {
        return !player.getLeaderFromID(cardID).isEnable();
    }

    @Override
    public TurnState handle(Player player, Game game) {
        //Forse non lo stiamo rimuovendo dal player ma solo da un clone di quell'arrayList
        player.getLeaderCards().remove(player.getLeaderFromID(cardID));
        //Notify all the players that this handle didn't discard any steps but moved the player forward by one
        game.fpAdvancement(0,1);
        return TurnState.DISCARD_LEADER_CARD;
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return !(turnStates.contains(TurnState.PLAY_LEADER_CARD) || turnStates.contains(TurnState.DISCARD_LEADER_CARD));
    }

    @Override
    public String getClassName() {
        return className;
    }

}
