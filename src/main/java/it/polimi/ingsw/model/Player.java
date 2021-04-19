package it.polimi.ingsw.model;
import java.util.ArrayList;

public abstract class Player {
    private Player original;
    public void setNickName(String newNick)
    {
        original.setNickName(newNick);
    }

    public String getNickName()
    {
        return original.getNickName();
    }

    public int getVictoryPoints()
    {
        return original.getVictoryPoints();
    }

    public Board getBoard()
    {
        return original.getBoard();
    }

    /*
    Used in the start game phase when the player chooses two out of the four leader cards
    @param a leader card that needs to be added to the player
     */
    public void addLeaderCard(LeaderCard card)
    {
        original.addLeaderCard(card);
    }

    public void addVictoryPoints(int vp)
    {
        original.addVictoryPoints(vp);
    }

    public void getFromMarket()
    {
        original.getFromMarket();
    }
}
