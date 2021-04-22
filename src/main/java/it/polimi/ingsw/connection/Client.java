package it.polimi.ingsw.connection;

import java.io.IOException;

public class Client
{


        public static void main(String[] args){
            LineClient client = new LineClient("127.0.0.1", 1337);
            try{
                client.startClient();
            }catch (IOException e){
                System.err.println(e.getMessage());
            }
        }

}


