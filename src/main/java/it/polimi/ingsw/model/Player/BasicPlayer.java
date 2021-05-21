package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.Request.Dimension;
import it.polimi.ingsw.Request.MarketRequest;
import it.polimi.ingsw.Request.ProduceRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.controller.MappedResource;
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
    private Table table;
    private Player original; //Even if this attribute is in the Player class for not rewriting all the code, it's never being used in this class

    /**
     * Instantiates a new Basic player.
     *
     *
     */
    public BasicPlayer(String nickName, Table table) {
        this.nickName = nickName;
        this.board = new Board(this);
        this.victoryPoints = 0;
        this.table = table;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * When player chooses a card, this method buys the card and sets the card into the given slot
     *
     * @param color dev card color
     * @param level dev card level
     */

    //DA RIVEDERE TUTTO
    public void getDevCard(String color, int level) {
        DevCard card = null;
        int slot = 2;

        //Dev'essere cambiato in modo che sia gestito in qualche modo dal game, tipo assegnando al giocatore un riferimento al game in cui si trova
        //card = table.buyDev(color, level);
        if (board.hasResources(card.getPrice()))

            card.setOwner(this);
        //richiesta al giocatore in quale slot mettere la card
        board.getSlot().placeCard(card, slot);
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    public void addLeaderCard(LeaderCard card) {
        leaderCards.add(card);
    }

    public void addVictoryPoints(int victoryPoints) {
        this.victoryPoints += victoryPoints;
    }

    public void getProduction() {
        for (DevCard dev : board.getSlot().getFrontCards())
            //Used for giving the power of all cards to the view
            //dev.getPower();
            System.out.println("Debug");
    }

    /**
     * Produces the resources (work in progress)
     *
     * @param cardID Card ID
     */
    public void produce(String cardID) {

        if(cardID.contains("dev")){
            getBoard().getTempBox().addResource(getBoard().getDevFromID(cardID).producedResources());

        }
        if(cardID.contains("basic")){
            ArrayList<Resource> basicProd = new ArrayList<Resource>();
            basicProd.add(Resource.CHOICE);
            basicProd.add(Resource.CHOICE);
            getBoard().getTempBox().addResource(basicProd);
        }

        //for (ProduceRequest r : requests )
        //if(getBoard().getSlot().getFrontCards().contains(r.getCard());
        //ALlora lo usa sostituendo a ogni richiesta un valore di r.getChoiche
        //per ogni richiesta di produzione (dalla connection) attiva la giusta carta e salva la produzione
        //nel forziere del giocatore
    }

    @Override
    public boolean checkSpace(Resource res, int level) {
        return board.getWareHouse().checkSpace(level, res);
    }

    @Override
    public boolean checkMarketRes(ArrayList<Resource> requestedRes, ArrayList<Resource> marketRes) {
        for (int i = 0; i < marketRes.size(); i++) {
            if (!marketRes.get(i).equals(requestedRes.get(i))) {
                //lancia eccezione: le risorse richieste e le risorse del mercato non corrispondono e non hai changes
            }
        }
        return true;
    }

    @Override
    public boolean checkLevel(int level) {
        if (!(level <= 3 && level > 0)) {
            //lancia eccezione perché non ha carte che aggiungono livelli e quindi non ha livelli 4 e 5.
        }
        return true;
    }

    @Override
    public void switchLevels(Resource res, int orLevel, int finLevel) {
        board.getWareHouse().switchLevels(res, orLevel, finLevel);
    }

    @Override
    public void addResource(int level, Resource res) {
        board.getWareHouse().addResource(level, res);
    }

    @Override
    public void removeResource(Resource res, String place) {
        //If the player doesn't have the resources throw a new exception
        if (place.equals("strongbox")) {
            board.getStrongBox().removeResource(res);
        } else if (place.equals("warehouse")) {
            board.getWareHouse().removeResource(res);
        //lancia eccezione: non hai questo posto da dove prendere la risorsa
        }
    }



    @Override
    public ArrayList<Resource> getAllResources() {
        ArrayList<Resource> tmp = new ArrayList<Resource>();
        tmp.addAll(this.getBoard().getStrongBox().getResources());
        tmp.addAll(this.getBoard().getWareHouse().getResources());
        return tmp;
    }

    @Override
    public boolean canBuy(DevCard devCard, ArrayList<Resource> allPlayerRes) {
        if (allPlayerRes.containsAll(devCard.getPrice())) {
            return true;
        }
        //else lancia eccezione: non hai risorse per comprare questa carta.
        return false;
    }

    @Override
    public LeaderCard getLeaderFromID(String cardID){
        for(LeaderCard leader : leaderCards){
            if(cardID.equals(leader.getID())){
                return leader;
            }
        }
        return null; //lancia eccezione ("non hai la leader card")
    }

    @Override
    public ArrayList<String> getProductionID() {
        return board.getProdID();
    }
}
