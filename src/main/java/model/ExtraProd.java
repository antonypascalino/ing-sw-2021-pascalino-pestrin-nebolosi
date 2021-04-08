package model;

import java.util.ArrayList;

public class ExtraProd implements LeaderCard {

    private int victoryPoints;
    private boolean isEnable;
    private Player player;
    private String requires;
    private Resource prodResource;

    public ExtraProd()
    {
        //legge da json victoryPoints, requires, prodResource
    }

    public void assignTo (Player player)
    {
        this.player = player;
    }

    public boolean isEnable()
    {
        return isEnable;
    }

    public boolean canBePlayed()
    {
        for(DevCard card : player.getBoard().getSlot().getDevCards() )
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
            player.getBoard().getExtraProdSlot().addCard(this);
        }

    }

    public boolean canProduce()
    {
        if (isEnable)
        {
            if (player.getBoard().getWareHouse().getResources().contains(prodResource)) return true;
            if (player.getBoard().getStrongBox().getResources().contains(prodResource)) return true;
        }
        return false
    }

    public ArrayList<Resource> produce(Resource choice)
    {
        if (canProduce())
        {
            //chiedere al player da dove vuole prendere la risorsa per produrre

            if(/*il giocatore ha scelto Warehouse*/)
            {
                player.getBoard().getWareHouse().removeResource(prodResource);
            }
            if(/*il giocatore ha scelto StrongBox*/)
            {
                player.getBoard().getStrongBox().removeResource(prodResource);
            }

            //add resource to StrongBox and a move in FaithPath
            player.getBoard().getStrongBox().addResources(choice);
            player.getBoard().getFaithPath().moveForward(1);
        }
    }
}
