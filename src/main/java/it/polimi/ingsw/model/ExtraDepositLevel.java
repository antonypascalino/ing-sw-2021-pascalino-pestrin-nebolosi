package it.polimi.ingsw.model;

import java.util.ArrayList;

public class ExtraDepositLevel {

    private Resource placeable;
    private ArrayList<Resource> extraLevel;

    public ExtraDepositLevel(Resource placeable) {
        this.placeable = placeable;
        this.extraLevel = new ArrayList<Resource>();
    }

    public Resource getPlaceable() {
        return placeable;
    }

    public ArrayList<Resource> getExtraLevel() {
        return extraLevel;
    }
}
