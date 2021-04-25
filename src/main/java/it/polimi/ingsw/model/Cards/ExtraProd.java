package it.polimi.ingsw.model.Cards;

import it.polimi.ingsw.model.Player.ExtraProdPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

/**
 * The type Extra prod.
 */
public class ExtraProd implements LeaderCard {

    private int victoryPoints;
    private boolean isEnable;
    private Player player;
    private String requires; //WHat it needs to be played
    private Resource prodResource;  //What it needs for producing
    private final String className;

    /**
     * Instantiates a new Extra prod.
     *
     * @param victoryPoints the victory points
     * @param requires      the color of the required card
     * @param prodResource  the prod resource
     */
    public ExtraProd(int victoryPoints, String requires, Resource prodResource)
    {
        className = this.getClass().getName();
        this.prodResource = prodResource;
        this.requires = requires;
        this.victoryPoints = victoryPoints;
        isEnable = false;
    }

    public void assignTo(Player player) {
        this.player = player;
        player.addLeaderCard(this);
    }


    public boolean isEnable()
    {
        return isEnable;
    }

    public boolean canBePlayed()
    {
        if(isEnable) return false; //It can't be played twice
        for(DevCard card : player.getBoard().getSlot().getAllCards() )
        {
            if (card.getColor().equals(requires) && card.getLevel()==2) return true;
        }
        return false;

    }

    public String getClassName()
    {
        return className;
    }

    public void playCard()
    {
        if(canBePlayed())
        {
            isEnable = true;
            player.addVictoryPoints(victoryPoints);
            Player tmp =new ExtraProdPlayer(player, prodResource );
            player.getGame().changePlayer(player, tmp);
            for (LeaderCard card : player.getLeaderCards())
            {
                card.assignTo(tmp);
            }
        }
    }
}
