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
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        GUIPrinter guiPrinter = new GUIPrinter(); //BISOGNA TROVARE UN MODO PER SCEGLIERE UNA GUI O UNA CLI

        launch(args);
        String nickname = guiPrinter.insertNickname();
        System.out.println("Insert the server IP");
        String serverIP = "127.0.0.1";//= scanner.nextLine();
        System.out.println("Insert the server port");
        int serverPort = 8080;//= scanner.nextInt();
        LineClient thisPlayer = new LineClient(serverIP, serverPort);
        Request request = new NewGameRequest(nickname, 2);
        thisPlayer.startClient();
        PlayerData data = new BasicData(nickname,thisPlayer);
        Observer observer = new Observer(thisPlayer,data);
        Thread t = new Thread(observer);
        t.start();
        thisPlayer.sendRequest(request);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Project1.fxml"));
        primaryStage.setTitle("Maestri del rinascimeto!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
