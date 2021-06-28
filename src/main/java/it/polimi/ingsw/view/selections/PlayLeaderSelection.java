package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.PlayLeaderRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The {@link Selection} that guides the player when he chose to play a leader card.
 */
public class PlayLeaderSelection extends Selection {

    public void handleSelection(PlayerData data) {
        ArrayList<String> leaderID = new ArrayList<>();
        leaderID.addAll(data.leaderCardsFilter());
        String cardID = data.getPrinter().printLeaderCardID(leaderID, data);
        Request playLeaderReq = new PlayLeaderRequest(data.getPlayerID(), data.getGameID(), cardID);
        data.sendRequest(playLeaderReq);
    }
}
