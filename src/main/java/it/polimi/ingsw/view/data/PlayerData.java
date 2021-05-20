package it.polimi.ingsw.view.data;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.MarketResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Board.StrongBox;
import it.polimi.ingsw.model.Board.WareHouse;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.view.ClientCard;

import java.util.ArrayList;

public abstract class PlayerData {
    //qua arrivano le cose dalla connection
    PlayerData originalData;

    public ArrayList<TurnState> turnStateFilter(){
        return originalData.turnStateFilter();
    }

    public ArrayList<String> slotCardsFilter(){
        return originalData.slotCardsFilter();
    }

    public ArrayList<MappedResource> createMappedRes(ArrayList<Resource> resources){
        return originalData.createMappedRes(resources);
    }

    public ArrayList<MappedResource> allResources(){
        return originalData.allResources();
    }

    public void removeMappedResource(ArrayList<MappedResource> map){

    }

    public ClientCard getCardFromID(String cardID){
        return originalData.getCardFromID(cardID);
    }

    public Resource[][] getMarket(){
        return originalData.getMarket();
    }

    public ArrayList<MarketResource> handleWarehouse(ArrayList<Resource> res){
        return originalData.handleWarehouse(res);
    }

    public ArrayList<String> tableCardsFilter(){
        return originalData.tableCardsFilter();
    }

    public Integer handleSlots(String devID){
        return originalData.handleSlots(devID);
    }
}
