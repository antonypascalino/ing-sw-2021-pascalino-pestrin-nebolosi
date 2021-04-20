package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

/**
 * The type Change res player.
 */
public class ChangeResPlayer extends Player{
    /**
     * The Possible resource in which a white resource can be turned into
     */
    ArrayList<Resource> change;

    /**
     * The Original.
     *
     * @param or  the or
     * @param res the res
     */

    public ChangeResPlayer(Player or, Resource res)
    {
        change= new ArrayList<Resource>();
        change.add(res);
        original = or;
        if(original instanceof ChangeResPlayer)
        {
            ChangeResPlayer sup=(ChangeResPlayer)original;
            change.addAll(sup.getChange());
        }
    }

    /**
     * @override
     * Gets from market considering the possibility to change resource
     *
     */
    public void getFromMarket()
    {



    }

    /**
     * Gets change.
     *
     * @return the change
     */
    public ArrayList<Resource> getChange()
    {
        return change;
    }
}
