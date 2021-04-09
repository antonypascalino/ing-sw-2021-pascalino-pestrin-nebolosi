package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class WareHouse
{
    private Resource level1[];
    private Resource level2[];
    private Resource level3[];
    ArrayList<Resource> discount; //If a player has a discount it's contained here
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
        discount= new ArrayList<Resource>();
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

    //remove the resource
    public Boolean removeResource(Resource res)
    {
        if(!checkAvailability(res))
            return false;

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

    public Boolean checkAvailability(Resource res)
    {
        if(this.getResources().contains(res))
            return true;
        else
            return false;
    }

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
