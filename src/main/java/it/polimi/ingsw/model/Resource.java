package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * The enum Resource.
 */
public enum Resource{


    /**
     * Gold resource.
     */
    GOLD,
    /**
     * Shield resource.
     */
    SHIELD,
    /**
     * Servant resource.
     */
    SERVANT,
    /**
     * Stone resource.
     */
    STONE,
    /**
     * Faith resource.
     */
    FAITH,
    /**
     * Empty resource.
     */
    EMPTY,

    /**
     * Choice resource.
     */
    CHOICE;

    private String color;


    public static ArrayList<Resource> cloneList(ArrayList<Resource> list) {
        ArrayList<Resource> clone = new ArrayList<Resource>(list.size());
        for (Resource item : list)
        {
                clone.add((item));
        }
        return clone;
    }

}
