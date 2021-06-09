package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

public class ExtraDepositLevel {

    private Resource placeable;
    private ArrayList<Resource> extraLevel;

    public ExtraDepositLevel(Resource placeable) {
        this.placeable = placeable;
        this.extraLevel = new ArrayList<>();
        extraLevel.add(Resource.EMPTY);
        extraLevel.add(Resource.EMPTY);
    }

    public Resource getPlaceable() {
        return placeable;
    }

    public ArrayList<Resource> getResources() {
        return extraLevel;
    }

    public void addResource(Resource res) {
        if (res.equals(placeable)) {
            for(Resource r : extraLevel)
                if(r.equals(Resource.EMPTY)) {
                    extraLevel.set(extraLevel.indexOf(r), res);
                    break;
                }
        }
    }

    public void removeResource(Resource res) {
        if (res.equals(placeable)) {
            extraLevel.remove(res);
            extraLevel.add(Resource.EMPTY);
        }
    }

}
