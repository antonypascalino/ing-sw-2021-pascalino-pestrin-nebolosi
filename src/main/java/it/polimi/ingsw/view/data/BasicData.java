package it.polimi.ingsw.view.data;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.MarketResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.view.ClientDevCard;
import it.polimi.ingsw.view.ClientLeaderCard;
import it.polimi.ingsw.view.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BasicData extends PlayerData {
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


    public BasicData(ArrayList<String> cardID, ArrayList<TurnState> turnStates, TurnState turnState, ArrayList<Resource[]> wareHouse, ArrayList<Resource> strongBox, int faithPoints, int victoryPoints, ArrayList<String> cardsID, ArrayList<String> leadersID, Resource[][] market, ArrayList<String> tableCardsID) {
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

    public ArrayList<TurnState> turnStateFilter(){
        ArrayList<TurnState> tmp = new ArrayList<TurnState>();
        tmp.add(TurnState.CHECK_STATS);
        tmp.add(TurnState.PRODUCE);
        tmp.add(TurnState.BUY_DEV_CARD);
        tmp.add(TurnState.GET_FROM_MARKET);
        tmp.add(TurnState.DISCARD_LEADER_CARD);
        tmp.add(TurnState.QUIT);

        if(turnStates.contains(TurnState.BUY_DEV_CARD) || turnStates.contains(TurnState.PRODUCE) || turnStates.contains(TurnState.GET_FROM_MARKET)){
            tmp.remove(TurnState.PRODUCE);
            tmp.remove(TurnState.BUY_DEV_CARD);
            tmp.remove(TurnState.GET_FROM_MARKET);
            tmp.add(TurnState.END_TURN);
        }

        if(turnStates.contains(TurnState.PLAY_LEADER_CARD)){
            tmp.remove(TurnState.PLAY_LEADER_CARD);
            tmp.remove(TurnState.DISCARD_LEADER_CARD);
        }

        if(turnStates.contains(TurnState.PLAY_LEADER_CARD)){
            tmp.remove(TurnState.PLAY_LEADER_CARD);
            tmp.remove(TurnState.DISCARD_LEADER_CARD);
        }

        if(wareHouse.size() > 0){
            tmp.add(TurnState.MOVE_RESOURCE);
        }

        if(leadersID.size() == 0){
            tmp.remove(TurnState.DISCARD_LEADER_CARD);
        }

        return tmp;

    }

    public ArrayList<String> slotCardsFilter(){
        ArrayList<String> cloned = new ArrayList<String>();
        cloned.addAll(cardsID);
        ArrayList<Resource> allRes = new ArrayList<Resource>();
        ArrayList<MappedResource> mapped = new ArrayList<MappedResource>();
        mapped.addAll(allResources());
        for(MappedResource m : mapped){
            allRes.add(m.getResource());
        }
        ClientDevCard playerCard = new ClientDevCard();
        cloned.removeIf(card -> !allRes.containsAll(playerCard.getRequired()));
        return cloned;
    }

    public ArrayList<MappedResource> allResources(){
        ArrayList<MappedResource> tmp = new ArrayList<MappedResource>();
        Resource[] level = new Resource[3];
        for(Resource[] l : wareHouse) {
            for (Resource w : level){
                MappedResource mappedW = new MappedResource(w, "warehouse");
                tmp.add(mappedW);
            }
        }
        for(Resource s : strongBox){
            MappedResource mappedS = new MappedResource(s, "strongbox");
            tmp.add(mappedS);
        }
        return tmp;
    }

    public ArrayList<MappedResource> createMappedRes(ArrayList<Resource> res){
        ArrayList<MappedResource> tmp = new ArrayList<MappedResource>();
        ArrayList<MappedResource> toSelect = new ArrayList<MappedResource>();
        ArrayList<MappedResource> mappedRes = new ArrayList<MappedResource>();

        //gets all the res
        tmp.addAll(allResources());

        for (Resource re : res) {
            for (MappedResource map : tmp) {
                if (map.getResource().equals(re)) {
                    toSelect.add(map);
                }
            }
            MappedResource selected = printer.printMappedRes(toSelect);
            mappedRes.add(selected);
            tmp.remove(selected);
            toSelect.clear();
        }

        return mappedRes;
    }

    public void removeMappedResource(ArrayList<MappedResource> mapped) {
        for (MappedResource m : mapped) {
            if (m.getPlace().equals("warehouse")) {
                for (Resource[] l : wareHouse) {
                    Resource[] current = l;
                    for (int j = 0; j < current.length; j++)
                        if (current[j].equals(m.getResource())) {
                            current[j] = null;
                        }
                }
            }
            if (m.getPlace().equals("strongbox")) {
                strongBox.remove(m.getResource());
            }
        }
    }

    public ClientDevCard getCardFromID(String cardID){
        ClientDevCard card = new ClientDevCard();
        return card;
    }

    public ClientLeaderCard getLeaderFromID(String cardID){
        ClientLeaderCard leader = new ClientLeaderCard();
        return leader;

    }

    public Resource[][] getMarket(){
        return market;
    }

    public ArrayList<MarketResource> handleWarehouse(ArrayList<Resource> res){
        ArrayList<MarketResource> marketRes = new ArrayList<MarketResource>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        ArrayList<Resource> wareHouseRes = new ArrayList<Resource>();

        for(Resource[] lv : wareHouse){
            wareHouseRes.addAll(Arrays.asList(lv));
        }
        Resource[] level = new Resource[3];
        for(int p = 0; p < res.size(); p++) {
            if (res.get(p).equals(Resource.EMPTY)) {
                ArrayList<Resource> convert = new ArrayList<Resource>();
                convert.add(Resource.GOLD);
                convert.add(Resource.SHIELD);
                convert.add(Resource.STONE);
                convert.add(Resource.SERVANT);
                res.set(p, printer.printResources(convert)); //converts the resource selected on the spot

            }
            if (res.get(p).equals(Resource.FAITH)) {
                MarketResource m = new MarketResource(res.get(p), -1);
                marketRes.add(m);
                p++;
            }

            for(int l = 0; l < wareHouse.size(); l++){
                Resource resource = res.get(p);
                if(Arrays.stream(wareHouse.get(l)).anyMatch(null) && !wareHouseRes.contains(res.get(p))) {
                    tmp.add(l);
                }
                if(!Arrays.stream(wareHouse.get(l)).anyMatch(null) && Arrays.stream(wareHouse.get(l)).anyMatch(x -> x.equals(resource)) && wareHouse.get(l).length < 3){
                    tmp.add(l);
                }
                else{
                    System.out.println("The resource" + "" + res.get(p) + "" + "was discarded");
                }
            }
            int wareHouseLevel = printer.printIntegers(tmp, false);
            MarketResource mr = new MarketResource(res.get(p), wareHouseLevel);
            System.out.println("The resource " + res.get(p) + "" + "was put in level " + wareHouseLevel);
            marketRes.add(mr);
        }
        return marketRes;
    }

    public ArrayList<String> tableCardsFilter(){
        ArrayList<String> cloned = new ArrayList<String>();
        cloned.addAll(tableCardsID);
        ArrayList<Resource> allRes = new ArrayList<Resource>();
        ArrayList<MappedResource> mapped = new ArrayList<MappedResource>();
        mapped.addAll(allResources());
        for(MappedResource m : mapped){
            allRes.add(m.getResource());
        }
        ClientDevCard playerCard = new ClientDevCard();
        cloned.removeIf(card -> !allRes.containsAll(playerCard.getRequired()));
        return cloned;
    }

    public Integer handleSlots(String devID){
        ArrayList<Integer> slots = new ArrayList<Integer>();
        for(int i = 0; i < cardsID.size(); i++){
            if(getCardFromID(cardsID.get(i)).getLevel() < getCardFromID(devID).getLevel()){
                slots.add(i);
            }
            if(cardsID.size() < 3){
                for(int j = i; j < 3 - cardsID.size(); j++){
                    slots.add(j);
                }
            }
        }
        return printer.printIntegers(slots, true);
    }

    public ArrayList<Resource[]> getWareHouse(){
        return wareHouse;
    }

    public int switchLevels(int origin){

        ArrayList<Integer> levels = new ArrayList<Integer>();
        int l = 0;
        for(int i = 0; i < wareHouse.size(); i++){
            if(wareHouse.get(origin).length <= wareHouse.get(i).length){
                levels.add(i);
            }
        }
        return printer.printIntegers(levels, false);
    }

    public ArrayList<String> leaderCardsFilter() {
        ArrayList<String> tmp = new ArrayList<String>();
        ArrayList<MappedResource> mapped = new ArrayList<MappedResource>();
        ArrayList<Resource> allRes = new ArrayList<Resource>();

        mapped.addAll(allResources());

        for (MappedResource m : mapped) {
            allRes.add(m.getResource());
        }

        for (String id : leadersID) {
            ClientLeaderCard leader = getLeaderFromID(id);
            if (allRes.contains(leader.getPrice())) {
                tmp.add(id);
            }
        }
        return tmp;
    }

    public ArrayList<String> getLeaders() {
        return leadersID;
    }
}
