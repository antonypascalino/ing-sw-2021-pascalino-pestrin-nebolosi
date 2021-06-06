package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.EndTurnRequest;
import it.polimi.ingsw.view.data.PlayerData;

public class EndTurnSelection extends Selection {

    @Override
    public void handleSelection(PlayerData data) {
        data.sendRequest(new EndTurnRequest(data.getPlayerID(), data.getGameID()));
    }
}