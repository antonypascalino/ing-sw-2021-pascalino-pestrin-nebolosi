package model;

import java.util.ArrayList;

public class StrongBox

{

    ArrayList<Resource> resources;

    public StrongBox()
    {
        resources = new ArrayList<Resource>();
    }

    public void addResource(Resource res)
    {
        resources.add(res);
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
