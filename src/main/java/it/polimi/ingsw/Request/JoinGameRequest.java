package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.Updates.Update;

import java.util.ArrayList;

/**
 * The {@link Request} sent by a player when he wants to join a {@link Game}.
 */
public class JoinGameRequest implements Request {

    private final String className;
    private int gameId;
    private String nickName;
    private String playerID;

    /**
     * Instantiates a new {@link JoinGameRequest} setting the {@link Game}'s ID and the player's nickname.
     *
     * @param gameId   the {@link Game}'s ID.
     * @param nickName the {@link Player}'s ID.
     */
    public JoinGameRequest(int gameId, String nickName) {
        className = this.getClass().getName();
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

    public int getGameID() {
        return gameId;
    }

    /**
     * Gets nick name.
     *
     * @return the nick name
     */
    public String getNickName() {
        return nickName;
    }

    public String getPlayerID() {
        return playerID;
    }
}
