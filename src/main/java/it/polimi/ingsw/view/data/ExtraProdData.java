package it.polimi.ingsw.view.data;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.view.Printer;

import java.util.ArrayList;

public class ExtraProdData extends PlayerData{
    private ArrayList<TurnState> turnStates;
    private TurnState turnState;
    private ArrayList<Resource[]> wareHouse;
    private ArrayList<Resource> strongBox;
    private ArrayList<String> tableCardsID; //just the front table cards
    private int faithPoints;
    private int victoryPoints;
    private ArrayList<String> cardsID;  //3 front cards + basic + extraProd
    private ArrayList<String> leadersID;
    private ArrayList<String> leadersPlayedID;
    private Resource[][] market;
    private Printer printer;
    private ArrayList<Resource[]> extraDep;

    public ExtraProdData(ArrayList<String> cardID, ArrayList<TurnState> turnStates, TurnState turnState, ArrayList<Resource[]> wareHouse, ArrayList<Resource> strongBox, int faithPoints, int victoryPoints, ArrayList<String> cardsID, ArrayList<String> leadersID, Resource[][] market, ArrayList<String> tableCardsID) {
        this.turnStates = turnStates;
        this.turnState = turnState;
        this.wareHouse = wareHouse;
        this.strongBox = strongBox;
        this.faithPoints = faithPoints;
        this.victoryPoints = victoryPoints;
        this.cardsID = cardsID;
        this.leadersID = leadersID;
        this.market = market;
        this.tableCardsID = tableCardsID;
    }


}
