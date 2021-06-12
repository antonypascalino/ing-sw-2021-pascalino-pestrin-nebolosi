package it.polimi.ingsw.model.card;

import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

/**
 * The Basic production power which every {@link Player} has.
 */
public class BasicProduction {
    private String cardID;
    private ArrayList<Resource> requires;
    private ArrayList<Resource> produces;

    /**
     * Instantiates a new Basic production.
     *
     * @param id  the Basic Production's ID
     * @param req the req
     * @param pro the pro
     */
    public BasicProduction(String id, ArrayList<Resource> req, ArrayList<Resource> pro) {
        cardID = id;
        requires = req;
        produces = pro;
    }

}
