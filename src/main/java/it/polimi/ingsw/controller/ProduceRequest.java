package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;


/**
 * When the player wants to produce with a card (DevCard, ExtraProdCard or BasicDevCard) sends a ProduceRequest for each
 * card he wants to use indicating the cardID and the {@link Resource}s with the place where to get them.
 * <p>
 * Because of the player could produce using more then one card, he will send an ArrayList of ProduceRequest.
 */
public class ProduceRequest {
    private String cardID;
    private ArrayList<MappedResource> mappedResources;

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public ArrayList<MappedResource> getMappedResource() {
        return mappedResources;
    }

    public void addMappedResource(MappedResource mappedResource) {
        mappedResources.add(mappedResource);
    }

}
