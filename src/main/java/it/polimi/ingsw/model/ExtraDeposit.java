package it.polimi.ingsw.model;

import java.util.Collections;

public class ExtraDeposit implements LeaderCard {

    private int victoryPoints;
    private boolean isEnable;
    private Player player;
    private Resource requires;
    private Resource depositableRes;


    public ExtraDeposit(int victoryPoints, Resource requires, Resource depositableRes)
    {
        this.victoryPoints = victoryPoints;
        this.requires = requires;
        this.depositableRes = depositableRes;
    }

    public void assignTo(Player player) {
        this.player = player;
        player.addLeaderCard(this);
    }

    public boolean isEnable()
    {
        return isEnable;
    }

    public void playCard()
    {
        if(canBePlayed())
        {
            isEnable = true;
            player.addVictoryPoints(victoryPoints);
            player.getBoard().getWareHouse().levels.add(new Resource[2]);
        }
    }

    public boolean canBePlayed()
    {
        return (Collections.frequency(player.getBoard().getWareHouse().getResources(), requires) +
                (Collections.frequency(player.getBoard().getStrongBox().getResources(), requires)) >= 5);
    }


}
