package it.polimi.ingsw.model;

import java.util.ArrayList;

public class BasicPlayer extends Player {
    private String nickName;
    private Board board;
    private ArrayList<LeaderCard> leaderCards;
    private int victoryPoints;
    private Player original; //Even if this attribute is in the Player class for not rewriting all the code, it's never being used in this class

    public BasicPlayer()
    {
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

    //when player chooses the card, this method buys the card and set the card into the slot
    public void getDevCard(String color, int level)
    {
        DevCard card;
        int slot;

        //Dev'essere cambiato in modo che sia gestito in qualche modo dal game, tipo assegnando al giocatore un riferimento al game in cui si trova
        card = Table.buyDev(color, level);
        if(board.hasResources(card.getPrice()))

            card.setOwner(this);
        //richiesta al giocatore in quale slot mettere la card
        board.getSlot().purchaseCard(card,slot);
    }

    public Board getBoard() {
        return board;
    }

    public void addLeaderCard (LeaderCard card)
    {
        leaderCards.add(card);
    }

    public void addVictoryPoints (int victoryPoints)
    {
        this.victoryPoints += victoryPoints;
    }
}
