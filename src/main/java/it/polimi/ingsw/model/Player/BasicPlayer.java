package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.Request.Dimension;
import it.polimi.ingsw.Request.MarketRequest;
import it.polimi.ingsw.Request.ProduceRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.controller.MarketResource;
import it.polimi.ingsw.model.Board.Board;
import it.polimi.ingsw.model.Cards.DevCard;
import it.polimi.ingsw.model.Cards.LeaderCard;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Table.Table;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;

/**
 * The type Basic player (it extends {@link Player})
 * It's the player without any leader card.
 */
public class BasicPlayer extends Player {
    private String nickName;
    private Board board;
    private ArrayList<LeaderCard> leaderCards;
    private int victoryPoints;
    //private Table table;
    private Player original; //Even if this attribute is in the Player class for not rewriting all the code, it's never being used in this class

    /**
     * Instantiates a new Basic player.
     *
     * @param tb the game table assigned to the player
     */
    public BasicPlayer(Table tb)
    {
        //table = tb;
        original=null;
        board = new Board(this);
        leaderCards = new ArrayList<LeaderCard>();
        victoryPoints = 0;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName()
    {
        return nickName;
    }

    public int getVictoryPoints()
    {
        return victoryPoints;
    }

    /**
     * When player chooses a card, this method buys the card and sets the card into the given slot
     *
     * @param color dev card color
     * @param level dev card level
     */


    //DA RIVEDERE TUTTO
    public void getDevCard(String color, int level)
    {
        DevCard card = null;
        int slot=2;

        //Dev'essere cambiato in modo che sia gestito in qualche modo dal game, tipo assegnando al giocatore un riferimento al game in cui si trova
        //card = table.buyDev(color, level);
        if(board.hasResources(card.getPrice()))

            card.setOwner(this);
        //richiesta al giocatore in quale slot mettere la card
        board.getSlot().purchaseCard(card,slot);
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<LeaderCard> getLeaderCards()
    {
        return leaderCards;
    }

    public void addLeaderCard (LeaderCard card)
    {
        leaderCards.add(card);
    }

    public void addVictoryPoints (int victoryPoints)
    {
        this.victoryPoints += victoryPoints;
    }

    public void getProduction()
    {
        for (DevCard dev : board.getSlot().getFrontCards())
            //Used for giving the power of all cards to the view
            //dev.getPower();
            System.out.println("Debug");
    }

    /**
     * Produces the resources (work in progress)
     *
     * @param requests the produce requests as an array list
     */
    public void produce(ArrayList<ProduceRequest> requests)
    {
        //for (ProduceRequest r : requests )
            //if(getBoard().getSlot().getFrontCards().contains(r.getCard());
            //ALlora lo usa sostituendo a ogni richiesta un valore di r.getChoiche
        //per ogni richiesta di produzione (dalla connection) attiva la giusta carta e salva la produzione
        //nel forziere del giocatore
    }

    @Override
    public boolean checkSpace(MarketResource marketRes) {
        return board.getWareHouse().checkSpace(marketRes.getLevel(), marketRes.getResource());
    }

    @Override
    public void addToWareHouse(int level, Resource res) {
        if (level <= 3) {
            board.getWareHouse().addResource(level, res);
        }
        //else il player non possiede livelli aggiuntivi
    }

//    @Override
//    public Table getTable() {
//        return table;
//    }

    @Override
    public boolean checkMarketRes(ArrayList<Resource> requestedRes, ArrayList<Resource> marketRes) {
        for (int i = 0; i < marketRes.size(); i++) {
            if (marketRes.get(i).equals(requestedRes.get(i))) {
                continue;
            }
            else return false; //o comunque invia eccezione
        }
        return true;
    }

    @Override
    public boolean checkLevel(ArrayList<MarketResource> marketResources) {
        for(MarketResource marketRes : marketResources) {
            if (!(marketRes.getLevel() <= 3 && marketRes.getLevel() > 0)) {
                return false;
                // se false lancia eccezione perché non ha carte che aggiungono livelli e quindi non ha livelli 4 e 5.
            }
        }
        return true;
    }
}
