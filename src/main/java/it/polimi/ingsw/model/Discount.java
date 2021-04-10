package it.polimi.ingsw.model;

public class Discount implements LeaderCard {

    private int victoryPoints;
    private DevCard[] requires = new DevCard[2] ;
    private Resource discount;
    private String color1;
    private String color2;
    private boolean isEnable;
    private Player player;

    public Discount(int victoryPoints, DevCard[] requires, Resource discount, String color1, String color2) {
        this.victoryPoints = victoryPoints;
        this.requires = requires;
        this.discount = discount;
        this.color1 = color1;
        this.color2 = color2;
    }

    public void assignTo (Player player)
    {
        this.player = player;
    }


    public void addDevCard(DevCard card)
    {
        for (int i=0;  i < requires.length; i++)
        {
            if (requires[i] == null) requires[i] = card;
            break;
        }
    }

    public boolean isEnable()
    {
        return isEnable;
    }

    public boolean canBePlayed()
    {
        return Slot.getALLCards().containsAll(requires);
    }

    public void playCard()
    {
        isEnable = true;
        Player.discount.add(discount);

    }

}
