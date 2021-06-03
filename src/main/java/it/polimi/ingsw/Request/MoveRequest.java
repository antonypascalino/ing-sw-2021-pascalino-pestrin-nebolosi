package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Updates.MoveUpdate;
import it.polimi.ingsw.model.Updates.Update;

import java.util.ArrayList;


public class MoveRequest implements Request {
    private int originLevel;
    private int destLevel;
    private final String className = this.getClass().getName();
    private String playerID;
    private int gameID;

    public MoveRequest(String playerID, int gameID, int originLevel, int destLevel) {
        this.playerID = playerID;
        this.gameID = gameID;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public boolean canBePlayed(Player player) {
        //Per ogni risorsa controlla che il giocatore abbia il livello in cui vuole mettere la
        //risorsa e che essa si possa spostare in quel livello.
        if (!player.checkLevel(destLevel)) {
            return false;
            //lancia eccezione: non possiedi il livello in cui ha detto di voler mettere la risorsa
        }
//            if(!player.checkSpace(toMoveRes.getResource(), toMoveRes.getFinLevel())) {
//                //lancia eccezione: non puoi mettere questa risorsa qua
//                return false;
//
        return true;
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
