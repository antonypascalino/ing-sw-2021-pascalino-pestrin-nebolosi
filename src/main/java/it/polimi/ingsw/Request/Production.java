package it.polimi.ingsw.Request;

import java.util.ArrayList;

/**
 * A type containing an ID of a card that can produce resources and an ArrayList of {@link MappedResource}s.
 */
public class Production {
    private String cardID;
    private ArrayList<MappedResource> resources;

    /**
     * Instantiates a new {@link Production} setting all the {@link MappedResource}s and the card's ID.
     *
     * @param resources the resources
     * @param cardID    the card id
     */
    public Production(ArrayList<MappedResource> resources, String cardID)
    {
        this.cardID = cardID;
        this.resources = resources;
    }

    /**
     * Gets card's ID.
     *
     * @return the card id
     */
    public String getCardID() {
        return cardID;
    }

    /**
     * Gets all the {@link MappedResource}s.
     *
     * @return the mapped resources
     */
    public ArrayList<MappedResource> getMappedResources() {
        return resources;
    }

}
