package it.polimi.ingsw.controller.Request;

import it.polimi.ingsw.model.Table.Resource;

/**
 * A type containing a {@link Resource} and a String meaning a place from where take the resource (Warehouse, strongbox,...).
 * Used by the player when he has to use resources for any reason.
 */
public class MappedResource {
    private Resource resource;
    private String place;

    /**
     * Instantiates a new {@link MappedResource}.
     *
     * @param resource the resource
     * @param place    the place
     */
    public MappedResource(Resource resource, String place) {
        this.resource = resource;
        this.place = place;
    }

    /**
     * Gets the resource.
     *
     * @return the resource
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Gets the place.
     *
     * @return the place
     */
    public String getPlace() {
        return place;
    }
}
