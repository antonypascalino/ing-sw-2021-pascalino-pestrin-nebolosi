package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Update;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class GameHub {

    private ArrayList<PlayerData> players;
    private PlayerData currData;
    private Resource[][] market;
    private ArrayList<String> frontTableCardsID;
    private ArrayList<ClientDevCard> clientDevCards;

    public Resource[][] getMarket() {
        return market;
    }

    public ArrayList<String> getFrontTableCardsID() {
       return frontTableCardsID;
    }

    public void refresh(Update update) {
        update.handleUpdate();
    }




    

}
