package it.polimi.ingsw.Request;

import it.polimi.ingsw.model.Table.Resource;

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
