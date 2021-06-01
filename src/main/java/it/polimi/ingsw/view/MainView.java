package it.polimi.ingsw.view;

import it.polimi.ingsw.Request.JoinGameRequest;
import it.polimi.ingsw.Request.NewGameRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.client.LineClient;
import it.polimi.ingsw.view.data.BasicData;
import it.polimi.ingsw.view.data.PlayerData;
import it.polimi.ingsw.view.selections.Selection;

import java.io.IOException;
import java.util.Scanner;

public class MainView {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert a new nickname");
        String nickname = scanner.nextLine();



        System.out.println("Insert the server IP");
        String serverIP = scanner.nextLine();
        System.out.println("Insert the server port");
        int serverPort = scanner.nextInt();
        LineClient thisPlayer = new LineClient(serverIP, serverPort);
        Request request = new NewGameRequest(nickname, 2);
        thisPlayer.startClient();
        System.out.println(thisPlayer.sendRequest(request));
        PlayerData data = new BasicData(nickname);
        MainMenu menu = new MainMenu(thisPlayer,data);
        menu.menuMaker();
    }
}
