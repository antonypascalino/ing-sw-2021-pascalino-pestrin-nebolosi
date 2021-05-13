package it.polimi.ingsw.view.data;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

public class BasicData extends PlayerData {
    private ArrayList<TurnState> turnStates;
    private TurnState turnState;
    private ArrayList<Resource[]> wareHouse;
    private ArrayList<Resource> strongBox;
    private int faithPoints;
    private int victoryPoints;
    private ArrayList<String> cardsID;  //3 front cards + basic + extraProd
    private int leaders;

    public BasicData(ArrayList<String> cardID, ArrayList<TurnState> turnStates, TurnState turnState, ArrayList<Resource[]> wareHouse, ArrayList<Resource> strongBox, int faithPoints, int victoryPoints, ArrayList<String> cardsID, int leaders) {
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
        ArrayList<String> cloned = new ArrayList<String>;
        cloned.addAll(cardsID);
        ArrayList<Resource> requiredRes = new ArrayList<Resource>();
        ArrayList<Resource> allRes = new ArrayList<Resource>();
        Card playerCard = new Card(); //il set di carte sul client
        for(String card : cloned){
            requiredRes.addAll(fromIDtoCard(card).required);
            if(!allRes.contains(requiredRes)){
                cloned.remove(card);
            }
        }
        return cloned;
    }
}
