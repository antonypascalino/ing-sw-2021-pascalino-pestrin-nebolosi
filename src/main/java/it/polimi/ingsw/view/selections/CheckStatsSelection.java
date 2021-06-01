package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.view.data.OtherPlayerData;
import it.polimi.ingsw.view.data.PlayerData;

public class CheckStatsSelection extends Selection{

    @Override
    public void handleSelection(PlayerData data) {
        if(printer.askQuestion()){
            printer.printMyStats(data);
        }
        else{
            for(OtherPlayerData p : data.getOtherPlayers())
            printer.printOtherStats(p);
        }

    }
}
