package model;

import java.util.ArrayList;

public class TempBox
{
    ArrayList<Resource> tempRes = new ArrayList<Resource>();
    private Resource resource;

    public Resource getResource()
    {
        return resource;
    }
    //adds the resource to an ArrayList
    public void addResource(Resource res)
    {
        tempRes.add(res);
    }


    //puts the resources back to the strongbox
    public void endTurn()
    {
        StrongBox.addResources

    }
}

