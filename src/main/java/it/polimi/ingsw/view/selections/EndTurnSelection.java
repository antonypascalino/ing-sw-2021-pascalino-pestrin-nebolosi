package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.controller.Request.EndTurnRequest;
import it.polimi.ingsw.view.data.PlayerData;

/**
 * The {@link Selection} that guides the player when he chose to buy a development card.
 */
public class EndTurnSelection extends Selection {

    @Override
    public void handleSelection(PlayerData data) {
        data.sendRequest(new EndTurnRequest(data.getPlayerID(), data.getGameID()));
    }
}