package it.polimi.ingsw.model.Board;

import it.polimi.ingsw.model.Board.Board;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.ResourceNotAvaible;

import java.util.ArrayList;

/**
 * The type Basic dev space.
 */
public class BasicDevSpace {

    private Board br;

    /**
     * Instantiates a new Basic dev space.
     *
     * @param board the board
     */
    public BasicDevSpace(Board board)
    {
        br = board;
    }

    /**
     * Use resource.
     *
     * @param res1 the res 1
     * @param res2 the res 2
     * @return the resource
     */
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

    /**
     * Can be used boolean.
     *
     * @param res1 the res 1
     * @param res2 the res 2
     * @return the boolean
     */
    public boolean canBeUsed(Resource res1, Resource res2)
    {
        ArrayList<Resource> tmp= new ArrayList<Resource>();
        tmp.add(res1);
        tmp.add(res2);
        return br.hasResources(tmp);
    }
}
