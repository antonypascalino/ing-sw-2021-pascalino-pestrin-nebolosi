package it.polimi.ingsw.Request;

import com.google.inject.internal.util.Join;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

public class JoinGameRequest implements Request{

    private final String className;
    private int gameId;
    private String nickName;

    public JoinGameRequest(int gameId, String nickName)
    {
        className=this.getClass().getName();
        this.gameId = gameId;
        this.nickName = nickName;
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

    public int getGameId() {
        return gameId;
    }

    public String getNickName()
    {
        return nickName;
    }
}
