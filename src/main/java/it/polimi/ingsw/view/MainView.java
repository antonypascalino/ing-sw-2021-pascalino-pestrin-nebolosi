package it.polimi.ingsw.view;

import it.polimi.ingsw.Request.NewGameRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.client.LineClient;
import it.polimi.ingsw.view.data.BasicData;
import it.polimi.ingsw.view.data.PlayerData;

import java.io.IOException;
import java.util.Scanner;

public class MainView {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert a new nickname");
        String nickname = scanner.nextLine();
        System.out.println("Insert the server IP");
        String serverIP = "127.0.0.1";//= scanner.nextLine();
        System.out.println("Insert the server port");
        int serverPort = 8080;//= scanner.nextInt();
        LineClient thisPlayer = new LineClient(serverIP, serverPort);
        Request request = new NewGameRequest(nickname, 3);
        thisPlayer.startClient();


        PlayerData data = new BasicData(nickname);
        Observer observer = new Observer(thisPlayer,data);
        Thread t = new Thread(observer);
        t.start();
        //Aggiungere a tutti i PlayerData il gameID
        thisPlayer.sendRequest(request);

    }
}
