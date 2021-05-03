package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Resource;

public class toMoveResource extends WarehouseResource{
    private int ogLevel;
    private int finLevel;

    public toMoveResource(Resource resource, int ogLevel, int finLevel) {
        this.resource = resource;
        this.ogLevel = ogLevel;
        this.finLevel = finLevel;
    }

    public Resource getResource() {
        return resource;
    }

    public int getOgLevel() {
        return ogLevel;
    }

    public int getFinLevel() {
        return finLevel;
    }
}
