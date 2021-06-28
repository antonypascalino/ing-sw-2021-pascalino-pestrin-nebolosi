package it.polimi.ingsw.connection;

import it.polimi.ingsw.Request.NewGameRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.SinglePlayer.SinglePlayerGame;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Updates.ExistingPlayerUpdate;
import it.polimi.ingsw.model.Updates.LobbyUpdate;
import it.polimi.ingsw.model.Updates.Update;

import java.util.ArrayList;

//This class is used so that the creation of a new game is synchronized
public class GameHolder {
    private ArrayList<Game> games;

    public GameHolder() {
        games = new ArrayList<>();
    }

    public synchronized int size() {
        return games.size();
    }

    public synchronized Game get(int i) {
        return games.get(i);
    }

    public synchronized void remove(Game game) {
        games.remove(game);
    }

    public synchronized void add(Game game) {
        games.add(game);
    }

    /**
     * Adding a new player to an existing game or creating a new game
     *
     * @param request the request used for creating a player
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
                newGame = new Game(tmp, DefaultCreator.produceDevCard(), gameId, ((NewGameRequest) request).getPlayers());
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
