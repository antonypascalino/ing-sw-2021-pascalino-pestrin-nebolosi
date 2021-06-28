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

    public String getCardID() {
        return cardID;
    }
}
