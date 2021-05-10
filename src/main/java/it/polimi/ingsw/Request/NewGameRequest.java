package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

public class NewGameRequest implements Request{

    private String nickname; //Name of the first player
    private int players; //Number of players in the game
    private final String className;

    public NewGameRequest(String nickname, int players)
    {
        this.className = this.getClass().getName();
        this.nickname = nickname;
        this.players = players;
    }

    @Override
    public void handle(Player player) {

    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
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
    public int getPlayerChoices() {
        return 0;
    }

    @Override
    public String getClassName() {
        return className;

    }

    public String getNickname() {
        return nickname;
    }

    public int getPlayers() {
        return players;
    }
}
