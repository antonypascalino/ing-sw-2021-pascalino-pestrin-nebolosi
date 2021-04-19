package it.polimi.ingsw.model.Cards;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.ResourceNotAvaible;

import java.util.ArrayList;

/**
 * The type Dev card.
 */
public class DevCard {
    private String color;
    private int level;
    private int victoryPoint;
    private boolean isEnable;
    /**
     * The Owner.
     */
    protected Player owner;

    /**
     * The Requires.
     */
//What it needs and what it produces
    ArrayList<Resource> requires;
    private ArrayList<Resource> produces;

    private ArrayList<Resource> price;


    /**
     * Instantiates a new Dev card.
     *
     * @param col the col
     * @param lev the lev
     * @param vp  the vp
     * @param req the req
     * @param pro the pro
     * @param pri the pri
     */
//Gets all the information from the it.polimi.ingsw.controller and istantiate a new card to be set on the game table
    public DevCard(String col, int lev, int vp, ArrayList<Resource> req, ArrayList<Resource> pro, ArrayList<Resource> pri) {
        color = col;
        level = lev;
        victoryPoint = vp;
        requires = req;
        produces = pro;
        price = pri;
        isEnable = false;
    }

    /**
     * Instantiates a new Dev card.
     *
     * @param res the res
     */
//Constructor used by subclasses
    public DevCard(Resource res)
    {
        ArrayList<Resource> tmp= new ArrayList<>();
        tmp.add(res);
        color = "grey";
        level = 0;
        victoryPoint = 0;
        requires = tmp;
        produces = null;
        price = null;
        isEnable = false;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
//When bought set their owner
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
     * Produce array list.
     *
     * @return the array list
     */
/*
    When used gives back the resources
    @result an arrayList containing all the resources
     */
    public ArrayList<Resource> produce()
    {
        try{
            owner.getBoard().removeResources((ArrayList<Resource>) requires.clone());
        }
        catch(ResourceNotAvaible ex)
        {

        }

        //Needs to be casted
        return (ArrayList<Resource>) produces.clone();
    }

    /**
     * Can produce boolean.
     *
     * @return the boolean
     */
    public boolean canProduce()
    {
        return owner.getBoard().hasResources(requires);
    }

    /**
     * Can be used boolean.
     *
     * @return the boolean
     */
//@result true if the card is on top and enabled
    public boolean canBeUsed() {
            return isEnable;
    }

    /**
     * Disable.
     */
//used when the card is covered and it can't be used anymore
    public void disable() {
        isEnable = false;
    }

    /**
     * Enable.
     */
//Used when the card is bought and set on top
    public void enable() {
        isEnable = true;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public ArrayList<Resource> getPrice() {
        return price;
    }
}
