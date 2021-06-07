package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.DiscardLeaderRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The type Discard leader selection.
 */
public class DiscardLeaderSelection extends Selection {


    public void handleSelection(PlayerData data) {
        ArrayList<String> leadersID = new ArrayList<String>();
        leadersID.addAll(data.getLeaders());
        String discarded = data.getPrinter().printCardID(leadersID, data);
        Request discardLeaderReq = new DiscardLeaderRequest(data.getGameID(), data.getPlayerID(), discarded);
        data.sendRequest(discardLeaderReq);
    }

}
