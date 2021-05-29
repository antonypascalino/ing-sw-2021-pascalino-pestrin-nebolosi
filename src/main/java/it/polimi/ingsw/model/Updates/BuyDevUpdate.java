package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.GameHub;

import java.util.ArrayList;

public class BuyDevUpdate implements Update {

    private ArrayList<TurnState> turnStates;
    private ArrayList<Resource[]> wareHouse;
    private ArrayList<Resource[]> extraDep;
    private ArrayList<Resource> strongBox;
    private ArrayList<String> tableCardsID; //just the front table cards
    private int victoryPoints;
    private ArrayList<String> cardsID;  //3 front cards + basic + extraProd

    public BuyDevUpdate(ArrayList<TurnState> turnStates, ArrayList<Resource[]> wareHouse, ArrayList<Resource> strongBox, ArrayList<String> tableCardsID, int victoryPoints, ArrayList<String> cardsID) {
        this.turnStates = turnStates;
        this.wareHouse = wareHouse;
        this.strongBox = strongBox;
        this.tableCardsID = tableCardsID;
        this.victoryPoints = victoryPoints;
        this.cardsID = cardsID;
    }



    @Override
    public void handleUpdate(GameHub game) {

        game.getCurrData().setTurnStates(turnStates);
        game.getCurrData().setWareHouse(wareHouse);
        game.getCurrData().setStrongBox(strongBox);
        game.getCurrData().setVictoryPoints(victoryPoints);
        game.getCurrData().setCardsID(cardsID);
        game.setFrontTableCardsID(tableCardsID);

    }
}
