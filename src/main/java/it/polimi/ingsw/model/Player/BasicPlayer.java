package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.connection.ProduceRequest;
import it.polimi.ingsw.model.Board.Board;
import it.polimi.ingsw.model.Cards.DevCard;
import it.polimi.ingsw.model.Cards.LeaderCard;
import it.polimi.ingsw.model.Table.Table;

import java.util.ArrayList;

/**
 * The type Basic player.
 */
public class BasicPlayer extends Player {
    private String nickName;
    private Board board;
    private ArrayList<LeaderCard> leaderCards;
    private int victoryPoints;
    private Table table;
    private Player original; //Even if this attribute is in the Player class for not rewriting all the code, it's never being used in this class

    /**
     * Instantiates a new Basic player.
     */
    public BasicPlayer(Table tb)
    {
        table = tb;
        original=null;
        board = new Board();
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
     * Gets dev card.
     *
     * @param color the color
     * @param level the level
     */
//when player chooses the card, this method buys the card and set the card into the slot
    public void getDevCard(String color, int level)
    {
        DevCard card;
        int slot=2;

        //Dev'essere cambiato in modo che sia gestito in qualche modo dal game, tipo assegnando al giocatore un riferimento al game in cui si trova
        card = table.buyDev(color, level);
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

    public void produce(ArrayList<ProduceRequest> requests)
    {
        //for (ProduceRequest r : requests )
            //if(getBoard().getSlot().getFrontCards().contains(r.getCard());
            //ALlora lo usa sostituendo a ogni richiesta un valore di r.getChoiche
        //per ogni richiesta di produzione (dalla connection) attiva la giusta carta e salva la produzione
        //nel forziere del giocatore
    }
}
