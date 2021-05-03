package it.polimi.ingsw.model.Player;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.MarketResource;
import it.polimi.ingsw.model.Board.Board;
import it.polimi.ingsw.model.Cards.LeaderCard;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Table.Table;

import java.util.ArrayList;

/**
 * The type Player.
 */
public abstract class Player {
    /**
     * The original player abstract class.
     */
    Player original;

    /**
     * Sets nick name.
     *
     * @param newNick the new nickname inserted at the start of the game.
     */
    public void setNickName(String newNick)
    {
        original.setNickName(newNick);
    }

    /**
     * Gets nickname.
     *
     * @return the nickname
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
     * Gets {@link Board}.
     *
     * @return {@link Board}.
     */
    public Board getBoard()
    {
        return original.getBoard();
    }

    /**
     * Gets {@link Game}.
     *
     * @return the {@link Game}.
     */
    public Game getGame() { return original.getGame(); }

    /**
     * Adds {@link LeaderCard}.
     * called at the beginning of the match.
     * The player chooses two out of the four leader cards.
     *
     * @param card the card that needs to be added to the player.
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
     * @param vp the victory points added throughout the game
     */
    public void addVictoryPoints(int vp)
    {
        original.addVictoryPoints(vp);
    }

    /**
     * Gets resources from {@link it.polimi.ingsw.model.Table.Market}.
     */
    public void getFromMarket()
    {
        original.getFromMarket();
    }


    /**
     * Gets {@link LeaderCard}.
     *
     * @return the leader cards
     */
    public ArrayList<LeaderCard> getLeaderCards()
    {
        return original.getLeaderCards();
    }

    /**
     * Gets all the production powers.
     */

    public void getProduction()
    {
        original.getProduction();
    }

    /**
     * Production method of the original player
     */
    public void produce()
    {
        original.produce();
    }

    public boolean checkSpace(MarketResource marketRes){
        return original.checkSpace(marketRes);
    }

    public void addToWareHouse(int level, Resource res) {
        original.addToWareHouse(level,res);
    }

    public Table getTable() {
        return original.getTable();
    }

    public boolean checkMarketRes(ArrayList<Resource> requestedRes, ArrayList<Resource> marketRes) {
        return original.checkMarketRes(requestedRes, marketRes);
    }

    public boolean checkLevel(ArrayList<MarketResource> marketResources) {
        return original.checkLevel(marketResources);
    }

    public ArrayList<Resource> getAllResources()
    {
        ArrayList<Resource> tmp = new ArrayList<Resource>();
        tmp.addAll(this.getBoard().getStrongBox().getResources());
        tmp.addAll(this.getBoard().getWareHouse().getResources());
        return tmp;
    }
}
