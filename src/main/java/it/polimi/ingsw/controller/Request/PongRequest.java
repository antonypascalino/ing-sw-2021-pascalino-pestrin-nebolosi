package it.polimi.ingsw.controller.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.Updates.Update;

import java.util.ArrayList;

/**
 * Whenever the client wants to answer
 */
public class PongRequest implements Request {
    private final String className;

    public PongRequest() {
        this.className = this.getClass().getName();
    }

    @Override
    public TurnState handle(Player curr, Game game) {
        return null;
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
    public Update createUpdate(Player player, Game game) {
        return null;
    }

    @Override
    public String getPlayerID() {
        return null;
    }

    @Override
    public int getGameID() {
        return 0;
    }

    @Override
    public String getClassName() {
        return className;
    }
}
