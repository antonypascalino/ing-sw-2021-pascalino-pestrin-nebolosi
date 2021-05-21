package it.polimi.ingsw.view.data;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.MarketResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.view.ClientDevCard;
import it.polimi.ingsw.view.ClientLeaderCard;

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

    public ClientDevCard getCardFromID(String cardID){
        return originalData.getCardFromID(cardID);
    }

    public ClientLeaderCard getLeaderFromID(String cardID){
        return originalData.getLeaderFromID(cardID);
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

    public ArrayList<Resource[]> getWareHouse(){
        return originalData.getWareHouse();
    }

    public int switchLevels(int origin){
        return originalData.switchLevels(origin);
    }

    public ArrayList<String> leaderCardsFilter(){
        return originalData.leaderCardsFilter();
    }

    public ArrayList<String> getLeaders(){
        return originalData.getLeaders();
    }
}
