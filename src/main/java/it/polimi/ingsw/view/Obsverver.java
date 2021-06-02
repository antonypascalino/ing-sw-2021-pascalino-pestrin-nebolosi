package it.polimi.ingsw.view;

import it.polimi.ingsw.client.LineClient;

//Keep listening on the server and print any message that is received
public class Obsverver implements Runnable{

    LineClient connection;
    public Obsverver(LineClient connection)
    {
        this.connection = connection;
    }
    @Override
    public void run()
    {
        while(true)
        {
            System.out.println(connection.readServer());
        }

    }
}
