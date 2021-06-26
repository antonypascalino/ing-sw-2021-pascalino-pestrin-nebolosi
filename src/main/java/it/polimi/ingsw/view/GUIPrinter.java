package it.polimi.ingsw.view;

import it.polimi.ingsw.view.FXControllers.NicknameController;
import it.polimi.ingsw.view.data.PlayerData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;

public class GUIPrinter extends Printer {

    @Override
    public ArrayList<String> chooseLeaderCard(ArrayList<String> leadersToChoose, PlayerData data) {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/FXMLs/Project1.fxml")
        );
        NicknameController nicknameController = loader.getController();
        nicknameController.showLeaderCards(leadersToChoose, data);
        return null;
    }
}

