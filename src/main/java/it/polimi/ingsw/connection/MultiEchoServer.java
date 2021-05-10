package it.polimi.ingsw.connection;

import it.polimi.ingsw.controller.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiEchoServer {

    private int port;

    //Array list used for handle different threads
    private ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
    private ArrayList<Game> games = new ArrayList<Game>();

    public MultiEchoServer(int port){
        this.port = port;
    }

    public void startServer() throws IOException {
        //It creates threads when necessary, otherwise it re-uses existing one when possible
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;
        try{
            serverSocket = new ServerSocket(port);
        }catch (IOException e){
            System.err.println(e.getMessage()); //port not available
            return;
        }
        System.out.println("Server ready");
        while (true){
            try{
                Socket socket = serverSocket.accept();
                executor.submit(new ClientHandler(socket,games));
            }catch(IOException e){
                break; //In case the serverSocket gets closed
            }
        }
        executor.shutdown();
        serverSocket.close();
    }

}

