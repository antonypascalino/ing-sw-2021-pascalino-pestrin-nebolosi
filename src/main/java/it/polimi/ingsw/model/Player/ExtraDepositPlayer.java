package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.controller.MarketResource;
import it.polimi.ingsw.model.Cards.ExtraDeposit;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

public class ExtraDepositPlayer extends Player {
    private ExtraDeposit card;
    private int addedLevel; //the level in the Warehouse added by this Mod
    private Resource placeableRes; //the resource placeable in the added level

    public ExtraDepositPlayer(Player player, Resource placeableRes) {
        addedLevel = player.getBoard().getWareHouse().getLevels().size(); //CONTOLLARE DISCORSO INDICI: LIVELLO 1 HA INDICE 0 NELL'ARRAYLIST
    }

    @Override
    public boolean checkLevel(int level) {
        if (!(level <= addedLevel && level > 0)) { //|| (!marketRes.getResource().equals(placeableRes))) {
            // lancia eccezione perché non ha carte che aggiungono il livello indicato
        }
    return true;
    }

    @Override
    public boolean checkSpace(Resource res, int level) {

    }


}



