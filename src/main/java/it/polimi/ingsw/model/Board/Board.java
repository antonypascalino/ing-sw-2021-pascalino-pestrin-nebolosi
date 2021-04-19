package it.polimi.ingsw.model.Board;

import it.polimi.ingsw.connection.Connection;
import it.polimi.ingsw.model.*;

import java.util.ArrayList;

/**
 * The type Board.
 */
public class Board
{
    //references to all the classes mentioned below
  private WareHouse wareHouse;
  private StrongBox strongBox;
  private Slot slot;
  private TempBox tempBox;
  private FaithPath faithPath;

    /**
     * Instantiates a new Board.
     */
    public Board()
    {
        wareHouse = new WareHouse();
        strongBox = new StrongBox();
        slot = new Slot();
        tempBox = new TempBox(strongBox);
        faithPath = new FaithPath();
    }

    /**
     * Gets ware house.
     *
     * @return the ware house
     */
    public WareHouse getWareHouse() {
        return wareHouse;
    }

    /**
     * Gets strong box.
     *
     * @return the strong box
     */
    public StrongBox getStrongBox() {
        return strongBox;
    }

    /**
     * Gets slot.
     *
     * @return the slot
     */
    public Slot getSlot() {
        return slot;
    }

    /**
     * Gets temp box.
     *
     * @return the temp box
     */
    public TempBox getTempBox() {
        return tempBox;
    }

    /**
     * Gets faith path.
     *
     * @return the faith path
     */
    public FaithPath getFaithPath() {
        return faithPath;
    }

    /**
     * Has resources boolean.
     *
     * @param needed the needed
     * @return the boolean
     */
    public boolean hasResources(ArrayList<Resource> needed)
    {
        ArrayList<Resource> tmp = new ArrayList<Resource>();
        tmp.addAll(strongBox.getResources());
        tmp.addAll(wareHouse.getResources());
        return tmp.containsAll(needed);
    }

    /**
     * Remove resources.
     *
     * @param toRem the to rem
     * @throws ResourceNotAvaible the resource not avaible
     */
/*
    Remove the resources asking through the view where to get them
    @result removed resources from either the strongBox or the wareHouse only if the player has them
    @signal throws an exception if the player doesn't have the resources
     */
    public void removeResources(ArrayList<Resource> toRem) throws ResourceNotAvaible
    {
        //If the player doesn't have the resources throw a new exception
        if(!hasResources(toRem))
        {
            throw new ResourceNotAvaible();
        }
        //Othetwise the resources have to be there and the player can choose where to get them
        else
        {
            for(Resource r: toRem)
            {
                String place= Connection.askWhere(r);
                if(place.equals("strongbox"))
                {
                    try {
                        strongBox.removeResource(r);
                    }
                    //If the resource is not in the strongbox it has to be in the warehouse
                    catch(ResourceNotAvaible ex)
                    {
                        Connection.print("The resource is not available here, I'm getting it from the warehouse");
                        wareHouse.removeResource(r);
                    }
                }

                if(place.equals("warehouse"))
                {
                    try {
                        wareHouse.removeResource(r);
                    }
                    //If the resource is not in the strongbox it has to be in the warehouse
                    catch(ResourceNotAvaible exc)
                    {
                        Connection.print("The resource is not available here, I'm getting it from the strongbox");
                        strongBox.removeResource(r);
                    }
                }
            }

        }
    }

    //remove resource and check resources methods needed here
}
