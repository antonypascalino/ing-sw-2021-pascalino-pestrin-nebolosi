package model;

import java.util.ArrayList;

public class ChangeResource implements LeaderCard
{
    private int victoryPoints;
    private boolean isEnable;
    private Player player;
    private ArrayList<DevCard> requires;
    private String color1;
    private String color2;
    private Resource change;

    public ChangeResource()
    {
        //legge da Json bla bla bla..e anche color1 e color2
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void assignTo (Player player)
    {
        this.player = player;
    }

    public boolean canBePlayed()
    {
        int secondColor = 0;
        boolean firstColor = false;

        for( DevCard card : player.getBoard().getSlot().getDevCards())
        {
            if (card.getColor().equals(color1)) firstColor = true;
            if (card.getColor().equals(color2)) secondColor++ ;
        }

        return (firstColor && secondColor >= 2);
    }

    public void playCard()
    {
        if(canBePlayed()) player.emptyValue.add(change);
    }
}
