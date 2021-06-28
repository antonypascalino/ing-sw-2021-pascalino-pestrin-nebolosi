package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.card.ExtraDeposit;

import java.util.ArrayList;

/**
 * A type having an ArrayList of {@link Resource}s and the only resource placeable in it.
 * Used by the {@link ExtraDeposit}.
 */
public class ExtraDepositLevel {

    private Resource placeable;
    private ArrayList<Resource> extraLevel;

    /**
     * Instantiates a new {@link ExtraDepositLevel} setting the placeable {@link Resource}.
     *
     * @param placeable the placeable
     */
    public ExtraDepositLevel(Resource placeable) {
        this.placeable = placeable;
        this.extraLevel = new ArrayList<>();
        extraLevel.add(Resource.EMPTY);
        extraLevel.add(Resource.EMPTY);
    }

    /**
     * Gets placeable resource.
     *
     * @return the resource.
     */
    public Resource getPlaceable() {
        return placeable;
    }

    /**
     * Gets the {@link Resource}s in this level.
     *
     * @return the resources
     */
    public ArrayList<Resource> getResources() {
        return extraLevel;
    }

    /**
     * Add a {@link Resource} to the level.
     *
     * @param res the res
     */
    public void addResource(Resource res) {
        if (res.equals(placeable)) {
            for (Resource r : extraLevel)
                if (r.equals(Resource.EMPTY)) {
                    extraLevel.set(extraLevel.indexOf(r), res);
                    break;
                }
        }
    }

    /**
     * Remove a {@link Resource} from the level.
     *
     * @param res the res
     */
    public void removeResource(Resource res) {
        if (res.equals(placeable)) {
            extraLevel.remove(res);
            extraLevel.add(Resource.EMPTY);
        }
    }
}
