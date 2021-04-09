package model;

import java.util.ArrayList;

public class Board
{
    //references to all the classes mentioned below
  private WareHouse wareHouse;
  private StrongBox strongBox;
  private Slot slot;
  private TempBox tempBox;
  private FaithPath faithPath;

    public Board()
    {
        wareHouse = new WareHouse();
        strongBox = new StrongBox();
        slot = new Slot();
        tempBox = new TempBox(strongBox);
        faithPath = new FaithPath();
    }
    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public StrongBox getStrongBox() {
        return strongBox;
    }

    public Slot getSlot() {
        return slot;
    }

    public TempBox getTempBox() {
        return tempBox;
    }

    public FaithPath getFaithPath() {
        return faithPath;
    }

    public boolean hasResources(ArrayList<Resource> needed)
    {
        ArrayList<Resource> tmp = new ArrayList<Resource>();
        tmp.addAll(strongBox.getResources());
        tmp.addAll(wareHouse.getResources());
        return tmp.containsAll(needed);
    }

    /*
    Remove the resources asking through the view where to get them
    @result removed resources from either the strongBox or the wareHouse only if the player has them
     */
    public void removeResources(ArrayList<Resource> toRem)
    {
        if(hasResources(toRem))
        {
            for(Resource r: toRem)
            {
                String place=View.askWhere(r);
                if(place.equals("strongbox"))
                {
                    try {
                        strongBox.removeResource(r);
                    }
                    //If the resource is not in the strongbox it has to be in the warehouse
                    catch(Exception ex)
                    {
                        View.print("The resource is not available here, I'm getting it from the warehouse");
                        wareHouse.removeResource(r);
                    }
                }

                if(place.equals("warehouse"))
                {
                    try {
                        wareHouse.removeResource(r);
                    }
                    //If the resource is not in the strongbox it has to be in the warehouse
                    catch(Exception ex)
                    {
                        View.print("The resource is not available here, I'm getting it from the strongbox");
                        try
                        {
                            strongBox.removeResource(r);
                        }
                        catch (Exception exc)
                        {

                        }
                    }
                }
            }

        }
    }

    //remove resource and check resources methods needed here
}
