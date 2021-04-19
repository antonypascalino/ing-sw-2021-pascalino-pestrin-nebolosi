package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * The type Extra prod.
 */
public class ExtraProd  extends DevCard implements LeaderCard{

    private int victoryPoints;
    private boolean isEnable;
    private Player player;
    private String requires; //WHat it needs to be played
    private Resource prodResource;  //What it needs for producing

    /**
     * Instantiates a new Extra prod.
     *
     * @param victoryPoints the victory points
     * @param requires      the requires
     * @param prodResource  the prod resource
     */
    public ExtraProd(int victoryPoints, String requires, Resource prodResource)
    {
        super(prodResource);
        //Un po' di rindondanza, forze basta il super anche per gli enable
        this.prodResource = prodResource;
        this.requires = requires;
        this.victoryPoints = victoryPoints;
    }

    public void assignTo(Player player) {
        this.player = player;
        player.addLeaderCard(this);
    }

    /*
    public boolean isEnable()
    {
        return isEnable;
    }
     */


    public boolean canBePlayed()
    {
        for(DevCard card : player.getBoard().getSlot().getAllCards() )
        {
            if (card.getColor().equals(requires) && card.getLevel()==2) return true;
        }
        return false;

    }

    public void playCard()
    {
        if(canBePlayed())
        {
            isEnable = true;
            player.addVictoryPoints(victoryPoints);
            player.getBoard().getSlot().addExternalCard(this);
        }

    }

    /*
    public boolean canProduce()
    {
        if (isEnable)
        {
            if (player.getBoard().getWareHouse().getResources().contains(prodResource)) return true;
            if (player.getBoard().getStrongBox().getResources().contains(prodResource)) return true;
        }
        return false;
    }
     */


    public ArrayList<Resource> produce()
    {
        //Chiedere tramite la view quale risorsa produrre
        Resource choice=new Resource(); //Risultato dalla view
        if (canProduce())
        {
            owner.getBoard().removeResources((ArrayList<Resource>) super.requires);

            //Needs to be casted


            //add resource to StrongBox and a move in FaithPath
            player.getBoard().getFaithPath().moveForward(1);
            return (ArrayList<Resource>) choice;
        }
        return null;
    }

}
