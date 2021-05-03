package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.controller.ToMoveResource;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;


public class MoveRequest implements Request {
    private ArrayList<ToMoveResource> toMoveResources;

    @Override
    public String getClassName() {
        return "MoveRequest";
    }

    @Override
    public void handle(Player player) {
        for(ToMoveResource toMoveRes : toMoveResources) {
            player.getBoard().getWareHouse().switchLevels(toMoveRes.getResource(), toMoveRes.getOgLevel(), toMoveRes.getFinLevel());
        }
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return true; //perché le risorse si possono spostare nel WareHouse in ogni momento
    }

    @Override
    public boolean canBePlayed(Player player) {
        //Per ogni risorsa controlla che il giocatore abbia il livello in cui vuole mettere la
        //risorsa e che essa si possa spostare in quel livello.
        for (ToMoveResource toMoveRes : toMoveResources) {
            if(!player.checkLevel(toMoveRes.getFinLevel())) {
                //lancia eccezione: non possiedi il livello in cui ha detto di voler mettere la risorsa
            }
            if(!player.getBoard().getWareHouse().checkSpace(toMoveRes.getFinLevel(), toMoveRes.getResource())) {
                //lancia eccezione: non puoi mettere questa risorsa qua
            }
        }
        return true;
    }

    @Override
    public TurnState nextTurnState() {
        return TurnState.MOVE_RESOURCE;
    }

    @Override
    public int getMyFPSteps() {
        return 0;
    }

    @Override
    public int getDiscardedSteps() {
        return 0;
    }
}
