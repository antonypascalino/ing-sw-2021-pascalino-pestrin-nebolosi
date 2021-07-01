package it.polimi.ingsw.view;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Request.Request;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;


/**
 * The serverHandler from the client
 */
public class LineClient {
    private String ip;
    private int port;
    //Message to sent to the server
    private String message;
    private Socket socket;
    private Gson json;
    private PrintWriter socketOut;

    /**
     * Instantiates a new Line client.
     *
     * @param ip   the ip
     * @param port the port
     */
    public LineClient(String ip, int port) {
        json = new Gson();
        this.ip = ip;
        this.port = port;
    }

    /**
     * Start client and open connection with the server
     *
     * @throws IOException the io exception
     */
    public void startClient() throws IOException {
        socket = new Socket(ip, port);
        System.out.println("Connection established");
        socketOut = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * Serialize and send a request to the server, it's synchronzied becuas both the game and the ponger use ths
     *
     * @param input the request before being serialized
     * @throws IOException the io exception
     */
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

    /**
     * Gets socket.
     *
     * @return the socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Sets ip for communicating to the server and starts the server
     *
     * @param serverIP the server ip
     */
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
