package it.polimi.ingsw.controller;

import java.util.ArrayList;

public class Production {
    private String cardID;
    private ArrayList<MappedResource> resources;

    public String getCardID() {
        return cardID;
    }

    public ArrayList<MappedResource> getResources() {
        return resources;
    }
}
