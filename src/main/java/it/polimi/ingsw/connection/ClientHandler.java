package it.polimi.ingsw.connection;

import com.google.gson.Gson;
import it.polimi.ingsw.Request.NewGameRequest;
import it.polimi.ingsw.Request.PongRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Updates.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

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
        out = new PrintWriter(socket.getOutputStream(), true);
        this.start();
    }

    public void setPlayerId(String playerId) {
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

        try {
            while (true) {
                socket.setSoTimeout(20000);
                String line = in.readLine();
                if (line != null) {
                    Request request = JsonReader.readSingleRequest(line);
                    //It doesn't exist the idea of a new game or join game, there's just a join game that creates a game
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

    //Synchronized because it's used by both threads (once for the actual game and another one for checking the status)
    public synchronized void notifyView(Update update) {
        String message = json.toJson(update);
        out.println(message);
        out.flush();
    }

    public void setGame(Game newGame) {
        thisGame = newGame;
    }
}



