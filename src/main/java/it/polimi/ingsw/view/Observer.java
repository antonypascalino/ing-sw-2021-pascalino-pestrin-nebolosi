package it.polimi.ingsw.view;

import it.polimi.ingsw.server.JsonReader;
import it.polimi.ingsw.Updates.PlayLeaderUpdate;
import it.polimi.ingsw.Updates.Update;
import it.polimi.ingsw.view.data.PlayerData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * The server Observer.
 * Keeps listening on the server and handle any update
 */
public class Observer implements Runnable {


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
     * @param connection the serverConnection
     * @param data       the Player data
     * @throws IOException the io exception
     */
    public Observer(LineClient connection, PlayerData data) throws IOException {
        this.data = data;
        this.socket = connection.getSocket();
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * Keeps reading updates from the server and handle them.
     * It also handles the server crash in case it occures
     */
    @Override
    public void run() {
        try {
            socket.setSoTimeout(20000);
            while (true) {
                    String input = in.readLine();
                    Update update = JsonReader.readUpdate(input);
                    if (update instanceof PlayLeaderUpdate) {
                        ((PlayLeaderUpdate)update).wrapPlayer(this);
                    }
                    update.handleUpdate(data);
            }
        } catch (SocketException e) {
            System.out.println("Server crashed");
            System.exit(1);
        } catch (SocketTimeoutException e) {
            System.out.println("Server crashed");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets player.
     *
     * @param data the playerData
     */
    public void setPlayer(PlayerData data) {
        this.data = data;
    }

    /**
     * Gets data.
     *
     * @return the PlayerData
     */
    public PlayerData getData() {
        return data;
    }
}
