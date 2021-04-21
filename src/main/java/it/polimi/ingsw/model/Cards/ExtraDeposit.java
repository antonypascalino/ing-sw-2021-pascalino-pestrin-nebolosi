package it.polimi.ingsw.model.Cards;

import it.polimi.ingsw.model.Player.DiscountedPlayer;
import it.polimi.ingsw.model.Player.ExtraDepositPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Resource;

import java.util.Collections;

/**
 * The type Extra deposit.
 */
public class ExtraDeposit implements LeaderCard {

    private int victoryPoints;
    private boolean isEnable;
    private Player player;
    private Resource requires;
    private Resource depositableRes;


    /**
     * Instantiates a new Extra deposit.
     *
     * @param victoryPoints  the victory points
     * @param requires       the requires for being played
     * @param depositableRes the depositable res
     */
    public ExtraDeposit(int victoryPoints, Resource requires, Resource depositableRes)
    {
        this.victoryPoints = victoryPoints;
        this.requires = requires;
        this.depositableRes = depositableRes;
        isEnable = false;
    }

    public void assignTo(Player player) {
        this.player = player;
        player.addLeaderCard(this);
    }

    /**
     * Is enable boolean.
     *
     * @return the boolean
     */
    public boolean isEnable()
    {
        return isEnable;
    }

    public void playCard()
    {
        if (canBePlayed())
        {
            isEnable = true;
            player.addVictoryPoints(victoryPoints);
            Player tmp = new ExtraDepositPlayer(player, depositableRes);
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
        if (isEnable) return false; //It can't be played twice
        return (Collections.frequency(player.getBoard().getWareHouse().getResources(), requires) +
                (Collections.frequency(player.getBoard().getStrongBox().getResources(), requires)) >= 5);
    }


}
