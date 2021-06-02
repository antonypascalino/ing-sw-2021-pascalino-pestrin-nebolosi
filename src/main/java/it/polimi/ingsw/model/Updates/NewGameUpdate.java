package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class NewGameUpdate implements Update{

    private ArrayList<String> frontTableCardsID;
    private Resource[][] market;
    private ArrayList<PlayerLC> leadersToChoose; // una mapped che per ogni playerID associa un arrayList di 4 leaderID random
                                                // importante è che non si ripetano le carte

    @Override
    public void handleUpdate(PlayerData data) {

    }
}
