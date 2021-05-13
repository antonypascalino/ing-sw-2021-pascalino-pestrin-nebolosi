package it.polimi.ingsw.view.data;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Board.StrongBox;
import it.polimi.ingsw.model.Board.WareHouse;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

public abstract class PlayerData {
    //qua arrivano le cose dalla connection
    PlayerData originalData;

    public ArrayList<TurnState> turnStateFilter(){
        return originalData.turnStateFilter();
    }

    public ArrayList<String> cardsFilter(){
        return originalData.cardsFilter();
    }
}
