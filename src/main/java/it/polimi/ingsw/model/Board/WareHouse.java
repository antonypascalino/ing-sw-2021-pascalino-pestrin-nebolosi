package it.polimi.ingsw.model.Board;

import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.card.LeaderCard;
import it.polimi.ingsw.model.Player.Player;
//import it.polimi.ingsw.model.ResourceNotAvailable;
import it.polimi.ingsw.model.card.ExtraDeposit;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains all the resources gained by the {@link Player} from the Market.
 * Together with the {@link StrongBox} is the only place where
 * the Player keeps their {@link Resource}s.
 */
public class WareHouse {
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
    public WareHouse() {
        levels = new ArrayList<Resource[]>();
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
    public ArrayList<Resource[]> getLevels() {
        return levels;
    }

    /**
     * Add a single {@link Resource} in the chosen level.
     * <p>
     * This action is safe-guaranteed by {@link #checkSpace(int, Resource)} previously called.
     *
     * @param level the level in which it wants add the the {@link Resource}.
     * @param res the {@link Resource} to add.
     * @return true if
     */
    public boolean addResource(int level, Resource res) {
        if(checkSpace(level, res)) {
            Resource[] current;
            current = levels.get(level);
            //Add the resource in the first empty space of the array
            for (int i = 0; i < current.length; i++) {
                if (current[i] == null) {
                    current[i] = res;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets all the {@link Resource}s contained in the {@link WareHouse} in every level.
     *
     * @return an ArrayList containing all the {@link Resource}s
     */
    public ArrayList<Resource> getResources() {
        ArrayList<Resource> result = new ArrayList<Resource>();
        Resource[] currentLevel;
        for (int i = 0; i < levels.size(); i++) {
            currentLevel = levels.get(i);
            for (int j = 0; j < currentLevel.length; j++)
                if (currentLevel[j] != null)
                    result.add(currentLevel[j]);
        }
        return result;
    }

    /**
     * Remove a single {@link Resource} from the {@link WareHouse}.
     *
     * @param res the {@link Resource} to remove.
     * @return true if the remove was successful, false if somehow there's an error.
     //* @throws ResourceNotAvailable if the {@link Resource} is not available.
     */
    public Boolean removeResource(Resource res) {
        if (!checkAvailability(res))
            return false;

        Resource[] currentLevel;
        for (Resource[] level : levels) {
            currentLevel = level;
            for (int j = 0; j < currentLevel.length; j++)
                if (currentLevel[j].equals(res)) {
                    currentLevel[j] = null;
                    return true;
                }
        }
        return false;
    }

    /**
     * Check if a single {@link Resource} is available in the {@link WareHouse}.
     *
     * @param res the {@link Resource} to check.
     * @return true if the {@link Resource} is available in the {@link WareHouse}, false otherwise.
     */
    public boolean checkAvailability(Resource res) {
        return this.getResources().contains(res);
    }

    /**
     * Check if a list of {@link Resource}s is available in the {@link WareHouse}.
     *
     * @param resources the ArrayList with all the {@link Resource}s to check.
     * @return true if all the {@link Resource}s in the ArrayList are available in the {@link WareHouse}, false if even
     * a single {@link Resource} misses.
     */
    public boolean checkAvailability(ArrayList<Resource> resources) {
        return this.getResources().containsAll(resources);
    }


    /**
     * Check if the single {@link Resource} can be placed in the {@link WareHouse}'s level according to its rules.
     *
     * @param level the level where it wants to add the {@link Resource}.
     * @param res the {@link Resource} it wants to add in the {@link WareHouse}.
     * @return true if the {@link Resource} can be added in the wanted level, false otherwise.
     */
    public boolean checkSpace(int level, Resource res) {
        Resource[] currentLevel;
        for (int i = 0; i < 2; i++) {
            currentLevel = levels.get(i);

            //If the level doesn't contain any empty space
            if (i == level && !(Arrays.stream(currentLevel).anyMatch(null)))
                return false;
            for (int j = 0; j < currentLevel.length; j++) {
                //If there's the same resource on another level
                if (currentLevel[j].equals(res) && i != level)
                    return false;
                //If the level contains different resources
                if (i == level && currentLevel[j] != null && !currentLevel[j].equals(res))
                    return false;
            }
        }
        return true;
    }

    public void switchLevels(Resource resource, int originLevel, int destLevel ) {
           this.removeResource(resource);
           this.addResource(destLevel, resource);
    }
}