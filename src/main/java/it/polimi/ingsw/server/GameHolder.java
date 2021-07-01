package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.Request.NewGameRequest;
import it.polimi.ingsw.controller.Request.Request;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.SinglePlayer.SinglePlayerGame;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.Updates.ExistingPlayerUpdate;
import it.polimi.ingsw.Updates.LobbyUpdate;
import it.polimi.ingsw.Updates.Update;

import java.util.ArrayList;

/**
 * The lobby containing every game on the server
 */
//This class is used so that the creation of a new game is synchronized
public class GameHolder {
    private ArrayList<Game> games;

    /**
     * Instantiates a new Game holder.
     */
    public GameHolder() {
        games = new ArrayList<>();
    }

    /**
     * Size the size of the games
     *
     * @return the int
     */
    public synchronized int size() {
        return games.size();
    }

    /**
     * Get game.
     *
     * @param i the
     * @return the game
     */
    public synchronized Game get(int i) {
        return games.get(i);
    }

    /**
     * Remove a game
     *
     * @param game the game
     */
    public synchronized void remove(Game game) {
        games.remove(game);
    }

    /**
     * Add a game
     *
     * @param game the game
     */
    public synchronized void add(Game game) {
        games.add(game);
    }

    /**
     * Adding a new player to an existing game or creating a new game
     *
     * @param request       the request used for creating a player and the new game
     * @param clientHandler the client handler
     */
    public synchronized void add(Request request, ClientHandler clientHandler) {
        //If there's no game on the server create the first one
        Game lastGame = null;
        if (games.size() != 0)
            lastGame = games.get(games.size() - 1);
        //If there's no game or the last one has reached the maximum player, it doesn't check it if games.size==0
        //Create a new game
        if (games.size() == 0 || !(lastGame.getPlayers().size() < lastGame.getMax())) {
            int gameId;
            if (games.size() != 0)
                gameId = games.get(games.size() - 1).getGameId() + 1;
            else
                gameId = 0;
            ArrayList<Player> tmp = new ArrayList<>();
            clientHandler.setPlayerId(((NewGameRequest) request).getNickname());
            Player newPlayer = new BasicPlayer(((NewGameRequest) request).getNickname(), clientHandler);
            tmp.add(newPlayer);
            Game newGame;
            Update update;
            if (((NewGameRequest) request).getPlayers() == 1) {
                newGame = new SinglePlayerGame(tmp, DefaultCreator.produceDevCard(), gameId);
                update = newGame.createNewGameUpdate();
            } else {
                //If the client requires a number of player above 4, creates a game with 4 players
                if(((NewGameRequest) request).getPlayers()<=4 && ((NewGameRequest) request).getPlayers() >=2)
                    newGame = new Game(tmp, DefaultCreator.produceDevCard(), gameId, ((NewGameRequest) request).getPlayers());
                else
                    newGame = new Game(tmp, DefaultCreator.produceDevCard(), gameId, 4);
                update = new LobbyUpdate(((NewGameRequest) request).getNickname(), newGame.getPlayers().size(), ((NewGameRequest) request).getPlayers());

            }
            clientHandler.setGame(newGame);
            newPlayer.setGame(newGame);
            newPlayer.setTable(newGame.getTable());
            games.add(newGame);
            System.out.println("Player " + ((NewGameRequest) request).getNickname() + " added to the new game " + newGame.getGameId());
            newGame.notifyAllPlayers(update);
        }

        //If it hasn't already reached the maximum number of players
        //add the new player
        else {
            Update update;
            //If there's already a player with the new name return an error
            for(Player p: lastGame.getPlayers())
            {
                if(p.getNickName().equals(((NewGameRequest) request).getNickname()))
                {
                    update = new ExistingPlayerUpdate();
                    clientHandler.notifyView(update);
                    return;
                }

            }
            Player newPlayer = new BasicPlayer(((NewGameRequest) request).getNickname(), clientHandler);
            clientHandler.setPlayerId(newPlayer.getNickName());
            lastGame.addPlayer(newPlayer);
            clientHandler.setGame(lastGame);
            newPlayer.setTable(lastGame.getTable());
            newPlayer.setGame(lastGame);
            System.out.println("Player " + ((NewGameRequest) request).getNickname() + " added to game " + lastGame.getGameId());

            //If the game has reached the max level of players with this new one
            if (lastGame.getPlayers().size() == lastGame.getMax()) {
                update = lastGame.createNewGameUpdate();
            } else {
                System.out.println("Player " + ((NewGameRequest) request).getNickname() + " added to game " + lastGame.getGameId());
                update = new LobbyUpdate(((NewGameRequest) request).getNickname(), lastGame.getPlayers().size(), ((NewGameRequest) request).getPlayers());
            }
            lastGame.notifyAllPlayers(update);
        }
    }
}
