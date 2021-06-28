package it.polimi.ingsw.Request;

import it.polimi.ingsw.model.Table.Resource;

/**
 * A type containing a {@link Resource} and an int meaning a level of the player's deposits.
 * Used by the player when he takes resource form market to indicate where wants to put them.
 */
public class MarketResource {
    private Resource resource;
    private int level;

    /**
     * Instantiates a new {@link MarketResource} setting the resource and teh level,
     *
     * @param resource the {@link Resource}
     * @param level    the level
     */
    public MarketResource(Resource resource, int level) {
        this.resource = resource;
        this.level = level;
    }

    /**
     * Gets resource.
     *
     * @return the resource
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }
}
