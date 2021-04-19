package it.polimi.ingsw.model;

/**
 * The type Change res player.
 */
public class ChangeResPlayer {
    /**
     * The Change.
     */
    Resource change;
    /**
     * The Original.
     */
    Player original;


    /**
     * Gets from market.
     */
    @override
            public void getFromMarket()

    {
        if(original.istanceOf(ChangeResPlayer))
            //original.getChange();

        View.chose(this.change(), original.getChange());
    }

    /**
     * Gets change.
     *
     * @return the change
     */
    public Resource getChange()
    {
        return change;
    }
}
