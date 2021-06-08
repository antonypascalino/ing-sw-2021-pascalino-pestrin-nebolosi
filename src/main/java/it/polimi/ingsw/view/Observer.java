package it.polimi.ingsw.view;

import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.client.LineClient;
import it.polimi.ingsw.connection.JsonReader;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Updates.Update;
import it.polimi.ingsw.view.data.PlayerData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * The type Observer.
 */
//Keep listening on the server and print any message that is received
public class Observer implements Runnable{

    /**
     * The Socket.
     */
    Socket socket;
    /**
     * The In.
     */
    BufferedReader in;
    /**
     * The Data.
     */
    PlayerData data;

    /**
     * Instantiates a new Observer.
     *
     * @param connection the connection
     * @param data       the data
     * @throws IOException the io exception
     */
    public Observer(LineClient connection, PlayerData data) throws IOException {
        this.data = data;
        this.socket = connection.getSocket();
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    @Override
    public void run()
    {
        try{
            while(true) {
                String input = in.readLine();
                Update update = JsonReader.readUpdate(input);
                
                update.handleUpdate(data);
            }
        }catch (IOException e) {
        }


    }
}
