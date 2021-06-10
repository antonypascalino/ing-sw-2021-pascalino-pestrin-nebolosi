package it.polimi.ingsw.model.card;

import it.polimi.ingsw.model.Player.ChangeResPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;

/**
 * The leader card that gives the player the possibility to change a resource
 */
public class ChangeResource implements LeaderCard
{
    private int victoryPoints;
    private boolean isEnable;
    private Player player;
    private String color1;
    private String color2;
    private Resource change;
    private final String className;
    public String cardID;
    /**
     * Instantiates a new Change resource and sets the enable to false
     *
     * @param victoryPoints the victory points the card gives to the player
     * @param color1        the color of the first card required for being played
     * @param color2        the color of the second card required for being played
     * @param change        the resource in which the new player turns the white resource
     * @param cardID the unique id for the card
     */
    public ChangeResource(int victoryPoints, String color1, String color2, Resource change, String cardID)
    {
        this.cardID = cardID;
        className = this.getClass().getName();
        this.victoryPoints = victoryPoints;
        this.color1 = color1;
        this.color2 = color2;
        this.change = change;
        isEnable=false;
    }

    @Override
    public void setPlayer(Player tmp) {
        this.player = tmp;
    }

    @Override
    public Resource getPowerResource() {
        return change;
    }

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


    public void assignTo(Player player) {
        this.player = player;
        player.addLeaderCard(this);
    }

    /**
     * Check if the player can play the card
     * @return true if he has one card of the first color e two card of the second color
     */
    public boolean canBePlayed()
    {
        return true;
//        int secondColor = 0;
//        boolean firstColor = false;
//
//        for( DevCard card : player.getBoard().getSlot().getAllCards())
//        {
//            if (card.getColor().equals(color1.toUpperCase())) firstColor = true;
//            if (card.getColor().equals(color2.toUpperCase())) secondColor++ ;
//        }
//
//        return (firstColor && secondColor >= 2);
    }

    @Override
    public String getClassName()
    {
        return className;
    }

    public void playCard()
    {
        if (canBePlayed())
        {
            player.addVictoryPoints(victoryPoints);
            isEnable= true;
            Player tmp = new ChangeResPlayer(player, change);
            //Add the new powered player in substitition to the actual one if the game references
            player.getGame().changePlayer(player, tmp);
            for (LeaderCard card : player.getLeaderCards())
            {
                //Do not change the reference on this card
                if(!card.getID().equals(this.getID()))
                    card.setPlayer(tmp);
            }
            this.player = tmp;
        }

    }


}
