package it.polimi.ingsw.controller;

import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Table;

import java.util.ArrayList;

/**
 * The type Game.
 */
public class Game {

    /**
     * The Players.
     */
    ArrayList<Player> players;


    private Table table;
    private TurnState turnState;
    private Player currPlayer;

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
    public void notify(ArrayList<Request> requests) {
        int playerSteps = 0;
        int discardedSteps = 0;

        for (Request req : requests) {
            if (req.validRequest(turnState, currPlayer)) {
                if (req.canBePlayed(currPlayer)) {
                    turnState = req.nextTurnState();
                    req.handle();
                    discardedSteps += req.getDiscardedSteps();
                    playerSteps += req.getMyFPSteps();
                }
            }
        }
        fpAdvancement(discardedSteps, playerSteps);
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
        if(discardedSteps !=0 ) {
            for(Player player : players) {
                if (player != currPlayer) {
                    player.getBoard().getFaithPath().moveForward(discardedSteps);
                }
                //qui si dovrebbe chiamare la CheckPope e checkVatican per ogni giocatore
            }
        }
        if (playerSteps !=0) {
            currPlayer.getBoard().getFaithPath().moveForward(playerSteps);
            //qui si dovrebbe chiamare la CheckPope e checkVatican
        }
    }
}
