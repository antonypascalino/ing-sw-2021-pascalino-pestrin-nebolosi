package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.MarketDimension;
import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

public class MarketArray {
    private ArrayList<Resource> resources;
    private MarketDimension dimension;
    private int index;

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
