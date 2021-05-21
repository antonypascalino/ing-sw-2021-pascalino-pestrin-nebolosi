package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Table.Resource;

public class MappedResource {
    private Resource resource;
    private String place;

    public MappedResource(Resource resource, String place) {
        this.resource = resource;
        this.place = place;
    }

    public Resource getResource() {
        return resource;
    }

    public String getPlace() {
        return place;
    }
}
