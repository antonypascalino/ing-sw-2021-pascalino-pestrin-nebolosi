package it.polimi.ingsw.view.data;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.MarketResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.view.ClientCard;

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
    private int leaders;
    private Resource[][] market;


    public BasicData(ArrayList<String> cardID, ArrayList<TurnState> turnStates, TurnState turnState, ArrayList<Resource[]> wareHouse, ArrayList<Resource> strongBox, int faithPoints, int victoryPoints, ArrayList<String> cardsID, int leaders, Resource[][] market, ArrayList<String> tableCardsID) {
        this.turnStates = turnStates;
        this.turnState = turnState;
        this.wareHouse = wareHouse;
        this.strongBox = strongBox;
        this.faithPoints = faithPoints;
        this.victoryPoints = victoryPoints;
        this.cardsID = cardsID;
        this.leaders = leaders;
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

        if(leaders == 0){
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
        ClientCard playerCard = new ClientCard();
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
        Scanner inputs = new Scanner(System.in);
        String selection = "";
        ArrayList<MappedResource> tmp = new ArrayList<MappedResource>();
        ArrayList<MappedResource> toSelect = new ArrayList<MappedResource>();
        ArrayList<MappedResource> mappedRes = new ArrayList<MappedResource>();

        //gets all the res
        tmp.addAll(allResources());


        for (Resource re : res) {
            int n = 0;
            for (MappedResource map : tmp) {
                if (map.getResource().equals(re)) {
                    n++;
                    System.out.println("[" + n + "]" + "" + map.getResource() + "" + map.getPlace());
                    toSelect.add(map);
                }
            }
            System.out.println("Enter selection: ");
            selection = inputs.nextLine();
            int index = Integer.parseInt(selection);
            mappedRes.add(toSelect.get(index - 1));
            tmp.remove(toSelect.get(index - 1));
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

    public ClientCard getCardFromID(String cardID){
        ClientCard card = new ClientCard();
        return card;
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

        int myFP = 0;
        int discardedFP = 0;
        Resource[] level = new Resource[3];

        for(int p = 0; p < res.size(); p++) {
            if (res.get(p).equals(Resource.EMPTY)) {
                Scanner inputs = new Scanner(System.in);
                String selection = "";
                ArrayList<Resource> convert = new ArrayList<Resource>();
                convert.add(Resource.GOLD);
                convert.add(Resource.SHIELD);
                convert.add(Resource.STONE);
                convert.add(Resource.SERVANT);

                for (int q = 0; q < convert.size(); q++) {
                    System.out.println("[" + (q + 1) + "]" + "" + convert.get(q));
                }
                System.out.println("Enter selection: ");
                selection = inputs.nextLine();
                int index = Integer.parseInt(selection);

                res.set(index, convert.get(index - 1)); //converts the resource selected on the spot

            }
            if (res.get(p).equals(Resource.FAITH)) {
                myFP++;
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

            for(int a = 0; a < tmp.size(); a++){
                System.out.println("[" + (a + 1) + "]" + "" + tmp.get(a));
            }
            Scanner inputs = new Scanner(System.in);
            String selection = "";

            System.out.println("Enter selection: ");
            selection = inputs.nextLine();
            int index = Integer.parseInt(selection);
            MarketResource mr = new MarketResource(res.get(p), tmp.get(index-1));
            System.out.println("The resource " + res.get(p) + "" + "was put in level " + tmp.get(index-1));
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
        ClientCard playerCard = new ClientCard();
        cloned.removeIf(card -> !allRes.containsAll(playerCard.getRequired()));
        return cloned;
    }

    public Integer handleSlots(String devID){
        ArrayList<Integer> slots = new ArrayList<Integer>();
        Scanner inputs = new Scanner(System.in);
        String selection = "";
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
        for(int s = 0; s < slots.size(); s++){
            System.out.println("[" + (s + 1) + "]" + "" + slots.get(s));
        }
        selection = inputs.nextLine();
        int index = Integer.parseInt(selection);
        return slots.get(index - 1);
    }


}
