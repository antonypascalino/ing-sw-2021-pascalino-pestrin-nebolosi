package it.polimi.ingsw.model.Table;

import java.util.ArrayList;
import it.polimi.ingsw.model.Board.FaithPath;

/**
 * A resource is used in the game by the players whenever they want to buy a development card or produce other resources.
 * The FAITH resource make the players move on their {@link FaithPath}.
 */
public enum Resource {
    GOLD,
    SHIELD,
    SERVANT,
    STONE,
    FAITH,
    EMPTY,
    CHOICE;

    /**
     * Gets all kinds of {@link Resource}s.
     *
     * @param list the list
     * @return an ArrayList with all kinds of {@link Resource}s.
     */
    public static ArrayList<Resource> cloneList(ArrayList<Resource> list) {
        ArrayList<Resource> clone = new ArrayList<Resource>(list.size());
        for (Resource item : list) {
            clone.add((item));
        }
        return clone;
    }
}
