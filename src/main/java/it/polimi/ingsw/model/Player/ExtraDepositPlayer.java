package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.controller.MarketResource;
import it.polimi.ingsw.model.Cards.ExtraDeposit;
import it.polimi.ingsw.model.ExtraDepositLevel;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

public class ExtraDepositPlayer extends Player {
    private ExtraDeposit card;
    private int addedLevel; //the level in the Warehouse added by this Mod
    private Resource placeableRes; //the resource placeable in the added level
    private ArrayList<ExtraDepositLevel> extraDep;
    //private Resource extra1;

    public ExtraDepositPlayer(Player player, Resource placeableRes) {
        addedLevel = player.getBoard().getWareHouse().getLevels().size(); //CONTOLLARE DISCORSO INDICI: LIVELLO 1 HA INDICE 0 NELL'ARRAYLIST
        extraDep = new ArrayList<ExtraDepositLevel>();
        extraDep.add(new ExtraDepositLevel(placeableRes));
    }

    @Override
    public boolean checkLevel(int level) {
        if (!(level <= addedLevel && level > 0)) {
            // lancia eccezione perché non ha carte che aggiungono il livello indicato
            return false;
        }
    return true;
    }

    @Override
    public boolean checkSpace(Resource res, int level) {
        if(level >= 1 && level <= 3) {
            return getBoard().getWareHouse().checkSpace(level, res);
        }
        else if(level >= 4 && level <= addedLevel) {
            if (extraDep.get(level - 4).getPlaceable().equals(res)) {
                return extraDep.get(level - 4).getExtraLevel().size() < 2;
            }
            return false;
        }
        return false; //nel caso eccezione
    }

}



