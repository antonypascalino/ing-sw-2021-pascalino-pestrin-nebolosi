package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.PlayLeaderRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class PlayLeaderSelection extends Selection{

    public void handleSelection(PlayerData data){
        ArrayList<String> leaderID = new ArrayList<String>();
        leaderID.addAll(data.leaderCardsFilter());
        String cardID = data.getPrinter().printCardID(leaderID);
        Request playLeaderReq = new PlayLeaderRequest(data.getPlayerID(), data.getGameID(), cardID);
        sendToConnection(playLeaderReq);
    }

}
