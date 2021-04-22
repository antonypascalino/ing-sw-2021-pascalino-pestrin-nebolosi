package it.polimi.ingsw.connection;

import java.io.IOException;

public class Server
{

        public static void main( String[] args )
        {
            MultiEchoServer server = new MultiEchoServer(1337);
            try {
                server.startServer();
            } catch(IOException e) {
                System.err.println(e.getMessage());
            }
        }
}

