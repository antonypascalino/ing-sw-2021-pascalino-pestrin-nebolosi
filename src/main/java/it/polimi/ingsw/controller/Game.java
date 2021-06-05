package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Request.InitialPlayersSetRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.model.Updates.*;
import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Table;
import it.polimi.ingsw.model.card.LeaderCard;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Game.
 */
public class Game {

    public int maxPlayer;
    private ArrayList<Player> players;
    private int currPlayerInt;
    private Table table;
    private ArrayList<TurnState> turnStates;
    private Player currPlayer;
    private Player nextPlayer;
    private int currPopeSpace;
    private final int gameId;
    private boolean lastTurn;
    public int playerReady; //Players ready to start the game that have choosen their leaderCards

    public Game(ArrayList<Player> players, ArrayList<DevCard> cards, int gameId, int maxPlayer) {
        playerReady = 0;
        currPlayerInt = 0;
        currPlayer = players.get(currPlayerInt);
        this.maxPlayer = maxPlayer;
        this.gameId = gameId;
        this.players = players;
        Collections.shuffle(cards);
        this.table = new Table(cards);
        this.turnStates = new ArrayList<TurnState>();
        this.currPlayer = players.get(0);
        this.currPopeSpace = 1;
        //Chiama metodo che crea Update per inviare a tutti i giocatori la situazione iniziale del Table, del Market e assegna loro 4 LeaderCard che poi dovranno selezonare lato client
        //Chiamata messa provvisoriamente nel costruttore
    }

    public int getGameId()
    {
        return gameId;
    }
    /**
     * Replace the new player in the position of the orignal one ( when a new player with new powers gets created)
     *
     * @param original player that is going to be substituted
     * @param newPlayer new player reference
     */
    public void changePlayer(Player original, Player newPlayer)
    {
        int pos = players.indexOf(original);
        players.set(pos, newPlayer);
    }

    public Table getTable()
    {
        return table;
    }

    /**v
     * La notify riceve dal player una lista di Request. Per ogni request il metodo notify() controlla,
     * chiamando sulla request il metodo validRequest(), che l'azione che
     * vuole compiere sia compatibile con il turnState corrente del Game.
     * La notify chiama il metodo canBePlayed che le restituirà la pozione su cui si troverà il Player sul FaithPath, con
     * tale posizione notificherà tutti gli altri player per chiedergli loro come di comporteranno di conseguenza
     */
    public synchronized void notify(Request req) {

        int playerSteps = 0;
        int discardedSteps = 0;

        //In the case the game is starting every game can send the request
        if (req.getPlayerID().equals(currPlayer.getNickName())  || req instanceof InitialPlayersSetRequest) {
            if (/*req.validRequest(turnStates)*/true) {
                if (req.canBePlayed(currPlayer)) {
                    turnStates.add(req.handle(getPlayerFromID(req.getPlayerID()), this));


                    //Check if the game is finished
                    if (lastTurn) {
                        if (currPlayerInt == 0) {
                            endgame();
                        }
                    }
                    if ((currPlayer.getBoard().getSlot().getAllCards().size() == 7 || currPlayer.getBoard().getFaithPath().checkPopeSpace(3)) && !lastTurn) {
                        lastTurn = true;
                    }
                    if (turnStates.contains(TurnState.END_TURN)) {
                        turnStates.clear();
                        currPlayer = nextPlayer;
                    }
                }
            }
        } else {
            Update error = new ErrorUpdate("It's not your turn", req.getPlayerID());
        }
    }

    /**
     * Calls all the player, different by the curr player, to make them moveForward on their FaithPath of a number
     * of steps equal to the discarded resources by the current player in this turn.
     * <p>
     * Also call the current player to make him moveForward of a number of steps equal to the number of all FAITH
     * resources obtained by the player in his turn.
     *
     * @param discardedSteps the number of FAITH resources discarded by the current player that make other players move
     * @param playerSteps the number of FAITH resources obtained by the player in his turn
     */
    public void fpAdvancement(int discardedSteps, int playerSteps) {
        //Sposta gli altri giocatori per le risorse scartate dal current player
        if (discardedSteps != 0) {
            for (Player player : players) {
                if (player != currPlayer) {
                    player.getBoard().getFaithPath().moveForward(discardedSteps);
                }
            }
        }
        //Sposta il curr player per i FAITH ottenuti nel suo turno
        if (playerSteps != 0) {
            currPlayer.getBoard().getFaithPath().moveForward(playerSteps);
        }

        boolean popeSpace = false;
        //Controlla se, a seguito dei movimenti di tutti, qualcuno ha raggiunto la popeSpace corrente
        for (Player player : players) {
            if (player.getBoard().getFaithPath().checkPopeSpace(currPopeSpace)) {
                popeSpace = true;
                break;
            }
        }
        //Se qualcuno ha raggiunto la pope space corrente chiamo la checkVaticanSection per tutti
        if (popeSpace) {
            for (Player player : players) {
                player.getBoard().getFaithPath().checkVaticanSection(currPopeSpace);
            }
            currPopeSpace++;
            this.fpAdvancement(0,0); //Richiama se stessa per verificare se qualche giocatore abbia superato più di una popeSpace
        }
    }

    //Synchronyzed player because two players can't register at the same time
    public synchronized void addPlayer(Player newPlayer) {
        if(players.size() < maxPlayer)
            players.add(newPlayer);
    }

    private void endgame() {
        int winnerPoints = 0;
        String winnerNickname = null;
        for (Player player : players) {
            player.addVictoryPoints((int)player.getAllResources().size()/5);
            if (player.getVictoryPoints() > winnerPoints) {
                winnerPoints = player.getVictoryPoints();
                winnerNickname = player.getNickName();
            }
        }
        new EndgameUpdate(winnerNickname);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getMax() {
        return maxPlayer;
    }

    public ArrayList<TurnState> getTurnStates() {
        return turnStates;
    }

    public Update createNewGameUpdate() {
        ArrayList<LeaderCard> allLeaderCards = DefaultCreator.produceLeaderCard(); //Produce tutte le Leader del gioco
        Collections.shuffle(allLeaderCards); //Le mischia

        //Crea un elenco di players e attibuisce ad ognungo di loro 4 leaderCard diverse
        ArrayList<PlayerLC> playersLC = new ArrayList<PlayerLC>();
        for (Player player : players) {
            player.setTable(table);
            ArrayList<String> leadersToChoose = new ArrayList<String>();
            for (int addedCard = 0; addedCard < 4; addedCard++) {
                leadersToChoose.add(allLeaderCards.remove(0).getID());
                //Qui si potrebbe aggiungere anche la carta al player nel model e poi la request successiva ne rimuoverebbe 2
                //Oppure (come ora) non aggiungerle al player nel model ma nelle request successiva aggiungere le uniche 2
            }
            playersLC.add(new PlayerLC(player.getNickName(), leadersToChoose));
        }

        ArrayList<PlayerST> playersST = new ArrayList<>();
        switch (players.size()) {
            case (1):
                PlayerST player11 = new PlayerST(players.get(0).getNickName(), 0, 0);
                playersST.add(player11);
                break;
            case (2):
                PlayerST player21 = new PlayerST(players.get(0).getNickName(), 0, 0);
                PlayerST player22 = new PlayerST(players.get(1).getNickName(), 1, 0);
                playersST.add(player21);
                playersST.add(player22);
                break;

            case (3):
                PlayerST player31 = new PlayerST(players.get(0).getNickName(), 0, 0);
                PlayerST player32 = new PlayerST(players.get(1).getNickName(), 1, 0);
                PlayerST player33 = new PlayerST(players.get(2).getNickName(), 1, 1);
                players.get(2).addVictoryPoints(1);
                playersST.add(player31);
                playersST.add(player32);
                playersST.add(player33);
                break;

            case (4):
                PlayerST player41 = new PlayerST(players.get(0).getNickName(), 0, 0);
                PlayerST player42 = new PlayerST(players.get(1).getNickName(), 1, 0);
                PlayerST player43 = new PlayerST(players.get(2).getNickName(), 1, 1);
                PlayerST player44 = new PlayerST(players.get(3).getNickName(), 2, 1);
                players.get(2).addVictoryPoints(1);
                players.get(3).addVictoryPoints(1);
                playersST.add(player41);
                playersST.add(player42);
                playersST.add(player43);
                playersST.add(player44);
                break;
        }
        return new NewGameUpdate(this.gameId, table.getFrontIDs(), table.market.getMarket(), playersLC, playersST);
    }

    public int getCurrPlayerInt() {
        return currPlayerInt;
    }

    public void setCurrPlayerInt(int currPlayerInt) {
        this.currPlayerInt = currPlayerInt;
    }

    public Player getCurrPlayer() {
        return currPlayer;
    }

    public void notifyAllPlayers(Update update){
        for (Player p: players)
            p.notifyView(update);
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public Player getPlayerFromID(String playerID) {
        for (Player p : players) {
            if (p.getNickName().equals(playerID)) {
                return p;
            }
        }
        return null;
    }

    public void start() {
        currPlayer = players.get(0);
        turnStates.add(TurnState.PRODUCE);
        turnStates.add(TurnState.MOVE_RESOURCE);
        turnStates.add(TurnState.BUY_DEV_CARD);
        turnStates.add(TurnState.CHECK_STATS);
        notifyAllPlayers(new StartGameUpdate(players.get(0).getNickName()));
    }
}
