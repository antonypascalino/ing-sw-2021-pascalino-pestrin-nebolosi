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
    //Id of the lastPlayer who joined
    private String lastPlayer;

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
                lastPlayer = newPlayer(lastPlayer);
                executor.submit(new ClientHandler(socket,games,lastPlayer));
            }catch(IOException e){
                break; //In case the serverSocket gets closed
            }
        }
        executor.shutdown();
        serverSocket.close();
    }

    //Increment the lastPlayerId
    public String newPlayer(String number) {
        if (!number.matches("^[A-Za-z]{3}[0-9]{3}$"))
            throw new IllegalArgumentException("Number doesn't match playerID format");
        int numericSum = Integer.valueOf(number.substring(3));
        int letterSum = value(number, 0) * 26 * 26 + value(number, 1) * 26 + value(number, 2);
        numericSum += 1;
        if (numericSum >= 1000)
        {
            letterSum += numericSum/1000;
            numericSum %= 1000;
        }

        char[] letters = new char[3];
        int n = letterSum;
        for (int i = 0; i < 3; i++) {
            letters[2-i] = (char)('A' + (n%26));
            n /= 26;
        }
        return new String(letters) + String.format("%03d", numericSum);
    }


    private int value(String s, int index) {
        return Character.toUpperCase(s.charAt(index)) - 'A';
    }

}


