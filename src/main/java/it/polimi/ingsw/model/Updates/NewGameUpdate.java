package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class NewGameUpdate implements Update{

    private ArrayList<String> frontTableCardsID;
    private Resource[][] market;
    private ArrayList<PlayerLC> playersLC; // una mapped che per ogni playerID associa un arrayList di 4 leaderID random
                                                // importante è che non si ripetano le carte


    public NewGameUpdate(ArrayList<String> frontTableCardsID, Resource[][] market, ArrayList<PlayerLC> playersLC) {
        this.frontTableCardsID = frontTableCardsID;
        this.market = market;
        this.playersLC = playersLC;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        PlayerLC playerLC = null;
        for (PlayerLC p : playersLC) {
            if(p.getPlayerID().equals(data.getPlayerID())) playerLC = p;
        }
        ArrayList<String> chosen = data.getPrinter().chooseLeaderCard(playerLC.getLeadersToChoose(), data);
        data.setLeadersID(chosen);
    }
}
