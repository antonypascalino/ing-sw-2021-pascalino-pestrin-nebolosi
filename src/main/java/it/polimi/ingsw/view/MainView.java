package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.Request.NewGameRequest;
import it.polimi.ingsw.controller.Request.Request;
import it.polimi.ingsw.client.LineClient;
import it.polimi.ingsw.view.data.BasicData;
import it.polimi.ingsw.view.data.PlayerData;

import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

/**
 * The type Main view.
 */
public class MainView {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert a new nickname");
        String nickname = scanner.nextLine();
        System.out.println("Insert the server IP");
        String serverIP = scanner.nextLine();
        int serverPort = 8080;
        LineClient thisPlayer = new LineClient(serverIP, serverPort);
        System.out.println("We're gonna try to find an existing game to add you\n\rin case there are none, how many players do you want in your game?");
        int maxPlayers = scanner.nextInt();
        Request request = new NewGameRequest(nickname, maxPlayers);
        try{
            thisPlayer.startClient();
        }catch (SocketException e)
        {
            System.out.println("Insert the server IP");
            serverIP = scanner.nextLine();
            thisPlayer.setIP(serverIP);
        }
        catch (IOException e)
        {
            System.out.println("Insert the server IP");
            serverIP = scanner.nextLine();
            thisPlayer.setIP(serverIP);
        }

        PlayerData data = new BasicData(nickname, thisPlayer);
        Observer observer = new Observer(thisPlayer, data);
        Thread t = new Thread(observer);
        t.start();
        Ponger pong = new Ponger(thisPlayer, nickname);
        Thread p = new Thread(pong);
        p.start();
        thisPlayer.sendRequest(request);
    }
}
