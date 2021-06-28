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
        ArrayList<Resource> clone = new ArrayList<>(list.size());
        clone.addAll(list);
        return clone;
    }

    /**
     * Used to pretty print the resources in the CLI.
     *
     * @return the string to print.
     */
    public String inLine() {
        if (this == GOLD) return "GOLD   ";
        else if (this == STONE) return "STONE  ";
        else if (this == SHIELD) return "SHIELD ";
        else if (this == SERVANT) return "SERVANT";
        else if (this == EMPTY) return "EMPTY  ";
        else if (this == FAITH) return "FAITH  ";
        return null;
    }
}
