package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Resource;

public class MarketResource {
    private Resource resource;
    private int level;

    public MarketResource(Resource resource, int level) {
        this.resource = resource;
        this.level = level;
    }

    public Resource getResource() {
        return resource;
    }

    public int getLevel() {
        return level;
    }

}
