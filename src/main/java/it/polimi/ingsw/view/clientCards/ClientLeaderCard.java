package it.polimi.ingsw.view.clientCards;

import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public abstract class ClientLeaderCard {

    private String leaderID;
    private String power;
//    public ArrayList<Resource> price;
//    public Resource placeable;
//    public int level;
//    private Resource changeRes;
//    public ArrayList<Resource> required;

    public ClientLeaderCard(String leaderID, String power) {
        this.power = power;
        this.leaderID = leaderID;
    }

    public String getLeaderID() {
        return leaderID;
    }

    public abstract boolean canBePlayed(PlayerData data);

//    public ArrayList<Resource> getRequired(){
//        return required;
//    }
//
//    public ArrayList<Resource> getPrice(){
//        return price;
//    }
//
//    public Resource getPlaceable(){
//        return placeable;
//    }
//
//    public int getLevel(){
//        return levl;
//    }
//
//    public Resource getChangeRes(){return changeRes;}

}
