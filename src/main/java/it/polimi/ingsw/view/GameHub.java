package it.polimi.ingsw.view;


import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Updates.Update;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class GameHub {

    private ArrayList<TurnState> turnStates;
    private ArrayList<PlayerData> players;
    private PlayerData currData;
    private Resource[][] market;
    private ArrayList<String> frontTableCardsID;
    private ArrayList<ClientDevCard> clientDevCards;

    public Resource[][] getMarket() {
        return market;
    }

    public GameHub(ArrayList<TurnState> turnStates, ArrayList<PlayerData> players, PlayerData currData, Resource[][] market, ArrayList<String> frontTableCardsID, ArrayList<ClientDevCard> clientDevCards) {
        this.players = players;
        this.currData = currData;
        this.market = market;
        this.frontTableCardsID = frontTableCardsID;
        this.clientDevCards = clientDevCards;
        this.turnStates = turnStates;
    }

    public ArrayList<String> getFrontTableCardsID() {
       return frontTableCardsID;
    }


    public void setPlayers(ArrayList<PlayerData> players) {
        this.players = players;
    }

    public void setCurrData(PlayerData currData) {
        this.currData = currData;
    }

    public void setMarket(Resource[][] market) {
        this.market = market;
    }

    public void setFrontTableCardsID(ArrayList<String> frontTableCardsID) {
        this.frontTableCardsID = frontTableCardsID;
    }


    public ArrayList<PlayerData> getPlayers() {
        return players;
    }

    public PlayerData getCurrData() {
        return currData;
    }

    public void refresh(Update update) {
        update.handleUpdate(this);
    }




    

}
