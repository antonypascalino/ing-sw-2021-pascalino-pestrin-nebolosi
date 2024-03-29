package it.polimi.ingsw.server;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import it.polimi.ingsw.controller.Request.NewGameRequest;
import it.polimi.ingsw.controller.Request.PongRequest;
import it.polimi.ingsw.controller.Request.Request;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.Updates.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * The type Client handler.
 */
public class ClientHandler extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private GameHolder games;
    /**
     * The Player id.
     */
//Each clientHandler has a playerId so it's sure the requests comes from the right socket
    public String playerId;
    private Game thisGame; //Game to which the player is connected
    /**
     * The Json.
     */
    Gson json;

    /**
     * Instantiates a new Client handler.
     *
     * @param socket the socket for this player
     * @param games  the game holder containing all the games
     * @throws IOException the io exception
     */
    public ClientHandler(Socket socket, GameHolder games) throws IOException {
        json = new Gson();
        this.games = games;
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        this.start();
    }

    /**
     * Sets player id, it's called by the GameHolder when it creates a new game
     *
     * @param playerId the player id
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    /**
     * Keeps reading from the socket every update and doesn't handle any request that doesn't corrispond to the default
     * ones
     */
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

        try {
            while (true) {
                socket.setSoTimeout(20000);
                String line = in.readLine();
                if (line != null) {
                    Request request;
                    try{
                         request = JsonReader.readSingleRequest(line);
                        if (request instanceof NewGameRequest) {
                            games.add(request, this);
                        } else {
                            //If i receive a pong request ignore it
                            if (!(request instanceof PongRequest)) {
                                //check if the gameID is present
                                System.out.println("Received " + request);
                                games.get(request.getGameID()).notify(request);
                            }
                        }
                    }
                    catch(JsonSyntaxException e)
                    {
                        //In case the request can't be translated don't do anything
                    }

                }
            }
        } catch (SocketException e) {
            System.err.println(e.getMessage());
            System.out.println("Player " + playerId + " disconnected");
            thisGame.notifyAllPlayers(new ConnectionErrorUpdate(playerId));
        } catch (SocketTimeoutException e) {
            System.err.println(e.getMessage());
            System.out.println("Player " + playerId + "disconnected");
            thisGame.notifyAllPlayers(new ConnectionErrorUpdate(playerId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Notify with an update every time a player edits the model.
     * Synchronized because it's used by both threads (one for the actual game and another one for checking the connection
     * status)
     * @param update the update
     */
    //
    public synchronized void notifyView(Update update) {
        String message = json.toJson(update);
        out.println(message);
        out.flush();
    }

    /**
     * Sets game, it's called by the GameHolder when it creates a new game
     *
     * @param newGame the new game
     */
    public void setGame(Game newGame) {
        thisGame = newGame;
    }
}



