package it.polimi.ingsw.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {

        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        }

        @Override
        public void run() {
            try
            {
                while (true)
                {
                    String line = in.readLine() ;
                    if(!line.equals(null))
                        System.out.println("Ricevuto un messaggio");
                    if(line.equals("quit")){
                        break;
                    } else {
                        out.println(line);
                        out.flush();
                    }
                }
                //close connections
                in.close();
                out.close();
                socket.close();
            } catch (IOException e){
                System.err.println(e.getMessage());
            }
        }

}



