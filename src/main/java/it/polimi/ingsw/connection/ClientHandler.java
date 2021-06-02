package it.polimi.ingsw.connection;

import com.google.gson.Gson;
import it.polimi.ingsw.Request.JoinGameRequest;
import it.polimi.ingsw.Request.NewGameRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Updates.Update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private ArrayList<Game> games;
        //Each clientHandler has a playerId so it's sure the requests comes from the right socket
        public String playerId;

        public ClientHandler(Socket socket, ArrayList<Game> games) throws IOException {
            this.games = games;
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        }

        @Override
        public void run() {
            try
            {
                while (true)
                {
                    String line = in.readLine() ;
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
                    if(request instanceof NewGameRequest)
                    {
                        //If there's no game on the server create the first one
                        Game lastGame = null;
                        if (games.size() != 0)
                            lastGame = games.get(games.size()-1);
                        //If there's no game or the last one has reached the maximum player, it doesn't check it if games.size==0
                        //Create a new game
                        if (games.size() == 0 || !(lastGame.getPlayers().size() < lastGame.getMax()))
                        {
                            int gameId;
                            if(games.size() != 0)
                                gameId = games.get(games.size()-1).getGameId() +1;
                            else
                                gameId=0;
                            ArrayList<Player> tmp = new ArrayList<Player>();
                            this.playerId = ((NewGameRequest) request).getNickname();
                            tmp.add(new BasicPlayer(((NewGameRequest) request).getNickname(),this));
                            Game newGame = new Game(tmp,DefaultCreator.produceDevCard(),gameId,((NewGameRequest) request).getPlayers());
                            games.add(newGame);
                            System.out.println("Player "+((NewGameRequest) request).getNickname()+ " added to the new game "+newGame.getGameId());
                            out.println("Player "+((NewGameRequest) request).getNickname()+ " added to the new game "+newGame.getGameId());
                            out.flush();
                        }
                        //If it hasn't alrady reached the maximum numner of players
                        //add the new player
                        else
                        {
                            Player newPlayer = new BasicPlayer(((NewGameRequest) request).getNickname());
                            lastGame.addPlayer(newPlayer);
                            System.out.println("Player "+((NewGameRequest) request).getNickname()+ " added to game "+lastGame.getGameId());

                        }

                    }
                    else {
                        out.println(line);
                        out.flush();
                    }
                }
                //close connections
                //in.close();
                //out.close();
                //socket.close();
            } catch (IOException e){
                System.err.println(e.getMessage());
            }
        }

        public void notifyView(Update update)
        {
            Gson json = new Gson();
            String message = json.toJson(update);
            out.println(message);
        }
}



