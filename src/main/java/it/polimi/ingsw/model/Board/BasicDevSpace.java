package it.polimi.ingsw.model.Board;

import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.ResourceNotAvailable;

import java.util.ArrayList;

/**
 * The type of the Basic Development Space where every player has their Basic Production Power.
 * Has a reference to the player's board where it belongs
 */
public class BasicDevSpace {
    private Board board;

    /**
     * Instantiates a new Basic Development Space adding the references of the board where it belongs.
     *
     * @param board player's board where the Basic Production Power is
     */
    public BasicDevSpace(Board board)
    {
        this.board = board;
    }

    /**
     * Produces a resource using the Basic Production Power
     * player chooses two resources in input to pay and one in output to receive
     *
     * @param res1 the first resource chosen by player to pay
     * @param res2 the second resource chosen by player to pay
     * @return the resource the player chose to receive
     */
//    public Resource use(Resource res1, Resource res2)
//    {
//        ArrayList<Resource> tmp= new ArrayList<Resource>();
//        tmp.add(res1);
//        tmp.add(res2);
//        if(canBeUsed(res1,res2))
//        try
//        {
//               board.removeResources(tmp);
//        }
//        catch (ResourceNotAvailable ex)
//        {
//            System.out.println("Risorsa non disponibile");
//        }
//       return Resource.SHIELD;
//        return View.chooseRes();
//    }

    /**
     * Checks if player has the two chosen resources to pay, uses Board.hasResource
     * to check into all player's resources
     *
     * @param res1 the first resource to check
     * @param res2 the second resource to check
     * @return true if the Basic Power Production can be used using the resources chosen by the player, false otherwise
     */
    public boolean canBeUsed(Resource res1, Resource res2)
    {
        ArrayList<Resource> tmp= new ArrayList<Resource>();
        tmp.add(res1);
        tmp.add(res2);
        return board.hasResources(tmp);
    }
}
