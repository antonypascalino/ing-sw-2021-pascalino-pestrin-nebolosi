package it.polimi.ingsw.model.Cards;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

/**
 * The type Change resource.
 */
public class ChangeResource implements LeaderCard
{
    private int victoryPoints;
    private boolean isEnable;
    private Player player;
    private ArrayList<DevCard> requires;
    private String color1;
    private String color2;
    private Resource change;

    /**
     * Instantiates a new Change resource.
     *
     * @param victoryPoints the victory points
     * @param requires      the requires
     * @param color1        the color 1
     * @param color2        the color 2
     * @param change        the change
     */
    public ChangeResource(int victoryPoints, ArrayList<DevCard> requires, String color1, String color2, Resource change) {
        this.victoryPoints = victoryPoints;
        this.requires = requires;
        this.color1 = color1;
        this.color2 = color2;
        this.change = change;
    }

    /**
     * Is enable boolean.
     *
     * @return the boolean
     */
    public boolean isEnable() {
        return isEnable;
    }

    public void assignTo(Player player) {
        this.player = player;
        player.addLeaderCard(this);
    }

    public boolean canBePlayed()
    {
        int secondColor = 0;
        boolean firstColor = false;

        for( DevCard card : player.getBoard().getSlot().getAllCards())
        {
            if (card.getColor().equals(color1)) firstColor = true;
            if (card.getColor().equals(color2)) secondColor++ ;
        }

        return (firstColor && secondColor >= 2);
    }

    public void playCard()
    {
        Resource tmp = new ChangeResource(player, change);

        //Add the new powered player in substitition to the actual one if the game references
        player.getGame().updatePlayer(player,tmp);
        if(canBePlayed()) player.emptyValue.add(change);
    }
}
