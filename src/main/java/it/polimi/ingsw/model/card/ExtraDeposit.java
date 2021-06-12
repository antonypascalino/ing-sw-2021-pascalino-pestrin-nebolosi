package it.polimi.ingsw.model.card;

import it.polimi.ingsw.model.Player.ExtraDepositPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Board.WareHouse;

import java.util.Collections;

/**
 * The {@link LeaderCard} that gives to the {@link Player} an extra level in his {@link WareHouse}.
 * In that extra level the player can only place a single type of {@link Resource}.
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
     * Instantiates a new Extra deposit {@link LeaderCard} and set enable to false.
     *
     * @param victoryPoints the victory points the {@link LeaderCard} gives the {@link Player} when played.
     * @param requires      the 5 required {@link Resource} the {@link Player} has to own to play the {@link LeaderCard}.
     * @param placeableRes  the {@link Resource} which can be placed in this extra level.
     * @param cardID        the {@link LeaderCard}'s ID.
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

    @Override
    public void assignTo(Player player) {
        this.player = player;
        player.addLeaderCard(this);
    }

    @Override
    public boolean isEnable()
    {
        return isEnable;
    }

    @Override
    public void playCard()
    {
        if (canBePlayed())
        {
            isEnable = true;
            player.addVictoryPoints(victoryPoints);
            Player tmp = new ExtraDepositPlayer(player, placeableRes);
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

    @Override
    public boolean canBePlayed()
    {
        if (isEnable) return false; //It can't be played twice
        return (Collections.frequency(player.getAllResources(), requires) >= 5);
    }

    @Override
    public String getClassName()
    {
        return className;
    }

    @Override
    public boolean equals(LeaderCard compare)
    {
        return this.cardID.equals(compare.getID());

    }

    @Override
    public String getID()
    {
        return cardID;
    }

    @Override
    public void setPlayer(Player tmp) {
        this.player = tmp;
    }

    @Override
    public Resource getPowerResource() {
        return placeableRes;
    }

}
