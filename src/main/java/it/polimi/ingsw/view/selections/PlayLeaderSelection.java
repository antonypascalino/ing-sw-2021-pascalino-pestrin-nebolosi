package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.PlayLeaderRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The type Play leader selection.
 */
public class PlayLeaderSelection extends Selection{

    public void handleSelection(PlayerData data){
        ArrayList<String> leaderID = new ArrayList<String>();
        leaderID.addAll(data.leaderCardsFilter());
        String cardID = data.getPrinter().printCardID(leaderID, data);
        Request playLeaderReq = new PlayLeaderRequest(data.getPlayerID(), data.getGameID(), cardID);
        data.sendRequest(playLeaderReq);
    }

}
