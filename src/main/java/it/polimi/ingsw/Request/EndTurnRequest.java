package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.Updates.EndTurnUpdate;
import it.polimi.ingsw.Updates.Update;

import java.util.ArrayList;

/**
 * The {@link Request} sent by a player when he wants to end his turn.
 */
public class EndTurnRequest implements Request {
    private final String className = this.getClass().getName();
    private String playerID;
    private int gameID;

    /**
     * Instantiates a new {@link EndTurnRequest} setting the information for handle the specific actions:
     * the {@link Game}'s ID, the player's nickname.
     *
     * @param playerID the player id
     * @param gameID   the game id
     */
    public EndTurnRequest(String playerID, int gameID) {
        this.playerID = playerID;
        this.gameID = gameID;
    }

    @Override
    public TurnState handle(Player player, Game game) {
        if (game.getCurrPlayerInt() + 1 >= game.getMax()) { //if loop already completed : restart
            game.setCurrPlayerInt(0);
        } else {
            game.setCurrPlayerInt(game.getCurrPlayerInt() + 1); //if loop not completed : continue
        }
        game.setNextPlayer(game.getPlayers().get(game.getCurrPlayerInt())); //set next current player
        return TurnState.END_TURN;
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return (turnStates.contains(TurnState.BUY_DEV_CARD) || turnStates.contains(TurnState.PRODUCE) || turnStates.contains(TurnState.GET_FROM_MARKET));
    }

    @Override
    public int getGameID() {
        return gameID;
    }

    @Override
    public boolean canBePlayed(Player player) {
        return true;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public Update createUpdate(Player player, Game game) {
        return new EndTurnUpdate(game.getCurrPlayer().getNickName());
    }

    @Override
    public String getPlayerID() {
        return playerID;
    }
}
