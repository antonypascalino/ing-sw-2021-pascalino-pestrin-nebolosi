package it.polimi.ingsw.view.data;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.clientCards.ClientDevCard;
import it.polimi.ingsw.view.Printer;

import java.util.ArrayList;

public class DiscountData extends PlayerData{

//    private ArrayList<TurnState> turnStates;
//    private TurnState turnState;
//    private ArrayList<Resource[]> wareHouse;
//    private ArrayList<Resource> strongBox;
//    private ArrayList<String> tableCardsID; //just the front table cards
//    private int faithPoints;
//    private int victoryPoints;
//    private ArrayList<String> cardsID;  //3 front cards + basic + extraProd
//    private ArrayList<String> leadersID;
//    private ArrayList<String> leadersPlayedID;
//    private Resource[][] market;
//    private Printer printer;
    private ArrayList<Resource> discount;

    public DiscountData( PlayerData originalData, ArrayList<Resource> discount, ArrayList<String> cardID, ArrayList<TurnState> turnStates, TurnState turnState, ArrayList<Resource[]> wareHouse, ArrayList<Resource> strongBox, int faithPoints, int victoryPoints, ArrayList<String> cardsID, ArrayList<String> leadersID, Resource[][] market, ArrayList<String> tableCardsID) {
//        this.turnStates = turnStates;
//        this.turnState = turnState;
//        this.wareHouse = wareHouse;
//        this.strongBox = strongBox;
//        this.faithPoints = faithPoints;
//        this.victoryPoints = victoryPoints;
//        this.cardsID = cardsID;
//        this.leadersID = leadersID;
//        this.market = market;
//        this.tableCardsID = tableCardsID;
        this.discount = discount;
        this.originalData = originalData;
    }


    public ArrayList<String> tableCardsFilter(ArrayList<MappedResource> mapped){

        ArrayList<String> cloned = new ArrayList<String>();
        cloned.addAll(originalData.getTableCardsID());
        ArrayList<Resource> allRes = new ArrayList<Resource>();
        for(MappedResource m : mapped){
            allRes.add(m.getResource());
        }
        allRes.addAll(discount);
        cloned.removeIf(card -> !allRes.containsAll(getCardFromID(card).getPrice()));
        return cloned;
    }

}
