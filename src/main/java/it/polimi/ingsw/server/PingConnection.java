package it.polimi.ingsw.server;

import it.polimi.ingsw.Updates.CheckUpdate;

import java.io.IOException;

/**
 * The type Ping connection.
 */
public class PingConnection implements Runnable {
    /**
     * The Client handler.
     */
    ClientHandler clientHandler;

    /**
     * Instantiates a new Ping connection for every socket
     *
     * @param clientHandler the client handler
     * @throws IOException the io exception
     */
    public PingConnection(ClientHandler clientHandler) throws IOException {
        this.clientHandler = clientHandler;
    }

    /**
     * Pings constatly the client so it knows that the server didn't disconnect
     */
    public void run() {
        while (true) {
            clientHandler.notifyView(new CheckUpdate());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}