package it.polimi.ingsw.client;

import com.google.gson.Gson;
import it.polimi.ingsw.Request.Request;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;


public class LineClient {
    private String ip;
    private int port;
    //Message to sent to the server
    private String message;
    private Socket socket;
    private Gson json;
    PrintWriter socketOut;

    public LineClient(String ip, int port) {
        json = new Gson();
        this.ip = ip;
        this.port = port;
    }

    public void startClient() throws IOException {
        socket = new Socket(ip, port);
        System.out.println("Connection established");
        socketOut = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * Send a message to the server and return the feedback from the server
     *
     * @param input the string that needs to be sent to the server
     * the server response
     * @throws IOException
     */
    public void sendMessage(String input) throws IOException {
        message = input;
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
        try {
            String inputLine = message;
            socketOut.println(inputLine);
            socketOut.flush();
        } catch (NoSuchElementException e) {
            System.out.println("Connection closed");
        } finally {
            socketOut.close();
        }
        //In case there's an error
    }

    public synchronized void sendRequest(Request input) throws IOException {
        message = json.toJson(input);

        try {
            String inputLine = message;
            socketOut.println(inputLine);
            socketOut.flush();
        } catch (NoSuchElementException e) {
            System.out.println("Connection closed");
        }
        //In case there's an error
    }

    public Socket getSocket() {
        return socket;
    }

    public void closeClient() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setIP(String serverIP) {
        this.ip = serverIP;
        try {
            this.startClient();
        } catch (IOException e) {
            System.out.println("Server not found");
            System.exit(1);
        }
    }
}
