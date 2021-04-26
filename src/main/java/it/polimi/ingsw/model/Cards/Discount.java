package it.polimi.ingsw.model.Cards;

import it.polimi.ingsw.model.Player.ChangeResPlayer;
import it.polimi.ingsw.model.Player.DiscountedPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Resource;

/**
 * The type Discount.
 */
public class Discount implements LeaderCard {

    private String color1;
    private String color2;
    private Resource discount;
    private int victoryPoints;
    private boolean isEnable;
    private Player player;
    private final String className;
    private String cardID;

    /**
     * Instantiates a new Discount.
     *
     * @param color1 : the color of the first card needed to play the card
     * @param color2 : the color of the second card needed to play the card
     * @param dis : the resource rappresenting the discount given to the player
     */
    public Discount(String color1, String color2, Resource dis, int vp, String cardID)
    {
        this.cardID = cardID;
        className = this.getClass().getName();
        this.color1 = color1;
        this.color2 = color2;
        discount = dis;
        victoryPoints = vp;
        isEnable = false;
    }

    public void assignTo(Player player) {
        this.player = player;
        player.addLeaderCard(this);
    }

    /**
     * Is enable boolean.
     *
     * @return true if the card is enabled
     */
    public boolean isEnable() {
        return isEnable;
    }

    @Override
    public Boolean equals(LeaderCard compare) {
        return this.cardID.equals(compare.getID());
    }

    @Override
    public String getID() {
        return cardID;
    }

    @Override
    public String getClassName() {
        return className;
    }

    public void playCard()
    {
        if (canBePlayed())
        {
            player.addVictoryPoints(victoryPoints);
            isEnable = true;
            Player tmp = new DiscountedPlayer(player, discount);
            //Add the new powered player in substitition to the actual one if the game references
            player.getGame().changePlayer(player, tmp );
            for (LeaderCard card : player.getLeaderCards())
            {
                //Change the reference for every dev card unless they point directly to the board

                //For every leader card change the owner
                card.assignTo(tmp);
            }
        }
    }


    public boolean canBePlayed()
    {
        boolean secondColor = false;
        boolean firstColor = false;
        if (isEnable) return false; //The card can't be played twice
        for (DevCard card : player.getBoard().getSlot().getAllCards()) {
            if (card.getColor().equals(color1)) firstColor = true;
            if (card.getColor().equals(color2)) secondColor = true;
        }
        return (firstColor && secondColor);
    }
}
