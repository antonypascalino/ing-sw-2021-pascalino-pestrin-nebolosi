package it.polimi.ingsw.view;


import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

public class ClientDevCard {
    public ArrayList<Resource> required;
    public ArrayList<Resource> price;
    public int level;

    public ArrayList<Resource> getRequired(){
        return required;
    }

    public ArrayList<Resource> getPrice(){
        return price;
    }

    public int getLevel(){
        return level;
    }

}
