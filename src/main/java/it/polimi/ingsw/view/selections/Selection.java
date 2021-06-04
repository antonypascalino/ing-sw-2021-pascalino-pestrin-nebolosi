package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.view.Printer;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public abstract class Selection {

    public abstract void handleSelection(PlayerData data);

    public void sendToConnection(Request request) {

    }
}
