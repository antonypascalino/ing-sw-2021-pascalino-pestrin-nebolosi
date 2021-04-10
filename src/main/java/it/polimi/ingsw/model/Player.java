package it.polimi.ingsw.model;
import java.util.ArrayList;

public class Player {

    private String nickName;
    private int victoryPoints;
    private int faithPoint;
    ArrayList<Resource> discounts;  //Dovrebbe essere friendly
    ArrayList<Resource> emptyValue;                             //Dovrebbe essere friendly
    private Board board;
    private ArrayList<LeaderCard> leaderCards;

    public Player()
    {
        board = new Board();
        discounts = new ArrayList<Resource>();
        emptyValue = new ArrayList<Resource>();
        leaderCards = new ArrayList<LeaderCard>();
        faithPoint = 0;
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
