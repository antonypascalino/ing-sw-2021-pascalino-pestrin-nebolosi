package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.card.ExtraDeposit;
import it.polimi.ingsw.model.ExtraDepositLevel;
import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;
import java.util.Arrays;

public class ExtraDepositPlayer extends Player {
    private ExtraDeposit card;
    //private int addedLevel; //the level in the Warehouse added by this Mod
    private Resource placeableRes; //the resource placeable in the added level
    private ArrayList<ExtraDepositLevel> extraDep;
    //private Player original;
    //private Resource extra1;

    public ExtraDepositPlayer(Player player, Resource placeableRes) {
       // addedLevel = player.getBoard().getWareHouse().getLevels().size(); //CONTOLLARE DISCORSO INDICI: LIVELLO 1 HA INDICE 0 NELL'ARRAYLIST
        if (original instanceof ExtraDepositPlayer)
        extraDep = new ArrayList<ExtraDepositLevel>();
        extraDep.add(new ExtraDepositLevel(placeableRes));
        original = player;
    }

    @Override
    public boolean checkLevel(int level) {
        if (!(level <= extraDep.size() + 3 && level > 0)) {
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
        else if(level >= 4 && level <= extraDep.size() + 3) {
            if (extraDep.get(level - 4).getPlaceable().equals(res)) {
                return extraDep.get(level - 4).getResources().size() < 2;
            }
            return false;
        }
        return false; //nel caso eccezione
    }

    @Override
    public void switchLevels(int originLevel, int destLevel) {
        //Se lo switch avviene interamente nel Warehouse
        if ((originLevel >= 1 && originLevel <= 3) && (destLevel >= 1 && destLevel <= 3)) {
            original.getBoard().getWareHouse().switchLevels(originLevel, destLevel);
        }
        //Se lo switch avviene dal Warehouse ad un deposito extra
        else if((originLevel >= 1 && originLevel <= 3) && (destLevel >= 4 && destLevel <= extraDep.size() + 3)) {

            int resCounter = 0;
            int extraResCounter = 0;
            for (Resource res : original.getBoard().getWareHouse().getLevels().get(originLevel)) {
                if (res != Resource.EMPTY) resCounter++;
            }
            for (Resource res : extraDep.get(destLevel).getResources()) {
                if (res != Resource.EMPTY) extraResCounter++;
            }
            if (resCounter == 1 && extraResCounter == 1) {
                extraDep.get(destLevel).addResource(placeableRes);
                Arrays.fill(original.getBoard().getWareHouse().getLevels().get(originLevel), Resource.EMPTY);
            }

        }
        //Se lo switch avviene da un deposito Extra al Warehouse
        else if((originLevel >= 4 && originLevel <= extraDep.size() + 3) && (destLevel >= 1 && destLevel <= 3)) {
            int resCounter = 0;
            int extraResCounter = 0;
            for (Resource res : extraDep.get(originLevel).getResources()) {
                if (res != Resource.EMPTY) resCounter++;
            }
            for (Resource res : original.getBoard().getWareHouse().getLevels().get(destLevel)) {
                if (res != Resource.EMPTY) extraResCounter++;
            }
            if (resCounter == 1 && extraResCounter == 1) {
                original.getBoard().getWareHouse().getLevels().get(destLevel)[1] = placeableRes;
                extraDep.get(originLevel).getResources().clear();
                for(int i = 0; i < 2; i++){
                    extraDep.get(originLevel).getResources().add(Resource.EMPTY);
                }
            }
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

    @Override
    public ArrayList<Resource[]> getDeposits() {
        ArrayList<Resource[]> deposits = original.getBoard().getWareHouse().getArrayListWareHouse();

        for(ExtraDepositLevel e : extraDep) {
            Resource[] extraLevel = e.getResources().toArray(new Resource[2]);
            deposits.add(extraLevel);
            }
        return deposits;
    }
}



