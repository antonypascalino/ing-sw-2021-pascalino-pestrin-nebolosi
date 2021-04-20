package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

/**
 * The type Game.
 */
public class Game {

    /**
     * The Players.
     */
    ArrayList<Player> players;

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
}
