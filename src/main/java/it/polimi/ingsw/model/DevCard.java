package it.polimi.ingsw.model;

import java.util.ArrayList;

public class DevCard {
    private String color;
    private int level;
    private int victoryPoint;
    private boolean isEnable;
    protected Player owner;

    //What it needs and what it produces
    ArrayList<Resource> requires;
    private ArrayList<Resource> produces;

    private ArrayList<Resource> price;


    //Gets all the information from the controller and istantiate a new card to be set on the game table
    public DevCard(String col, int lev, int vp, ArrayList<Resource> req, ArrayList<Resource> pro, ArrayList<Resource> pri) {
        color = col;
        level = lev;
        victoryPoint = vp;
        requires = req;
        produces = pro;
        price = pri;
        isEnable = false;
    }

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

    //When bought set their owner
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getColor() {
        return color;
    }

    public int getLevel() {
        return level;
    }

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

    public boolean canProduce()
    {
        return owner.getBoard().hasResources(requires);
    }

    //@result true if the card is on top and enabled
    public boolean canBeUsed() {
            return isEnable;
    }

    //used when the card is covered and it can't be used anymore
    public void disable() {
        isEnable = false;
    }

    //Used when the card is bought and set on top
    public void enable() {
        isEnable = true;
    }

}
