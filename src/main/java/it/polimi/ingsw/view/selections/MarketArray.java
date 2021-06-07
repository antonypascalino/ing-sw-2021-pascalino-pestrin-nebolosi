package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.MarketDimension;
import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

/**
 * The type Market array.
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

    /**
     * Gets resources.
     *
     * @return the resources
     */
    public ArrayList<Resource> getResources() {
        return resources;
    }

    /**
     * Gets dimension.
     *
     * @return the dimension
     */
    public MarketDimension getDimension() {
        return dimension;
    }

    /**
     * Gets index.
     *
     * @return the index
     */
    public int getIndex() {
        return index;
    }
}
