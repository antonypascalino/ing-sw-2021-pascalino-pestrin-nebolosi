package it.polimi.ingsw.controller;

import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.model.Updates.NewGameUpdate;
import it.polimi.ingsw.model.Updates.PlayerLC;
import it.polimi.ingsw.model.Updates.Update;
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

    private int maxPlayer;
    private ArrayList<Player> players;
    private Table table;
    private ArrayList<TurnState> turnStates;
    private Player currPlayer;
    private int currPopeSpace;
    private final int gameId;
    private boolean lastTurn;
    private ArrayList<Player> lastRound;

    public Game(ArrayList<Player> players, ArrayList<DevCard> cards, int gameId, int maxPlayer) {
        this.maxPlayer = maxPlayer;
        this.gameId = gameId;
        this.players = players;
        Collections.shuffle(cards);
        this.table = new Table(cards);
        //Set the table on all the players
        for(Player player : players)
            player.setTable(table);
        this.turnStates = new ArrayList<TurnState>();
        this.currPlayer = players.get(0);
        this.currPopeSpace = 1;
        //Chiama metodo che crea Update per inviare a tutti i giocatori la situazione iniziale del Table, del Market e assegna loro 4 LeaderCard che poi dovranno selezonare lato client
        //Chiamata messa provvisoriamente nel costruttore
        createNewGameUpdate();
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

        //BISOGNA AGGIUNGERE UN METODO CHE CONTROLLI CHE IL PLAYER CHE HA INVIATO LA REQUEST SIA IL CURRENT PLAYER
        if (/*req.validRequest(turnStates)*/true) {
            if (req.canBePlayed(currPlayer)) {
                turnStates.add(req.handle(currPlayer ,this));
                if (turnStates.contains(TurnState.END_TURN)) {
                    turnStates.clear();
                }

                if(lastTurn) {
                    lastRound.add(currPlayer);
                    if (lastRound.containsAll(players)) {
                        endgame();
                    }
                }
                if((currPlayer.getBoard().getSlot().getAllCards().size() == 7 || currPlayer.getBoard().getFaithPath().checkPopeSpace(3)) && !lastTurn) {
                    lastTurn = true;
                    lastRound = new ArrayList<Player>();
                    lastRound.add(currPlayer);
                }
            }
        }

    }

    public synchronized void updatePlayers(Update update)
    {
        for(Player p : players)
            p.notifyView(update);
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

    private String endgame() {
        int winnerPoints = 0;
        String winnerNickname = null;
        for (Player player : players) {
            player.addVictoryPoints((int)player.getAllResources().size()/5);
            if (player.getVictoryPoints() > winnerPoints) {
                winnerPoints = player.getVictoryPoints();
                winnerNickname = player.getNickName();
            }
        }
        return winnerNickname;
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

    private Update createNewGameUpdate() {
        ArrayList<LeaderCard> allLeaderCards = DefaultCreator.produceLeaderCard(); //Produce tutte le Leader del gioco
        Collections.shuffle(allLeaderCards); //Le mischia

        //Crea un elenco di players e attibuisce ad ognungo di loro 4 leaderCard diverse
        ArrayList<PlayerLC> playersLC = new ArrayList<PlayerLC>();
        for (Player player : players) {
            ArrayList<String> leadersToChoose = new ArrayList<String>();
            for (int addedCard = 0; addedCard <= 4; addedCard++) {
                leadersToChoose.add(allLeaderCards.remove(0).getID());
                //Qui si potrebbe aggiungere anche la carta al player nel model e poi la request successiva ne rimuoverebbe 2
                //Oppure (come ora) non aggiungerle al player nel model ma nelle request successiva aggiungere le uniche 2
            }
            playersLC.add(new PlayerLC(player.getNickName(), leadersToChoose));
        }
        return new NewGameUpdate(table.getFrontIDs(), table.market.getMarket(), playersLC);
    }
}
