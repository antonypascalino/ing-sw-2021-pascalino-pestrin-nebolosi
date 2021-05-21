package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
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
    public TurnState handle(Player curr, Game game) {
        return TurnState.LOBBY;
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
