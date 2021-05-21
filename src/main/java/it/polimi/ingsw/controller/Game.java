package it.polimi.ingsw.controller;

import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.model.Cards.DevCard;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Table;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The type Game.
 */
public class Game {

    private int maxPlayer;
    ArrayList<Player> players;
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
        this.table = new Table(cards);
        this.turnStates = new ArrayList<TurnState>();
        this.currPlayer = players.get(0);
        this.currPopeSpace = 1;
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
        if (req.validRequest(turnStates)) {
            if (req.canBePlayed(currPlayer)) {
                turnStates.add(req.nextTurnState());
                req.handle(currPlayer);
                if (req.getDiscardedSteps() != 0 || req.getMyFPSteps() != 0) {
                    fpAdvancement(discardedSteps, playerSteps);
                }
                if (turnStates.contains(TurnState.END_TURN)) {
                    turnStates.clear();
                }
                if (req.getPlayerChoices() != 0) {
                    //chiama model che chiama view che fa scegliere al player le risorse
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
    private void fpAdvancement(int discardedSteps, int playerSteps) {
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
        }
        this.fpAdvancement(0,0); //Richiama se stessa per verificare se qualche giocatore abbia superato più di una popeSpace
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
}
