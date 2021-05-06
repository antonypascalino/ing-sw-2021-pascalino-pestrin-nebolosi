package it.polimi.ingsw.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LineClient {
    private String ip;
    private int port;
    //Message to sent to the server
    private String message;
    private boolean avaible = false;
    Socket socket;

    public LineClient(String ip, int port){
        this.ip = ip;
        this.port = port;
    }
    public void startClient() throws IOException {
        socket = new Socket(ip, port);
        System.out.println("Connection established");

    }


    /**
     * Send a message to the server and return the feedback from the server
     * @param input the string that needs to be sent to the server
     * @return the server response
     * @throws IOException
     */
    public String sendMessage(String input) throws IOException
    {
        message = input;

        Scanner socketIn = new Scanner(socket.getInputStream());
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
        Scanner stdin = new Scanner(System.in);
        try{
                String inputLine = message;
                socketOut.println(inputLine);
                socketOut.flush();
                String socketLine = socketIn.nextLine();
                return socketLine;
        } catch(NoSuchElementException e) {
            System.out.println("Connection closed");
        }
        finally {
            stdin.close();
            socketIn.close();
            socketOut.close();
        }
        //In case there's an error
        return "Failed";
    }

    public void closeClient()
    {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
