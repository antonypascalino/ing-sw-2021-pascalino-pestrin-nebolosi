package it.polimi.ingsw.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoHandler implements Runnable {

        private Socket socket;

        public EchoHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                Scanner in = new Scanner(socket.getInputStream());
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                while (true){
                    String line = in.nextLine();
                    if(line.equals("quit")){
                        break;
                    } else {
                        out.println("Received: " + line);
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



