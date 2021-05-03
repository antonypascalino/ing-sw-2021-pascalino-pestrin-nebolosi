package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.controller.toMoveResource;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;


public class MoveRequest implements Request {
    private ArrayList<toMoveResource> toMoveResources;

    @Override
    public String getClassName() {
        return "MoveRequest";
    }

    @Override
    public void handle(Player player) {
        for(toMoveResource toMoveRes : toMoveResources) {
            player.getBoard().getWareHouse().switchLevels(toMoveRes.getResource(), toMoveRes.getOgLevel(), toMoveRes.getFinLevel());
        }

    }

    @Override
    public boolean validRequest(TurnState turnState) {
        return true; //perché le risorse si possono spostare nel WareHouse in ogni momento
    }

    @Override
    public boolean canBePlayed(Player player) {

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
