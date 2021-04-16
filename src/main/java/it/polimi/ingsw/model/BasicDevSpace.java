package it.polimi.ingsw.model;

import java.util.ArrayList;

public class BasicDevSpace {

    private Board br;
    public BasicDevSpace(Board board)
    {
        br = board;
    }

    public Resource use(Resource res1, Resource res2)
    {
        ArrayList<Resource> tmp= new ArrayList<Resource>();
        tmp.add(res1);
        tmp.add(res2);
        //if(canBeUsed(res1,res2))
        try
        {
                br.removeResources(tmp);
        }
        catch (ResourceNotAvaible ex)
        {
            System.out.println("Risorsa non disponibile");
        }
       return Resource.SHIELD;
        //return View.chooseRes();
    }

    public boolean canBeUsed(Resource res1, Resource res2)
    {
        ArrayList<Resource> tmp= new ArrayList<Resource>();
        tmp.add(res1);
        tmp.add(res2);
        return br.hasResources(tmp);
    }
}