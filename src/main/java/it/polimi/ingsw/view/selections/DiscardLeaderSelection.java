package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.DiscardLeaderRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The {@link Selection} that guides the player when he chose to discard a leader card.
 */
public class DiscardLeaderSelection extends Selection {


    public void handleSelection(PlayerData data) {
        ArrayList<String> leadersID = new ArrayList<>();
        leadersID.addAll(data.getLeaders());
        String discarded = data.getPrinter().printLeaderCardID(leadersID, data);
        Request discardLeaderReq = new DiscardLeaderRequest(data.getGameID(), data.getPlayerID(), discarded);
        data.sendRequest(discardLeaderReq);
    }

}
