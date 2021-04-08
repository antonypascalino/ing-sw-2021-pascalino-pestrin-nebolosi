package model;

public class Discount implements LeaderCard {

    private DevCard[] requires = new DevCard[2];
    private Resource discount;
    private int victoryPoints;
    private boolean isEnable;
    private Player player;

    public void Discount() 
    {
        //legge da Json discount, victoryPoints, requires
    }

    public void assignTo (Player player)
    {
        this.player = player;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void playCard()
    {
        if(canBePlayed())
        {
            player.discounts.add(discount);
            isEnable = true;
            player.addVictoryPoints(victoryPoints);
        }
    }

    public boolean canBePlayed()
    {
        return player.getBoard().getSlot().getDevCards().containsAll(requires);
    }



}
