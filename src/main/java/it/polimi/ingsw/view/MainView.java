package it.polimi.ingsw.view;

import it.polimi.ingsw.Request.NewGameRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.client.LineClient;
import it.polimi.ingsw.view.data.BasicData;
import it.polimi.ingsw.view.data.PlayerData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Main view.
 */
public class MainView extends Application {
    private static String nickname;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args)  {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/Insert_nickname.fxml"));
        primaryStage.setTitle("Maestri del rinascimeto!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void startGame() throws IOException {

        System.out.println("Insert the server IP");
        String serverIP = "127.0.0.1";//= scanner.nextLine();
        System.out.println("Insert the server port");
        int serverPort = 8080;//= scanner.nextInt();
        LineClient thisPlayer = new LineClient(serverIP, serverPort);
        Request request = new NewGameRequest(getNickname(), 2);
        thisPlayer.startClient();
        PlayerData data = new BasicData(getNickname(),thisPlayer);
        Observer observer = new Observer(thisPlayer,data);
        Thread t = new Thread(observer);
        t.start();
        thisPlayer.sendRequest(request);
    }

    public static void setNickname(String nickname) {
        MainView.nickname = nickname;
    }

    public static String getNickname() {
        return nickname;
    }
}
