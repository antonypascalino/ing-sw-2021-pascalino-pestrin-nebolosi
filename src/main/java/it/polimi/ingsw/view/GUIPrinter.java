package it.polimi.ingsw.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GUIPrinter extends Printer {

    boolean textReady;
    @FXML
    TextField nicknameField;
    @FXML
    Button confirmNickname;

    @Override
    public String insertNickname() {
        while (!textReady) {
        }
        return nicknameField.getText();
    }

    @FXML
    public void setNickname(ActionEvent actionEvent) {
        textReady = true;
    }
}

