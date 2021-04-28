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

    //Return the player
    Table table;
    private TurnState turnState;
    private Player currPlayer;
    /**
     * Change player.
     *
     * @param original  the original
     * @param newPlayer the new player
     */
/*
    Replace the new player in the position of the orignal one ( when a new player with new powers gets created)
    @param the original player that is going to be substituted
    @param the new player reference
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

    /**valid
     * La notify riceve dal player una lista di Request. Per ogni request il metodo notify() controlla,
     * chiamando sulla request il metodo validRequest(), che l'azione che
     * vuole compiere sia compatibile con il turnState corrente del Game.
     * La notify chiama il metodo canBePlayed che le restituirà la pozione su cui si troverà il Player sul FaithPath, con
     * tale posizione notificherà tutti gli altri player per chiedergli loro come di comporteranno di conseguenza
     */
    public void notify(ArrayList<Request> requests) {
        for (Request req : requests) {
            if (req.validRequest(turnState, currPlayer)) {
                turnState = req.nextTurnState();
                if (req.canBePlayed(currPlayer)) {
                    req.handle();
                    fpAdvancement(req);
                }
            }
        }
    }

    private void fpAdvancement(Request req) {
        if(req.getDiscardedSteps() !=0 ) {
            for(Player player : players) {
                if (player != currPlayer) {
                    player.getBoard().getFaithPath().moveForward(req.getDiscardedSteps());
                }
            }
        }
        if (req.getMyFPSteps() !=0) {
            currPlayer.getBoard().getFaithPath().moveForward(req.getMyFPSteps());
        }
    }
}
