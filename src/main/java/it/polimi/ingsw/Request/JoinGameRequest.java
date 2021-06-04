package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Updates.Update;

import java.util.ArrayList;

public class JoinGameRequest implements Request{

    private final String className;
    private int gameId;
    private String nickName;
    private String playerID;

    public JoinGameRequest(int gameId, String nickName)
    {
        className=this.getClass().getName();
        this.gameId = gameId;
        this.nickName = nickName;
    }
    @Override
    public TurnState handle(Player player, Game game) {

        return TurnState.LOBBY;
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return true;
    }

    @Override
    public boolean canBePlayed(Player player) {
        return true;
    }

    @Override
    public Update createUpdate(Player player, Game game) {
        return null;
    }

    @Override
    public String getClassName() {
        return className;
    }

    public int getGameId() {
        return gameId;
    }

    public String getNickName()
    {
        return nickName;
    }

    public String getPlayerID(){
        return playerID;
    }
}
