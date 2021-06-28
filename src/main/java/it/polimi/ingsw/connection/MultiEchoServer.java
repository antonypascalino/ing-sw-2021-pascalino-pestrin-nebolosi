package it.polimi.ingsw.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiEchoServer {

    private int port;

    //Array list used for handle different threads
    private ArrayList<ClientHandler> clients = new ArrayList<>();
    //Id of the lastPlayer who joined
    private GameHolder games;

    public MultiEchoServer(int port) {
        this.port = port;
    }

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

    //Increment the lastPlayerId
    public String newPlayer(String number) {
        if (!number.matches("^[A-Za-z]{3}[0-9]{3}$"))
            throw new IllegalArgumentException("Number doesn't match playerID format");
        int numericSum = Integer.parseInt(number.substring(3));
        int letterSum = value(number, 0) * 26 * 26 + value(number, 1) * 26 + value(number, 2);
        numericSum += 1;
        if (numericSum >= 1000) {
            letterSum += numericSum / 1000;
            numericSum %= 1000;
        }

        char[] letters = new char[3];
        int n = letterSum;
        for (int i = 0; i < 3; i++) {
            letters[2 - i] = (char) ('A' + (n % 26));
            n /= 26;
        }
        return new String(letters) + String.format("%03d", numericSum);
    }

    private int value(String s, int index) {
        return Character.toUpperCase(s.charAt(index)) - 'A';
    }
}


