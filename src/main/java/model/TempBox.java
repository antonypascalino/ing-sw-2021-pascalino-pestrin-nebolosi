package model;

import java.util.ArrayList;

public class TempBox
{
    ArrayList<Resource> tempRes = new ArrayList<Resource>();
   // private Resource resource;
    StrongBox sb = new StrongBox();


    //adds the resource to an ArrayList
    public void addResource(Resource res)
    {
        tempRes.add(res);
    }


    //puts the resources back to the strongbox
    public void endTurn()
    {
        sb.resources.addAll(tempRes);
    }
}

