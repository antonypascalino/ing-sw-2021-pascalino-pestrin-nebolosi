package it.polimi.ingsw.model.Board;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

/**
 * Contains all the {@link Resource}s produced by the {@link Player}.
 */
public class StrongBox {
    private ArrayList<Resource> resources;

    /**
     * Instantiates a new {@link StrongBox} creating a new ArrayList of {@link Resource}.
     */
    public StrongBox()
    {
        resources = new ArrayList<Resource>();
    }

    /**
     * Add a single {@link Resource} to the {@link StrongBox}.
     *
     * @param res the {@link Resource} to add in the {@link StrongBox}.
     */
    public void addResource(Resource res) { resources.add(res); }

    /**
     * Add {@link Resource}s to the {@link StrongBox}.
     *
     * @param res ArrayList containing all the {@link Resource}s to add in the {@link StrongBox}.
     */
    public void addResource(ArrayList<Resource> res)
    {
        resources.addAll(res);
    }

    /**
     * Gets the all the {@link Resource}s contained in the {@link StrongBox}.
     *
     * @return an ArrayList containing all the {@link Resource}s in the {@link StrongBox}.
     */
    public ArrayList<Resource> getResources()
    {
        return resources;
    }

    /**
     * Remove a single {@link Resource} from the {@link StrongBox}.
     *
     * @param res the {@link Resource} to be removed.
     * @return true if the {@link Resource} was in the {@link StrongBox} and has been successfully removed from it.
     */
    public boolean removeResource(Resource res) {
            return resources.remove(res);
    }
}
