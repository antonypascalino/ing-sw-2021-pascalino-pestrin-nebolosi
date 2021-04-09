package model;

import java.util.ArrayList;

public class TempBox
{
    ArrayList<Resource> tempRes;
    StrongBox sb;

    public TempBox(StrongBox strongBox)
    {
        tempRes = new ArrayList<Resource>();
        sb = strongBox;
    }

    //adds the resource to an ArrayList
    public void addResource(Resource res)
    {
        tempRes.add(res);
    }


    //puts the resources back to the strongbox
    public void endTurn()
    {
        sb.addResource(tempRes);
        tempRes.clear();
    }
}

