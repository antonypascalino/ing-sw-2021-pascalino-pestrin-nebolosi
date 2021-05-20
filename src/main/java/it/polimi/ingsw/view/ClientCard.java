package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

public class ClientCard {
    public ArrayList<Resource> required;
    public int level;

    public ArrayList<Resource> getRequired(){
        return required;
    }

    public int getLevel(){
        return level;
    }

}
