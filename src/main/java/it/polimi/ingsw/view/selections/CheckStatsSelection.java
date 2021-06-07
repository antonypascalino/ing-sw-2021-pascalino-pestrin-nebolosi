package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.view.data.OtherPlayerData;
import it.polimi.ingsw.view.data.PlayerData;

/**
 * The type Check stats selection.
 */
public class CheckStatsSelection extends Selection{

    @Override
    public void handleSelection(PlayerData data) {
        if(data.getPrinter().chooseStats()){
            data.getPrinter().printMyStats(data);
        }
        else{
            for(OtherPlayerData p : data.getOtherPlayers())
            data.getPrinter().printOtherStats(p);
        }
        data.getMenu().menuMaker();
    }
}
