package model;

import jdk.internal.icu.text.UnicodeSet;

import java.util.ArrayList;

public class StrongBox

{

    ArrayList<Resource> resources = new ArrayList<Resource>();

    //Resource resource;

   /* public Resource getResource()
    {
        return resource;
    }*/

    public void addResource(Resource res)
    {
        resources.add(res);
    }

    public void addResource(ArrayList<Resource> res)
    {
        resources.addAll(res);
    }

    public ArrayList<Resource> getResources()
    {
        return resources;
    }

    public boolean removeResource(Resource res)
    {
        return resources.remove(res);
    }

    public boolean checkAvailability(ArrayList<Resource> res)
    {
        return resources.retainAll(res);
    }


    public boolean checkAvailabilityMarket(Resource res)
    {
        return resources.contains(res);
    }


}
