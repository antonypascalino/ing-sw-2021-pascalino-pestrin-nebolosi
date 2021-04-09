package Model;

import java.util.ArrayList;

public class BasicDevSpace {
    Board br;
    public BasicDevSpace(Board board)
    {
        br = board;
    }

    public Resource use(Resource res1, Resource res2)
    {
        ArrayList<Resource> tmp= new ArrayList<Resource>();
        tmp.add(res1);
        tmp.add(res2);
        if(canBeUsed(res1,res2))
            br.removeResources(tmp);
        return View.chooseRes();
    }

    public boolean canBeUsed(Resource res1, Resource res2)
    {
        ArrayList<Resource> tmp= new ArrayList<Resource>();
        tmp.add(res1);
        tmp.add(res2);
        return br.hasResources(tmp);
    }
}
