package model;
import java.util.;

public class Player {

    private String nickName;
    private int victoryPoints;
    private int faithPoint;
    ArrayList<Resource> discounts; //Dovrebbe essere friendly
    ArrayList<Resource> emptyValue; //Dovrebbe essere friendly
    private boolean hasChange; //Se non sbaglio lo avevamo tolto e usavamo emptyValue però nell'UML c'è ancora
    private Board board;
    private ArrayList<LeaderCard> leaderCards;


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

    public getFromMarket(ArrayList<Resource> res)
    {

        // Una volta ricevute le risorse (biglie) dal mercato, vanno messe nel WareHouse;

        //Chiama la View per chiedere al giocatore in quale livello vuole mettere le risorse,

        WareHouse.addResources(/*metodo view che restitusice livello*/, res);

        //Bisogna vedere dove inserire la chiamata a switchLevels nel caso in cui fosse necessario spostare le risorse

    }
    //when player chooses the card this method buys the card and set the card into the slot
    public void getDevCard(String color, int level)
    {
        DevCard card;
        int slot;

        card = Table.buyDev(color, level);
        //richiesta al giocatore in quale slot mettere la card
        purchaseCard(card,slot);
    }
}
