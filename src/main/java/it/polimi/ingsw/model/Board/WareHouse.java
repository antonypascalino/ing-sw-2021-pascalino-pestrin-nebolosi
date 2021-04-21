package it.polimi.ingsw.model.Board;

import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Cards.LeaderCard;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.ResourceNotAvailable;
import it.polimi.ingsw.model.Cards.ExtraDeposit;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains all the resources gained by the {@link Player} from the Market.
 * Together with the {@link StrongBox} is the only place where
 * the Player keeps their {@link Resource}s.
 */
public class WareHouse
{
    private Resource level1[];
    private Resource level2[];
    private Resource level3[];
    /**
     * The various sections of the {@link WareHouse} where the {@link Resource}s from Market
     * can be put. Them can be expanded from {@link ExtraDeposit} card,
     * one of the {@link LeaderCard}s.
     */
    ArrayList<Resource[]> levels; //Contains all the possible levels, including the leader cards ones.


    /**
     * Instantiates a new WareHouse (with only 3 levels)
     */
    public WareHouse()
    {
        level1 = new Resource[1];
        level2 = new Resource[2];
        level3 = new Resource[3];
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
    }

    /**
     * Get all the Levels currently in the {@link WareHouse};
     *
     * @return an ArrayList with the {@link WareHouse}'s levels
     */
    public ArrayList<Resource[]> getLevels(){
        return levels;
    }

    /**
     * Add a single {@link Resource} in the chosen level. This action is safe-guaranteed by {@link #checkAvailability(Resource)}
     * and {@link #checkSpace(int, Resource)} previously called.
     *
     * @param level the level in which it wants add the the {@link Resource}.
     * @param res the {@link Resource} to add.
     */
    public void addResource(int level, Resource res)
    {
        Resource[] current;
        current = levels.get(level);
        //Add the resource in the first empty space of the array
        for (int i = 0; i<current.length ; i++)
        {
            if(current[i]==null)
            {
                current[i]=res;
                break;
            }
        }
    }

    /**
     * Gets resources.
     *
     * @return the resources
     */
//doesn't remove resource, it only gives all the resources
    public ArrayList<Resource> getResources()
    {
        ArrayList<Resource> result=new ArrayList<Resource>();
        Resource[] currentLevel;
        for (int i = 0; i<levels.size(); i++)
        {
            currentLevel = levels.get(i);
            for (int j = 0; j < currentLevel.length; j++)
                if(currentLevel[j] != null)
                    result.add(currentLevel[j]);
        }

        return result;
    }

    /**
     * Remove resource boolean.
     *
     * @param res the res
     * @return the boolean
     * @throws ResourceNotAvailable the resource not avaible
     */
//remove the resource
    public Boolean removeResource(Resource res) throws ResourceNotAvailable
    {
        //If the resource cannot be removed throw an exception
        if(!checkAvailability(res))
            throw new ResourceNotAvailable();

        //Else return true after removing the first occurancy of the resource
        Resource[] currentLevel;
        for (int i = 0; i<levels.size(); i++)
        {
            currentLevel = levels.get(i);
            for (int j = 0; j < currentLevel.length; j++)
                if(currentLevel[j].equals(res))
                {
                    currentLevel[j]=null;
                    return true;
                }

        }

        //If somehow there's an error return false
        return false;
    }

    /**
     * Check availability boolean.
     *
     * @param res the res
     * @return the boolean
     */
    public Boolean checkAvailability(Resource res)
    {
        if(this.getResources().contains(res))
            return true;
        else
            return false;
    }

    /**
     * Check availability boolean.
     *
     * @param resources the resources
     * @return the boolean
     */
    public Boolean checkAvailability(ArrayList<Resource> resources)
    {
        //First remove, if there's any, the resource rappresenting the discount
        resources.removeAll(discount);

        //Check if the warehouse contains the elements in any order.
        if(this.getResources().containsAll(resources))
            return true;
        else
            return false;
    }

    /**
     * Check space boolean.
     *
     * @param level the level
     * @param res   the res
     * @return the boolean
     */
    public Boolean checkSpace(int level, Resource res)
    {
        Resource[] currentLevel;
        for(int i=0; i < levels.size(); i++)
        {
            currentLevel=levels.get(i);
            //If the level doesn't contain any empty space
            if(i==level && !(Arrays.stream(currentLevel).anyMatch(null)))
                return false;

            for( int j=0 ; j< currentLevel.length; j++)
            {
                //If there's the same resource on another level
                if(currentLevel[j].equals(res) && i != level)
                    return false;
                //If the level contains different resources
                if(i==level && currentLevel[j]!= null && !currentLevel[j].equals(res))
                    return false;
            }
        }
        return true;
    }


}