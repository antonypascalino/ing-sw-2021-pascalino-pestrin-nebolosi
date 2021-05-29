package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

public class ClientLeaderCard {
    public ArrayList<Resource> required;
    public ArrayList<Resource> price;
    public Resource placeable;
    public int level;
    private Resource changeRes;

    public ArrayList<Resource> getRequired(){
        return required;
    }

    public ArrayList<Resource> getPrice(){
        return price;
    }

    public Resource getPlaceable(){
        return placeable;
    }

    public int getLevel(){
        return level;
    }

    public Resource getChangeRes(){return changeRes;}

}
