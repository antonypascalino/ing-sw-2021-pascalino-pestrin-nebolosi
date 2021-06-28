package it.polimi.ingsw.view.data;

import it.polimi.ingsw.Request.MarketResource;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.clientCards.*;

import java.util.ArrayList;

/**
 * It's the player with the {@link ClientChangeRes} {@link ClientLeaderCard} (it extends {@link PlayerData}).
 */
public class ChangeResData extends PlayerData {
    private ArrayList<Resource> changes;

    /**
     * Instantiates a new {@link ClientChangeRes}.
     *
     * @param changes      the changes.
     * @param originalData the original data.
     */
    public ChangeResData(ArrayList<Resource> changes, PlayerData originalData) {
        this.originalData = originalData;
        this.changes = new ArrayList<>();
        if (originalData instanceof ChangeResData)
            changes.addAll(((ChangeResData) originalData).getChanges());
        this.changes.addAll(changes);
    }

    /**
     * Gets changes.
     *
     * @return the changes
     */
    public ArrayList<Resource> getChanges() {
        return changes;
    }

    public ArrayList<MarketResource> handleWarehouse(ArrayList<Resource> res) {
        return originalData.handleWarehouse(changeEmpty(res));
    }

    public ArrayList<Resource> changeEmpty(ArrayList<Resource> res) {
        ArrayList<Resource> result = new ArrayList<>();
        for (Resource re : res) {
            //If the resource is an empty resource convert it
            if (re.equals(Resource.EMPTY)) {
                if (changes.size() == 1) {
                    re = changes.get(0);
                } else {
                    //Maybe the same thing can be done using the printResources method
                    re = originalData.getPrinter().printResource(changes);
                }
            }
            result.add(re);
        }
        return result;
    }
}
