package it.polimi.ingsw.model.Board;

import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Table.Market;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Contains the {@link Resource}s gained by the {@link Player} from the {@link Market}.
 */
public class WareHouse {
    private Resource[] level1;
    private Resource[] level2;
    private Resource[] level3;
    private ArrayList<Resource[]> levels; //Contains the 3 levels of the Warehouse.


    /**
     * Instantiates a new WareHouse (with only 3 levels) and fills them with EMPTY {@link Resource}s.
     */
    public WareHouse() {
        levels = new ArrayList<>();
        level1 = new Resource[1];
        level2 = new Resource[2];
        level3 = new Resource[3];
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        level1[0]=Resource.EMPTY;
        level2[0]=Resource.EMPTY;
        level2[1]=Resource.EMPTY;
        level3[0]=Resource.EMPTY;
        level3[1]=Resource.EMPTY;
        level3[2]=Resource.EMPTY;
    }

    /**
     * Get all the levels in the {@link WareHouse}.
     *
     * @return an ArrayList with the {@link WareHouse}'s levels.
     */
    public ArrayList<Resource[]> getLevels() {
        return levels;
    }

    /**
     * Add a single {@link Resource} in the chosen level.
     * <p>
     *
     * @param level the level in the {@link WareHouse} in which it wants to add the the {@link Resource}.
     * @param res the {@link Resource} to add.
     * @return true if the {@link Resource} was actually added, false otherwise.
     */
    public boolean addResource(int level, Resource res) {
        if(checkSpace(level, res)) {
            Resource[] current;
            current = levels.get(level);
            //Add the resource in the first empty space of the array
            for (int i = 0; i < current.length; i++) {
                if (current[i] == Resource.EMPTY) {
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
        ArrayList<Resource> result = new ArrayList<>();
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
     */
    public boolean removeResource(Resource res) {
        if (!checkAvailability(res)) {
            return false;
        }
        Resource[] currentLevel;
        for (Resource[] level : levels) {
            currentLevel = level;
            for (int j = currentLevel.length - 1; j >= 0; j--)
                if (currentLevel[j].equals(res)) {
                    currentLevel[j] = Resource.EMPTY;
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
     * Check if the single {@link Resource} can be placed in the {@link WareHouse}'s level according to its rules.
     *
     * @param level the level where it wants to add the {@link Resource}.
     * @param res the {@link Resource} it wants to add in the {@link WareHouse}.
     * @return true if the {@link Resource} can be added in the wanted level, false otherwise.
     */
    public boolean checkSpace(int level, Resource res) {
        Resource[] currentLevel;
        for (int i = 0; i <= 2; i++) {
            currentLevel = levels.get(i);

            //If the level doesn't contain any empty space
            if (i == level && Arrays.stream(currentLevel).noneMatch(x -> x.equals(Resource.EMPTY)))
                return false;
            for (int j = 0; j < currentLevel.length; j++) {
                //If there's the same resource on another level
                if (currentLevel[j].equals(res) && i != level)
                    return false;
                //If the level contains different resources
                if (i == level && currentLevel[j] != Resource.EMPTY && !currentLevel[j].equals(res))
                    return false;
            }
        }
        return true;
    }


    /**
     * Move the {@link Resource}s from a level to an other in the {@link WareHouse}.
     *
     * @param originLevel the level from which the movement starts.
     * @param destLevel   the level from which the movement ends.
     */
    public void switchLevels(int originLevel, int destLevel ) {
        ArrayList<Resource> helper = new ArrayList<>();
        helper.addAll(Arrays.asList(levels.get(destLevel)));
        Arrays.fill(levels.get(destLevel), Resource.EMPTY);
        for (int j = 0; j < levels.get(originLevel).length; j++) {
            if (!levels.get(originLevel)[j].equals(Resource.EMPTY)) { //needed to check levels length
                levels.get(destLevel)[j] = levels.get(originLevel)[j];
            }
        }
        Arrays.fill(levels.get(originLevel), Resource.EMPTY);
        for (int k = 0; k < helper.size(); k++) {
            if(!helper.get(k).equals(Resource.EMPTY)) {
                levels.get(originLevel)[k] = helper.get(k);
            }
        }
    }


    /**
     * Gets the {@link WareHouse}'s levels adding them in one ArrayList.
     *
     * @return the a copy of the ArrayList with the {@link WareHouse}'s levels.
     */
    public ArrayList<Resource[]> getArrayListWareHouse() {
        ArrayList<Resource[]> wareHouse = new ArrayList<>();
        wareHouse.add(new Resource[1]);
        wareHouse.add(new Resource[2]);
        wareHouse.add(new Resource[3]);

        wareHouse.get(0)[0] = level1[0];

        wareHouse.get(1)[0] = level2[0];
        wareHouse.get(1)[1] = level2[1];

        wareHouse.get(2)[0] = level3[0];
        wareHouse.get(2)[1] = level3[1];
        wareHouse.get(2)[2] = level3[2];

        return (ArrayList<Resource[]>) wareHouse.clone();

    }
}