package it.polimi.ingsw.model.Board;

import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

/**
 * The type of the Basic Development Space where every player has their Basic Production Power.
 * Has a reference to the player's board where it belongs
 */
public class BasicDevSpace {
    private String cardID;
    private Board board;

    /**
     * Instantiates a new Basic Development Space adding the references of the board where it belongs.
     *
     * @param board player's board where the Basic Production Power is
     */
    public BasicDevSpace(Board board)
    {
        this.cardID = "BASIC";
        this.board = board;
    }

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

    public String getCardID() {
        return cardID;
    }
}
