package it.polimi.ingsw.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PingConnection implements Runnable {
    Socket socket;
    String player;
    private BufferedReader in;
    private PrintWriter out;

    public PingConnection(Socket socket, String playerNick) throws IOException {
        this.socket = socket;
        this.player = playerNick;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);

    }

    public void run()
    {
        while (true)
        {
            out.println("Ping");
            try {
                if(!in.ready());
                    //Chiudi partita
                else if(!in.readLine().equals("pong"));
                //Chiud partita

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}