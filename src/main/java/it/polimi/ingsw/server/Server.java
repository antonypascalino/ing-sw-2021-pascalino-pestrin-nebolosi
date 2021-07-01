package it.polimi.ingsw.server;

import java.io.IOException;

/**
 * The type Server.
 */
public class Server {
    /**
     * The entry point of application for the server which opens a new server
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        MultiEchoServer server = new MultiEchoServer(8080);
        try {
            server.startServer();
        } catch (IOException e) {
            System.out.println("Server failure");
            System.err.println(e.getMessage());
        }
    }
}

