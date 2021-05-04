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
    private Player original;
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
                return extraDep.get(level - 4).getResources().size() < 2;
            }
            return false;
        }
        return false; //nel caso eccezione
    }

    @Override
    public void switchLevels(Resource res, int orLevel, int finLevel) {
        //Se lo switch avviene interamente nel Warehouse
        if ((orLevel >= 1 && orLevel <= 3) && (finLevel >= 1 && finLevel <= 3)) {
            original.getBoard().getWareHouse().switchLevels(res, orLevel, finLevel);
        }
        //Se lo switch avviene dal Warehouse al ad un deposito extra
        else if((orLevel >= 1 && orLevel <= 3) && (finLevel >= 4 && finLevel <= addedLevel)) {
            original.getBoard().getWareHouse().removeResource(res);
            this.add(res, finLevel);

        }
        //Se lo switch avviene da un deposito Extra al Warehouse
        else if((orLevel >= 4 && orLevel <= addedLevel) && (finLevel >= 1 && finLevel <= 3)) {
            this.remove(res, orLevel);
            original.getBoard().getWareHouse().addResource(finLevel, res);
        }
        //Lo switch non può avvenire da un deposito extra ad un altro deposito extra in quanto le risorse piazzabili in essi si escludono a vicenda
    }

    private void add (Resource res, int level) {
        extraDep.get(level-4).addResource(res);
    }

    private void remove (Resource res, int level) {
        extraDep.get(level-4).removeResource(res);
    }

    @Override
    public void addResource(int level, Resource res) {
        if (level >= 1 && level <= 3) {
            original.getBoard().getWareHouse().addResource(level, res);
        } else {
            this.add(res, level);
        }
    }

    @Override
    public void removeResource(Resource res, String place) {
        //If the player doesn't have the resources throw a new exception
        if (place.equals("strongbox")) {
            original.getBoard().getStrongBox().removeResource(res);
        } else if (place.equals("warehouse")) {
            original.getBoard().getWareHouse().removeResource(res);
            //lancia eccezione: non hai questo posto da dove prendere la risorsa
        } else if (place.equals("extradep")) {
            for (ExtraDepositLevel extralevel : extraDep) {
                if (extralevel.getPlaceable() == res) {
                    remove(res, extraDep.indexOf(extralevel));
                }
            }
        }
        //Non hai depositi extra che contengono questa risorsa.
    }

    @Override
    public ArrayList<Resource> getAllResources() {
        ArrayList<Resource> tmp = new ArrayList<Resource>();
        tmp.addAll(this.getBoard().getStrongBox().getResources());
        tmp.addAll(this.getBoard().getWareHouse().getResources());
        for (ExtraDepositLevel extraLevel : extraDep) {
            tmp.addAll(extraLevel.getResources());
        }
        return tmp;
    }
}



