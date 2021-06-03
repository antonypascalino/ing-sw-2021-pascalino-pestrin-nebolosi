package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.view.data.OtherPlayerData;
import it.polimi.ingsw.view.data.PlayerData;

public class CheckStatsSelection extends Selection{

    @Override
    public void handleSelection(PlayerData data) {
        if(data.getPrinter().askQuestion()){
            data.getPrinter().printMyStats(data);
        }
        else{
            for(OtherPlayerData p : data.getOtherPlayers())
            data.getPrinter().printOtherStats(p);
        }

    }
}
