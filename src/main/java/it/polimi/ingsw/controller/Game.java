package it.polimi.ingsw.controller;

import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.model.Cards.DevCard;
import it.polimi.ingsw.model.Cards.LeaderCard;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * The type Game.
 */
public class Game {

    private ArrayList<Player> players; //The players

    private Table table; //The gaming table
    private TurnState turnState;
    private int turn; //Rappresent with an int the player currently playing

    /**
     * Once the lobby is ready and the player select to start the game it instate a new game with all the information.
     * It doesn't need the leader cards because it all need
     * Set the turn state and the player to the first one
     *
     * @param players the list with all the players
     * @param devCards the Array with all the devCards
     */
    public Game(ArrayList<Player> players, ArrayList<DevCard> devCards)
    {
        Collections.shuffle(devCards);
        this.players = players;
        table = new Table(devCards);
        turnState = TurnState.Initial;
        turn = 0;
    }
    /**
    Replace the new player in the position of the orignal one ( when a new player with new powers gets created)
    @param original the original player that is going to be substituted
    @param newPlayer the new player reference
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
    public void notify(ArrayList<Request> requests)
    {
        //for(request r)
        //    if(request.validRequest(turnstate,player))
        //      turnState = request.handle() BOZZA
    }
}
