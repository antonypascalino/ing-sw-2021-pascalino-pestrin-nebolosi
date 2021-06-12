package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.MarketDimension;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.Request.MarketRequest;

import java.util.ArrayList;

/**
 * A type having an ArrayList of {@link Resource}s, an {@link MarketDimension} and an index of the market's matrix.
 * It is used by the {@link MarketSelection} to create a {@link MarketRequest} so the server can check if the Resources
 * sent by the players match those ones on the server's Market
 */
public class MarketArray {
    private ArrayList<Resource> resources;
    private MarketDimension dimension;
    private int index;

    /**
     * Instantiates a new Market array.
     *
     * @param resources the resources
     * @param dimension the dimension
     * @param index     the index
     */
    public MarketArray(ArrayList<Resource> resources, MarketDimension dimension, int index) {
        this.resources = resources;
        this.dimension = dimension;
        this.index = index;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public MarketDimension getDimension() {
        return dimension;
    }

    public int getIndex() {
        return index;
    }
}
