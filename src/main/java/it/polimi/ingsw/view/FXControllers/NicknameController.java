package it.polimi.ingsw.view.FXControllers;

import it.polimi.ingsw.view.MainView;
import it.polimi.ingsw.view.data.PlayerData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

//Controller JavaFX
public class NicknameController {

    @FXML TextField nicknameField;
    @FXML Button confirmNickname;
    @FXML Button leaderToChoose1;
    @FXML Button leaderToChoose2;
    @FXML Button leaderToChoose3;
    @FXML Button leaderToChoose4;
    private int leadersChosen = 0;
    private ArrayList<String> leaderToChoose;
    private ArrayList<String> leadersIDChosen;
    private PlayerData data;
    @FXML Pane nickNamePane;
    @FXML Pane chooseLeaderCardPane;
    @FXML ImageView image1;
    @FXML ImageView image2;
    @FXML ImageView image3;
    @FXML ImageView image4;


    @FXML
    public void setNickname(ActionEvent actionEvent) {
        MainView.setNickname(nicknameField.getText());
        nickNamePane.setVisible(false);
        try {MainView.startGame(); } catch (IOException e) {}
    }

    @FXML
    public void chooseLeaderCard1(ActionEvent actionEvent) {
        leadersChosen++;
        leadersIDChosen.add(leaderToChoose.get(0));
        leaderToChoose1.setVisible(false);
        if (leadersChosen == 2) {
            data.setLeadersID(leadersIDChosen);
        }
    }

    @FXML
    public void chooseLeaderCard2(ActionEvent actionEvent) {
        leadersChosen++;
        leadersIDChosen.add(leaderToChoose.get(1));
        leaderToChoose2.setVisible(false);
        if (leadersChosen == 2) {
            data.setLeadersID(leadersIDChosen);
        }

    }

    @FXML
    public void chooseLeaderCard3(ActionEvent actionEvent) {
        leadersChosen++;
        leadersIDChosen.add(leaderToChoose.get(2));
        leaderToChoose3.setVisible(false);
        if (leadersChosen == 2) {
            data.setLeadersID(leadersIDChosen);
        }

    }

    @FXML
    public void chooseLeaderCard4(ActionEvent actionEvent) {
        leadersChosen++;
        leadersIDChosen.add(leaderToChoose.get(3));
        leaderToChoose4.setVisible(false);
        if (leadersChosen == 2) {
            data.setLeadersID(leadersIDChosen);
        }
    }


    public void showLeaderCards(ArrayList<String> leadersToChoose, PlayerData data ) {
        this.leaderToChoose = leadersToChoose;
        this.data = data;
        image1.setImage(new Image(CardsMap.getCardURLFromID(leadersToChoose.get(0))));
        image2.setImage(new Image(CardsMap.getCardURLFromID(leadersToChoose.get(1))));
        image3.setImage(new Image(CardsMap.getCardURLFromID(leadersToChoose.get(2))));
        image4.setImage(new Image(CardsMap.getCardURLFromID(leadersToChoose.get(3))));
        chooseLeaderCardPane.setVisible(true);
    }
}

