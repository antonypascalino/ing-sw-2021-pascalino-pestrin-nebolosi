package it.polimi.ingsw.view;

import it.polimi.ingsw.Request.PongRequest;
import it.polimi.ingsw.client.LineClient;

import java.io.IOException;

/**
 * Each client has an element that pong the server so
 * it doesn't timeout.
 * Whenever the client dies and the ponger doesn't send a pong it means it's dead
 */
public class Ponger implements Runnable {

    private LineClient connection;
    private String nickname;

    public Ponger(LineClient client, String nickname) {
        this.connection = client;
        this.nickname = nickname;
    }

    @Override
    public void run() {
        while (true) {
            try {
                connection.sendRequest(new PongRequest());
            } catch (IOException e) {
                System.out.println("Disconnected from the server");
                e.printStackTrace();
            }
        }
    }
}
