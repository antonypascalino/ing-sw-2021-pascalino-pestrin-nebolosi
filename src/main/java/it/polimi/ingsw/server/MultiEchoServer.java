package it.polimi.ingsw.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Multi echo server.
 */
public class MultiEchoServer {

    private int port;

    //Array list used for handle different threads
    //Id of the lastPlayer who joined
    private GameHolder games;

    /**
     * Instantiates a new Multi echo server.
     *
     * @param port the port
     */
    public MultiEchoServer(int port) {
        this.port = port;
    }

    /**
     * Start server which is able to handle more client
     *
     * @throws IOException the io exception
     */
    public void startServer() throws IOException {
        //It creates threads when necessary, otherwise it re-uses existing one when possible
        ExecutorService executor = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket;
        games = new GameHolder();
        try {
            serverSocket = new ServerSocket(port);
            System.out.println(port);
        } catch (IOException e) {
            System.err.println(e.getMessage()); //port not available
            return;
        }
        System.out.println("Server ready");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                //Create the new game id
                //lastPlayer = newPlayer(lastPlayer);
                new ClientHandler(socket, games);
            } catch (IOException e) {
                break; //In case the serverSocket gets closed
            }
        }
        executor.shutdown();
        serverSocket.close();
    }

}


