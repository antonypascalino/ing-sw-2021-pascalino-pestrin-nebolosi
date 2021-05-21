package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class DiscardLeaderSelection extends Selection {


    public void handleSelection(PlayerData data) {
        ArrayList<String> leaderID = new ArrayList<String>();
        leaderID.addAll(data.getLeaders());
        String discarded = printer.printCardID(leaderID);
    }

}
