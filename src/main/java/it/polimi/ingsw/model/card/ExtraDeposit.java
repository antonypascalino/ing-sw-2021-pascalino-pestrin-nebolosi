package it.polimi.ingsw.model.card;

import it.polimi.ingsw.model.Player.ExtraDepositPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;

import java.util.Collections;

/**
 * The type Extra deposit.
 */
public class ExtraDeposit implements LeaderCard {

    private int victoryPoints;
    private boolean isEnable;
    private Player player;
    private Resource requires;
    private Resource placeableRes;
    private final String className;
    private String cardID;

    /**
     * Instantiates a new Extra deposit.
     *
     * @param victoryPoints  the victory points
     * @param requires       the requires for being played
     * @param placeableRes the depositable res
     * @param cardID the unique id for the card
     */
    public ExtraDeposit(int victoryPoints, Resource requires, Resource placeableRes, String cardID)
    {
        this.cardID = cardID;
        className = this.getClass().getName();
        this.victoryPoints = victoryPoints;
        this.requires = requires;
        this.placeableRes = placeableRes;
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
            Player tmp = new ExtraDepositPlayer(player, placeableRes);
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

    public String getClassName()
    {
        return className;
    }

    @Override
    public Boolean equals(LeaderCard compare)
    {
        return this.cardID.equals(compare.getID());

    }

    @Override
    public String getID()
    {
        return cardID;
    }

    public boolean checkPlaceable(Resource res) {
        return res.equals(placeableRes);
    }

    public String toString() {
        return "Extra Deposit Leader Card:\nYou will have an Extra Deposit of 2 spaces in you Warehouse; in it you can deposit " + placeableRes + "S" + "\nTo play this card you need to have 5 " + requires + "S";
    }
}
