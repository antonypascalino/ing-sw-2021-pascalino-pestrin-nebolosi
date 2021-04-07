package Model;

import java.util.ArrayList;

public class WareHouse
{
    private Resource level1[1];
    private Resource level2[2];
    private Resource level3[3];
    ArrayList<Resource[]> levels; //Contains all the possible levels, including the leader cards ones.

    //Default without any leaderCard
    public WareHouse()
    {
        level1 = new Resource[1];
        level2 = new Resource[2];
        level3 = new Resource[3];
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
    }

    //Add res in the level. Already checked space avaibilty
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

}
