package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DevCard {
    private String color;
    private int level;
    private int victoryPoint;
    private boolean isEnable;
    private Player owner;

    //What it needs and what it produces
    private ArrayList<Resource> requires;
    private ArrayList<Resource> produces;

    private ArrayList<Resource> price;


    //Gets all the information from the controller and istantiate a new card to be set on the game table
    public DevCard(String col, int lev, int vp, ArrayList<Resource> req, ArrayList<Resource> pro, ArrayList<Resource> pri)
    {
        color=col;
        level=lev;
        victoryPoint=vp;
        requires=req;
        produces=pro;
        price=pri;
        isEnable=false;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /*
    When used gives back the resources
    @result an arrayList containing all the resources
     */
    public ArrayList<Resource> use()
    {
        owner.removeResources((ArrayList<Resource>) requires.clone());

        //Needs to be casted
        return (ArrayList<Resource>) produces.clone();
    }

    //@result true if the card is on top and enabled
    public boolean canBeUsed()
    {
        return isEnable;
    }

    //used when the card is covered and it can't be used anymore
    public void disable()
    {
        isEnable=false;
    }

    //Used when the card is bought and set on top
    public void enable()
    {
        isEnable=true;
    }


}

