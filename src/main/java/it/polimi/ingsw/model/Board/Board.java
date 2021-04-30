package it.polimi.ingsw.model.Board;

import it.polimi.ingsw.connection.Connection;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

/**
 * The player's Board with the references to player's {@link WareHouse}, {@link StrongBox}, {@link Slot}, {@link FaithPath}, {@link BasicDevSpace}
 */
public class Board
{
    //references to all the classes mentioned below
  private WareHouse wareHouse;
  private StrongBox strongBox;
  private Slot slot;
  private TempBox tempBox;
  private FaithPath faithPath;
  private BasicDevSpace basicDevSpace;
  private Player player;

    /**
     * Instantiates a new Board building the objects it needs: {@link WareHouse}, {@link StrongBox}, {@link Slot}, {@link FaithPath}, {@link BasicDevSpace}, {@link TempBox}.
     */
    public Board(Player player)
    {
        wareHouse = new WareHouse();
        strongBox = new StrongBox();
        slot = new Slot();
        tempBox = new TempBox(strongBox);
        faithPath = new FaithPath();
        basicDevSpace = new BasicDevSpace(this);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets the {@link WareHouse}.
     *
     * @return the reference to the {@link WareHouse} of the board
     */
    public WareHouse getWareHouse() {
        return wareHouse;
    }

    /**
     * Gets the {@link StrongBox}.
     *
     * @return the reference to the {@link StrongBox} of the board
     */
    public StrongBox getStrongBox() {
        return strongBox;
    }

    /**
     * Gets the {@link Slot}.
     *
     * @return the reference to the {@link Slot} of the board
     */
    public Slot getSlot() {
        return slot;
    }

    /**
     * Gets the {@link TempBox}.
     *
     * @return the reference to the {@link TempBox} of the board
     */
    public TempBox getTempBox() {
        return tempBox;
    }

    /**
     * Gets the {@link FaithPath}.
     *
     * @return the reference to the {@link FaithPath} of the board.
     */
    public FaithPath getFaithPath() {
        return faithPath;
    }

    /**
     * Check if player has all the resources received as parameters, watching in both {@link WareHouse} and {@link StrongBox}
     * Use {@link StrongBox#getResources()} and {@link WareHouse#getResources()} to build an ArrayList with all player's resources.
     * @param needed the ArrayList with all the resources to check
     * @return true if the player has all needed resources, false otherwise.
     */
    public boolean hasResources(ArrayList<Resource> needed)
    {
        ArrayList<Resource> tmp = new ArrayList<Resource>();
        tmp.addAll(strongBox.getResources());
        tmp.addAll(wareHouse.getResources());
        return tmp.containsAll(needed);
    }

    /**
     * Remove the resources after check his availability, asking through the Connection where to get them.
     * <p>
     * First of all, check if player actually has the resources to remove.
     * Then, for every resource in toRem, ask him from where remove it:
     * • He choose to take it from WareHouse - check if there's it - if yes remove it, if no automatically remove it from StrongBox
     * • He choose to take it from StrongBox - check if there's it - if yes remove it, if no automatically remove it from WareHouse
     * The automatic remove is possible because the method previously checked the player's possession.
     *
     * @param toRem the ArrayList with the resources to remove.
     * @throws ResourceNotAvailable if the not all the resources in toRem aren't in the player's possession.
     */
    public void removeResources(ArrayList<Resource> toRem) throws ResourceNotAvailable
    {
        //If the player doesn't have the resources throw a new exception
        if(!hasResources(toRem))
        {
            throw new ResourceNotAvailable();
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
                    catch(ResourceNotAvailable ex)
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
                    catch(ResourceNotAvailable exc)
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
