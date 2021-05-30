package it.polimi.ingsw.Request;

import java.util.ArrayList;

public class Production {
    private String cardID;
    private ArrayList<MappedResource> resources;

    public Production(ArrayList<MappedResource> resources, String cardID)
    {
        this.cardID = cardID;
        this.resources = resources;
    }
    public String getCardID() {
        return cardID;
    }

    public ArrayList<MappedResource> getMappedResources() {
        return resources;
    }

}
