package it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Board.Board;
import it.polimi.ingsw.model.Cards.LeaderCard;

/**
 * The type Player.
 */
public abstract class Player {
    Player original;

    /**
     * Sets nick name.
     *
     * @param newNick the new nick
     */
    public void setNickName(String newNick)
    {
        original.setNickName(newNick);
    }

    /**
     * Gets nick name.
     *
     * @return the nick name
     */
    public String getNickName()
    {
        return original.getNickName();
    }

    /**
     * Gets victory points.
     *
     * @return the victory points
     */
    public int getVictoryPoints()
    {
        return original.getVictoryPoints();
    }

    /**
     * Gets board.
     *
     * @return the board
     */
    public Board getBoard()
    {
        return original.getBoard();
    }

    /**
     * Add leader card.
     *
     * @param card the card
     */
/*
    Used in the start game phase when the player chooses two out of the four leader cards
    @param a leader card that needs to be added to the player
     */
    public void addLeaderCard(LeaderCard card)
    {
        original.addLeaderCard(card);
    }

    /**
     * Add victory points.
     *
     * @param vp the vp
     */
    public void addVictoryPoints(int vp)
    {
        original.addVictoryPoints(vp);
    }

    /**
     * Gets from market.
     */
    public void getFromMarket()
    {
        original.getFromMarket();
    }
}
