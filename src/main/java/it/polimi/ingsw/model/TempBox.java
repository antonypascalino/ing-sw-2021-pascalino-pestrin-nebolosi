package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * The type Temp box.
 */
public class TempBox
{
    /**
     * The Temp res.
     */
    ArrayList<Resource> tempRes;
    /**
     * The Sb.
     */
    StrongBox sb;

    /**
     * Instantiates a new Temp box.
     *
     * @param strongBox the strong box
     */
    public TempBox(StrongBox strongBox)
    {
        tempRes = new ArrayList<Resource>();
        sb = strongBox;
    }

    /**
     * Add resource.
     *
     * @param res the res
     */
//adds the resource to an ArrayList
    public void addResource(Resource res)
    {
        tempRes.add(res);
    }


    /**
     * End turn.
     */
//puts the resources back to the strongbox
    public void endTurn()
    {
        sb.addResource(tempRes);
        tempRes.clear();
    }
}

