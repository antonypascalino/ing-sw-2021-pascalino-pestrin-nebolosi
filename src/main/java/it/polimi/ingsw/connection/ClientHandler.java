package it.polimi.ingsw.connection;

import com.google.gson.Gson;
import it.polimi.ingsw.Request.JoinGameRequest;
import it.polimi.ingsw.Request.NewGameRequest;
import it.polimi.ingsw.Request.PongRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.SinglePlayer.SinglePlayerGame;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Updates.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class ClientHandler extends Thread {

        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private GameHolder games;
        //Each clientHandler has a playerId so it's sure the requests comes from the right socket
        public String playerId;
        private Game thisGame; //Game to which the player is connected
        Gson json;

        public ClientHandler(Socket socket, GameHolder games) throws IOException {
            json = new Gson();
            this.games = games;
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);

            this.start();

        }

        public void setPlayerId(String playerId)
        {
            this.playerId = playerId;
        }
        @Override
        public void run() {

            PingConnection pingConnection = null;
            try {
                pingConnection = new PingConnection(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread ping = new Thread(pingConnection);
            ping.start();

            try
            {
                while (true)
                {
                    socket.setSoTimeout(20000);
                    String line = null;
                    line = in.readLine() ;
                    if(line != null) {
                        Request request = JsonReader.readSingleRequest(line);
                        //It doesn't exist the idea of a new game or join game, there's just a join game that creates a game
                    /*if(request instanceof NewGameRequest)
                    {
                        int playerNum = ((NewGameRequest) request).getPlayers();
                        ArrayList<Player> tmp = new ArrayList<Player>();
                        Player first = new BasicPlayer(((NewGameRequest) request).getNickname());
                        tmp.add(first);

                        //Deciding the new game id
                        int newId;
                        if(games.size() == 0)
                            newId = 0;
                        else
                            newId = games.get(games.size()-1).getGameId() + 1;
                        Game game = new Game(tmp, DefaultCreator.produceDevCard(),newId, playerNum);
                        games.add(game);
                        String response = "new game created with ID: " + newId;
                        System.out.println(response);
                        out.println(response);
                    }

                    else if(request instanceof JoinGameRequest)
                    {
                        int gameId = ((JoinGameRequest) request).getGameId();
                        String nickName = ((JoinGameRequest) request).getNickName();
                        //Return the first occuracy of the game, if there's not return not found
                        Game game = games.stream().filter(game1 -> (game1.getGameId() == gameId)).findFirst().orElse(null);
                        if(game == null)
                        {
                            System.out.println("Game not found");
                            //Null game
                        }
                        else
                        {
                            Player newPlayer= new BasicPlayer(nickName);
                            game.addPlayer(newPlayer);
                            System.out.println("Player "+nickName+" added to game "+ gameId);
                        }
                    }*/
                        if (request instanceof NewGameRequest) {
                            games.add(request,this);
                        } else {
                            //If i recive a pong request ignore it
                            if(!(request instanceof PongRequest))
                            {
                                System.out.println("Received "+request);
                                games.get(request.getGameID()).notify(request);
                            }

                        }
                    }
                }
                //close connections
                //in.close();
                //out.close();
                //socket.close();
            //If the client is dead
            } catch (SocketException e){
                System.err.println(e.getMessage());
                System.out.println("Player "+playerId+" disconnected");
                thisGame.notifyAllPlayers(new ConnectionErrorUpdate(playerId));
                games.remove(thisGame);
            } catch (SocketTimeoutException e) {
                System.err.println(e.getMessage());
                System.out.println("Player "+playerId+"disconnected");
                thisGame.notifyAllPlayers(new ConnectionErrorUpdate(playerId));
                games.remove(thisGame);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        //Synchronyzed because it's used by both thread (once for the actual game and another one for checking the status
        public synchronized void notifyView(Update update)
        {
            String message = json.toJson(update);
            out.println(message);
            out.flush();
        }

    public void setGame(Game newGame) {
            thisGame = newGame;
    }
}



