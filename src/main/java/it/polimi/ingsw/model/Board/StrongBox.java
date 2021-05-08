package it.polimi.ingsw.model.Board;

import it.polimi.ingsw.model.Cards.DevCard;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.ResourceNotAvailable;

import java.util.ArrayList;

/**
 * Contains all the resources produced by the player. Together with the {@link WareHouse} is the only place where
 * the {@link it.polimi.ingsw.model.Player.Player} keeps their {@link Resource}s.
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
     * Add {@link Resource} to the {@link StrongBox}.
     *
     * @param res the {@link Resource} to add in the {@link StrongBox}.
     */
    public void addResource(Resource res) { resources.add(res); }

    /**
     * Add {@link Resource}s to the {@link StrongBox}.
     *
     * @param res ArrayList conteining all the {@link Resource}s to add in the {@link StrongBox}.
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
     * @throws ResourceNotAvailable if the {@link Resource} is not available in the {@link StrongBox}.
     */
    public boolean removeResource(Resource res) {
            return resources.remove(res);
    }

    /**
     * Receives an ArrayList of {@link Resource}s and checks if all of them are in the {@link StrongBox}.
     *
     * @param res the ArrayList containing the {@link Resource}s to check.
     * @return true if the {@link StrongBox} contains every {@link Resource} in <em>res</em>, false otherwise.
     */
    public boolean checkAvailability(ArrayList<Resource> res)
    {
        return resources.containsAll(res);
    }


    /**
     * Serviva per controllare se ci fossero le risorse per comprare una carta dal mercato tenendo conto dello sconto, ma forse lo trattiamo in modo diverso.
     *
     * @param res the res
     * @return the boolean
     */
    public boolean checkAvailabilityMarket(Resource res)
    {
        return resources.contains(res);
    }


}
