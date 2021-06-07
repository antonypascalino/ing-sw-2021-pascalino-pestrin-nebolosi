package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.connection.ClientHandler;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Board.Board;
import it.polimi.ingsw.model.Updates.Update;
import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.card.LeaderCard;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Table.Table;

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
    private ClientHandler thisPlayer;
    private Game game;

//    public BasicPlayer(String nickName, Table table, ClientHandler thisPlayer) {
//        this.nickName = nickName;
//        this.board = new Board(this);
//        this.victoryPoints = 0;
//        this.table = table;
//        leaderCards = new ArrayList<LeaderCard>();
//    }

    //Constructor used for debugging without connection
    public BasicPlayer(String nickName) {
        leaderCards = new ArrayList<LeaderCard>();
        this.nickName = nickName;
        this.board = new Board(this);
    }

    public BasicPlayer(String nickname, ClientHandler thisPlayer) {
        leaderCards = new ArrayList<LeaderCard>();
        this.nickName = nickname;
        this.board = new Board(this);
        this.thisPlayer = thisPlayer;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setTable(Table tbl)
    {
        this.table = tbl;
    }

    public void setGame(Game game)
    {
        this.game = game;
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
        if (!(level <= 2 && level >= 0)) {
            return false; //lancia eccezione perché non ha carte che aggiungono livelli e quindi non ha livelli 3 e 4 (indici di programmazione non indici in linguaggio naturale).
        }
        return true;
    }

    @Override
    public void switchLevels(int originLevel, int destLevel) {
        board.getWareHouse().switchLevels(originLevel, destLevel);
    }

    @Override
    public void addResource(int level, Resource res) {
        board.getWareHouse().addResource(level, res);
    }

    @Override
    public void removeResource(Resource res, String place) {
        //If the player doesn't have the resources throw a new exception
        if (place.toLowerCase().equals("strongbox")) {
            board.getStrongBox().removeResource(res);
        } else if (place.toLowerCase().equals("warehouse")) {
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

    public Table getTable()
    {
        return table;
    }

    @Override
    public ArrayList<String> getLeadersID() {
        ArrayList<String> leadersID = new ArrayList<String>();
        for (LeaderCard leader : leaderCards) {
            leadersID.add(leader.getID());
        }
        return leadersID;
    }

    @Override
    public ArrayList<Resource[]> getDeposits() {
        return board.getWareHouse().getArrayListWareHouse();
    }

    /**
     * Sending an update to the view after the request is done
     * The requests calls the game and the game calls this method on each player connected to the game
     */
    @Override
    public void notifyView(Update update)
    {
        thisPlayer.notifyView(update);
    }

    @Override
    public Game getGame()
    {
        return game;
    }

}
