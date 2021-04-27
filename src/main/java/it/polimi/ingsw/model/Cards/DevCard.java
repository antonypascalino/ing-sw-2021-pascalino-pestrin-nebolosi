package it.polimi.ingsw.model.Cards;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.ResourceNotAvailable;

import java.util.ArrayList;

/**
 * The type Dev card.
 */
public class DevCard {
    private String color;
    private int level;
    private int victoryPoint;
    private boolean isEnable;
    private String cardId;
    /**
     * The Owner.
     */
    protected Player owner;

    /**
     * The Requires for production
     */
    ArrayList<Resource> requires;
    private ArrayList<Resource> produces;

    private ArrayList<Resource> price;


    /**
     * Instantiates a new Dev card and set its enable to false
     * Gets all the information from the it.polimi.ingsw.controller which deserializes from the json
     * the  and istantiate a new card to be set on the game table
     * @param col the color
     * @param lev the level
     * @param vp  the victory points
     * @param req the requires needed for producing
     * @param pro the resource produced by the card
     * @param pri the price for buying the card
     */
    public DevCard(String id, String col, int lev, int vp, ArrayList<Resource> req, ArrayList<Resource> pro, ArrayList<Resource> pri) {
        cardId = id;
        color = col;
        level = lev;
        victoryPoint = vp;
        requires = req;
        produces = pro;
        price = pri;
        isEnable = false;
        owner = null;
        cardId = id;
    }

    /**
     * Sets owner when the player buys a card
     *
     * @param owner the owner
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * When used gives back the resources and remove the resources
     * from the strongBox or from the wareHouse
     *
     * @param mappedResources the map cointaing the place where to remove each resource
     * @return the array list of the produced resourced
     */
    //PLACE IT IN THE STRONGBOX
    public ArrayList<Resource> produce(ArrayList<MappedResource> mappedResources)
    {
        try{
            owner.getBoard().removeResources((ArrayList<Resource>) requires.clone());
        }
        catch(ResourceNotAvailable ex)
        {

        }

        //Needs to be casted
        return (ArrayList<Resource>) produces.clone();
    }

    /**
     * Can produce boolean.
     *
     * @return the true if the player has the resource for using the card
     */
    public boolean canProduce()
    {
        return owner.getBoard().hasResources(requires);
    }

    /**
     * Can be used boolean.
     *
     * @return true if the card is enabled
     */
    public boolean canBeUsed() {
            return isEnable;
    }

    /**
     * Used when the card is covered and it gets disabled
     * Disable.
     */
    public void disable() {
        isEnable = false;
    }

    /**
     * Used when the card is bought and set on top
     */
    public void enable() {
        isEnable = true;
    }

    /**
     * Gets price
     *
     * @return the price that the player needs to pay the card
     */
    public ArrayList<Resource> getPrice() {
        return price;
    }
}
