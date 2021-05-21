package it.polimi.ingsw.model.card;

import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

public class BasicProduction {
    private String cardID;

    ArrayList<Resource> requires;
    private ArrayList<Resource> produces;

    public BasicProduction(String id, ArrayList<Resource> req, ArrayList<Resource> pro) {
        cardID = id;
        requires = req;
        produces = pro;
    }

}
