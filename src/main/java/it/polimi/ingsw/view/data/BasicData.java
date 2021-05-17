package it.polimi.ingsw.view.data;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.view.ClientCard;

import java.util.ArrayList;
import java.util.Scanner;

public class BasicData extends PlayerData {
    private ArrayList<TurnState> turnStates;
    private TurnState turnState;
    private ArrayList<Resource> wareHouse;
    private ArrayList<Resource> strongBox;
    private int faithPoints;
    private int victoryPoints;
    private ArrayList<String> cardsID;  //3 front cards + basic + extraProd
    private int leaders;

    public BasicData(ArrayList<String> cardID, ArrayList<TurnState> turnStates, TurnState turnState, ArrayList<Resource> wareHouse, ArrayList<Resource> strongBox, int faithPoints, int victoryPoints, ArrayList<String> cardsID, int leaders) {
        this.turnStates = turnStates;
        this.turnState = turnState;
        this.wareHouse = wareHouse;
        this.strongBox = strongBox;
        this.faithPoints = faithPoints;
        this.victoryPoints = victoryPoints;
        this.cardsID = cardsID;
        this.leaders = leaders;
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

    public ArrayList<String> cardsFilter(){
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
        for(Resource w : wareHouse){
            MappedResource mappedW = new MappedResource(w, "warehouse");
            tmp.add(mappedW);
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
        ArrayList<MappedResource> mapped = new ArrayList<MappedResource>();
        ArrayList<Resource> counter = new ArrayList<Resource>();
        int size = res.size();

        tmp.addAll(allResources());

        for(MappedResource t : tmp){
            if(res.contains(t.getResource())){
                mapped.add(t);
            }

        }

        tmp.clear();

        do{
            for(int i = 0; i < mapped.size(); i++){
                System.out.println("[" + (i + 1) + "]" + "" + mapped.get(i));
            }
            System.out.println("Enter selection: ");
            selection = inputs.nextLine();
            int index = Integer.parseInt(selection);
            tmp.add(mapped.get(index-1));
            counter.add(mapped.get(index-1).getResource());
            mapped.remove(mapped.get(index-1));
            //manca un solo controllo riguardo al fatto che se finisco per
            //esempio il numero di ori, mi deve togliere da mapped automaticamente
            //tutti gli altri ori
            size--;

        }while(size>0);

        return tmp;
    }

    public void removeMappedResource(ArrayList<MappedResource> mapped){
        for(MappedResource m : mapped){
            if(m.getPlace().equals("warehouse")){
                wareHouse.remove(m.getResource());
            }
            if(m.getPlace().equals("strongbox")){
                strongBox.remove(m.getResource());
            }
        }
    }

    public ClientCard getCardFromID(String cardID){
        ClientCard card = new ClientCard();
        return card;
    }

}
