package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.controller.ToMoveResource;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Updates.MoveUpdate;
import it.polimi.ingsw.model.Updates.Update;

import java.util.ArrayList;


public class MoveRequest implements Request {
    private ArrayList<ToMoveResource> toMoveResources;
    private final String className = this.getClass().getName();

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public boolean canBePlayed(Player player) {
        //Per ogni risorsa controlla che il giocatore abbia il livello in cui vuole mettere la
        //risorsa e che essa si possa spostare in quel livello.
        for (ToMoveResource toMoveRes : toMoveResources) {
            if(!player.checkLevel(toMoveRes.getFinLevel())) {
                return false;
                //lancia eccezione: non possiedi il livello in cui ha detto di voler mettere la risorsa
            }
            if(!player.checkSpace(toMoveRes.getResource(), toMoveRes.getFinLevel())) {
                //lancia eccezione: non puoi mettere questa risorsa qua
                return false;
            }
        }
        return true;
    }

    @Override
    public TurnState handle(Player player, Game game) {
        for(ToMoveResource toMoveRes : toMoveResources) {
            player.switchLevels(toMoveRes.getResource(), toMoveRes.getOgLevel(), toMoveRes.getFinLevel());
        }
        return TurnState.MOVE_RESOURCE;
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return true; //perché le risorse si possono spostare nel WareHouse in ogni momento
    }

    @Override
    public Update createUpdate(Player player, Game game) {
        return new MoveUpdate(game.getTurnStates(), player.getDeposits());
    }
}
