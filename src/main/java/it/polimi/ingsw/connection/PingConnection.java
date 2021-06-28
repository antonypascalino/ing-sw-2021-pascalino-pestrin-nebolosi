package it.polimi.ingsw.connection;

import it.polimi.ingsw.model.Updates.CheckUpdate;

import java.io.IOException;

public class PingConnection implements Runnable {
    ClientHandler clientHandler;

    public PingConnection(ClientHandler clientHandler) throws IOException {
        this.clientHandler = clientHandler;
    }

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