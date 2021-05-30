package it.polimi.ingsw.model.Board;

import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Used to place all the {@link Resource} produced by the {@link it.polimi.ingsw.model.Player.Player} in the current production
 * turn. It is created to avoid the use of just-produced {@link Resource}s to activate other production powers using them in the same turn
 */
public class TempBox
{
    private ArrayList<Resource> tempRes;
    private StrongBox sb;


    /**
     * Instantiates a new {@link TempBox} adding the {@link StrongBox} references.
     *
     * @param strongBox the {@link StrongBox} to whom save the reference.
     */
    public TempBox(StrongBox strongBox)
    {
        tempRes = new ArrayList<Resource>();
        sb = strongBox;
    }

    /**
     * Add a single (why only one?) {@link Resource} to the {@link StrongBox}.
     *
     * @param res the {@link Resource} to add.
     */
    public void addResource(ArrayList<Resource> res)
    {
        tempRes.addAll(res);
    }

    /**
     * At the end of the production turn puts all the {@link Resource}s from the {@link TempBox} into the {@link StrongBox}.
     */
    public ArrayList<Resource> filterChoices() {
        ArrayList<Resource> choices = new ArrayList<Resource>();
        for (Resource r : tempRes) {
            if (r.equals(Resource.CHOICE)) {
                choices.add(Resource.CHOICE);
                tempRes.remove(r);
            }
        }
        return choices;
    }

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

    public void moveToStrongBox(){
        sb.addResource(tempRes);
        tempRes.clear();
    }
}

