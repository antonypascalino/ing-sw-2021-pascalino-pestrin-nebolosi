package it.polimi.ingsw.model.card;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

/**
 * The type Dev card.
 */
public class DevCard /*extends Producer*/ {
    private String color;
    private int level;
    private int victoryPoint;
    private boolean isEnable;
    private String cardID;
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
        cardID = id;
        color = col;
        level = lev;
        victoryPoint = vp;
        requires = req;
        produces = pro;
        price = pri;
        isEnable = false;
        owner = null;
        cardID = id;
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
     *
     * @return the array list of the produced resourced
     */
    //PLACE IT IN THE STRONGBOX

    public ArrayList<Resource> producedResources()
    {
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

    public String getCardID() {
        return cardID;
    }

    public ArrayList<Resource> getRequirements(){
        return requires;
    }
}
