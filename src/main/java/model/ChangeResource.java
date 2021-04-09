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

    public ChangeResource(int victoryPoints, ArrayList<DevCard> requires, String color1, String color2, Resource change) {
        this.victoryPoints = victoryPoints;
        this.requires = requires;
        this.color1 = color1;
        this.color2 = color2;
        this.change = change;
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
