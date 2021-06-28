package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Updates.MoveUpdate;
import it.polimi.ingsw.model.Updates.Update;

import java.util.ArrayList;

/**
 * The {@link Request} sent by a player when he wants to move {@link Resource}s in his deposits.
 */
public class MoveRequest implements Request {
    private int originLevel;
    private int destLevel;
    private final String className;
    private String playerID;
    private int gameID;

    /**
     * Instantiates a new {@link MoveRequest} setting the origin level from where the movement start and the dest level
     * where the movement ends.
     *
     * @param gameID      the {@link Game}'s ID.
     * @param playerID    the {@link Player}'s ID.
     * @param originLevel the origin level
     * @param destLevel   the dest level
     */
    public MoveRequest(String playerID, int gameID, int originLevel, int destLevel) {
        this.originLevel = originLevel;
        this.destLevel = destLevel;
        this.playerID = playerID;
        this.gameID = gameID;
        className = this.getClass().getName();
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public boolean canBePlayed(Player player) {
        //Controlla che il giocatore possegga il livello di destinazione
        if (!player.checkLevel(destLevel)) {
            return false;
        }
        //Since the check depends on the type of player (it's different if it has some extradep) this check is done by the player
        return player.checkSwitch(originLevel, destLevel);
    }

    @Override
    public int getGameID() {
        return gameID;
    }

    @Override
    public TurnState handle(Player player, Game game) {
        player.switchLevels(originLevel, destLevel);
        return TurnState.MOVE_RESOURCE;
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return true; //perché le risorse si possono spostare nel WareHouse in ogni momento
    }

    @Override
    public Update createUpdate(Player player, Game game) {
        return new MoveUpdate(player.getNickName(), game.getTurnStates(), player.getDeposits());
    }

    @Override
    public String getPlayerID() {
        return playerID;
    }
}
