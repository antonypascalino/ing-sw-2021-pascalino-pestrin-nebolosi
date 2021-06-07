package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

/**
 * The type Change Resource player (it extends {@link Player}).
 * It's the player with the change resource leader card.
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
     *
     * Gets from market considering the possibility to change resource
     *
     */
    @Override
    public boolean checkMarketRes(ArrayList<Resource> requestedRes, ArrayList<Resource> marketRes) {
        for (int i = 0; i < marketRes.size(); i++) {
            if (!marketRes.get(i).equals(requestedRes.get(i))) {
                if(!change.contains(requestedRes.get(i))) {
                    return false; //o chiama eccezione
                }
            }
        }
        return true;
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
