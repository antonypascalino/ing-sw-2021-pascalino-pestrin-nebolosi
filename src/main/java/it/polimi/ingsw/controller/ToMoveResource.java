package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Table.Resource;

public class ToMoveResource {
    private Resource resource;
    private int ogLevel;
    private int finLevel;


    public ToMoveResource(Resource resource, int ogLevel, int finLevel) {
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
