package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * The type Strong box.
 */
public class StrongBox

{

    /**
     * The Resources.
     */
    ArrayList<Resource> resources;


    //Resource resource;

   /* public Resource getResource()
    {
        return resource;
    }*/

    /**
     * Instantiates a new Strong box.
     */
    public StrongBox()
    {
        resources = new ArrayList<Resource>();
    }

    /**
     * Add resource.
     *
     * @param res the res
     */
    public void addResource(Resource res)
    {
        resources.add(res);
    }

    /**
     * Add resource.
     *
     * @param res the res
     */
    public void addResource(ArrayList<Resource> res)
    {
        resources.addAll(res);
    }

    /**
     * Gets resources.
     *
     * @return the resources
     */
    public ArrayList<Resource> getResources()
    {
        return resources;
    }

    /**
     * Remove resource boolean.
     *
     * @param res the res
     * @return the boolean
     * @throws ResourceNotAvaible the resource not avaible
     */
    public boolean removeResource(Resource res) throws ResourceNotAvaible
    {
        if (checkAvailability(resources) == true)
            return resources.remove(res);
        else
            throw new ResourceNotAvaible();
    }

    /**
     * Check availability boolean.
     *
     * @param res the res
     * @return the boolean
     */
    public boolean checkAvailability(ArrayList<Resource> res)
    {
        return resources.retainAll(res);
    }


    /**
     * Check availability market boolean.
     *
     * @param res the res
     * @return the boolean
     */
    public boolean checkAvailabilityMarket(Resource res)
    {
        return resources.contains(res);
    }


}
