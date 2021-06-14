package it.polimi.ingsw.connection;

import it.polimi.ingsw.controller.Game;

import java.util.ArrayList;

//This class is used so that the creation of a new game is synchronized
public class GameHolder {
    private ArrayList<Game> games;

    public GameHolder()
    {
        games = new ArrayList<>();
    }

    public synchronized int size()
    {
        return games.size();
    }
    public synchronized void addGame(Game game)
    {
        games.add(game);
    }

    public synchronized Game get(int i)
    {
        return games.get(i);
    }

    public synchronized void remove(Game game)
    {
        games.remove(game);
    }

    public synchronized void add(Game game)
    {
        games.add(game);
    }

    /**
     * Adding a new player to an existing game or creating a new game
     * @param playerId
     */
    public void add(String playerId) {
    }
}
