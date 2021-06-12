package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Updates.EndTurnUpdate;
import it.polimi.ingsw.model.Updates.Update;
import it.polimi.ingsw.model.card.DevCard;

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
        if (game.getCurrPlayerInt() + 1 >= game.getMax()) { //Se è già stato completato il giro, ricomincia
            game.setCurrPlayerInt(0);
        } else {
            game.setCurrPlayerInt(game.getCurrPlayerInt() + 1); //Se il giro non è ancora stato completato, contiunua
        }
        game.setNextPlayer(game.getPlayers().get(game.getCurrPlayerInt())); //Setta il successivo currPlayer
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
