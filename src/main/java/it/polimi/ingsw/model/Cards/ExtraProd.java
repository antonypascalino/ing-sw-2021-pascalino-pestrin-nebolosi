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

    /**
     * Instantiates a new Extra prod.
     *
     * @param victoryPoints the victory points
     * @param requires      the requires
     * @param prodResource  the prod resource
     */
    public ExtraProd(int victoryPoints, String requires, Resource prodResource)
    {
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
            Player tmp =new ExtraProdPlayer(player, prodResource );
            player.getGame().changePlayer(player, tmp);
            for (LeaderCard card : player.getLeaderCards())
            {
                card.assignTo(tmp);
            }
        }
    }






}
