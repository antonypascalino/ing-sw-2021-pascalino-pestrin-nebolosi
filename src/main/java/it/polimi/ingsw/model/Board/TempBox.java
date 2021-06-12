package it.polimi.ingsw.model.Board;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Used to place all the {@link Resource} produced by the {@link Player} in the current turn.
 * It is created to avoid the use of just-produced {@link Resource}s to activate other production powers.
 */
public class TempBox {
    private ArrayList<Resource> tempRes;
    private StrongBox strongBox;


    /**
     * Instantiates a new {@link TempBox} adding the {@link StrongBox} references.
     *
     * @param strongBox the {@link StrongBox} to whom save the reference.
     */
    public TempBox(StrongBox strongBox) {
        tempRes = new ArrayList<Resource>();
        this.strongBox = strongBox;
    }

    /**
     * Add a single {@link Resource} to the {@link StrongBox}.
     *
     * @param res the {@link Resource} to add.
     */
    public void addResource(ArrayList<Resource> res)
    {
        tempRes.addAll(res);
    }

    /**
     * Remove from the {@link TempBox} all the Faith points, counting and returning them.
     *
     * @return the number of Faith points produced by the {@link Player} during the turn.
     */
    public int filterFaithPoints() {
        ArrayList<Resource> faithPoints = new ArrayList<Resource>();
        for (Resource r : tempRes) {
            if (r.equals(Resource.FAITH)) {
                faithPoints.add(Resource.FAITH);
            }
        }
        tempRes = (ArrayList<Resource>) tempRes.stream().filter(r->  r!=Resource.FAITH).collect(Collectors.toList());
        return faithPoints.size();
    }

    /**
     * Move all the {@link Resource}s into the {@link StrongBox}.
     */
    public void moveToStrongBox(){
        strongBox.addResource(tempRes);
        tempRes.clear();
    }
}

