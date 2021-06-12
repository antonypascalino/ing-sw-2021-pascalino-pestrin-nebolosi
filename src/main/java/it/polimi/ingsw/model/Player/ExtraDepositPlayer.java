package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.card.ExtraDeposit;
import it.polimi.ingsw.model.ExtraDepositLevel;
import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ExtraDepositPlayer extends Player {
    private ExtraDeposit card;
    private ArrayList<Resource> placeableRes; //the resource placeable in the added level
    private ArrayList<ExtraDepositLevel> extraDep;


    public ExtraDepositPlayer(Player original, Resource placeableRes) {
       // addedLevel = player.getBoard().getWareHouse().getLevels().size(); //CONTOLLARE DISCORSO INDICI: LIVELLO 1 HA INDICE 0 NELL'ARRAYLIST
        this.placeableRes = new ArrayList<>();
        extraDep = new ArrayList<>();
        this.original = original;
        if (original instanceof ExtraDepositPlayer) {
            this.placeableRes.addAll(((ExtraDepositPlayer) original).placeableRes);
            extraDep.add(((ExtraDepositPlayer) original).getExtraDep().get(0)); //Non dovrebbe essere get 0 ma get l'ultimo elemento, ma tanto possiamo avere solo 2 carte al massimo
            //Points to the basic
            this.original = original.original;
        }

        extraDep.add(new ExtraDepositLevel(placeableRes));
        this.placeableRes.add(placeableRes);

    }

    @Override
    public boolean checkLevel(int level) {
        if (!(level <= extraDep.size() + 2 && level >= 0)) {
            // lancia eccezione perché non ha carte che aggiungono il livello indicato
            return false;
        }
        return true;
    }

    @Override
    public boolean checkSpace(Resource res, int level) {
        if(level >= 0 && level <= 2) {
            return getBoard().getWareHouse().checkSpace(level, res);
        }
        else if(level >= 3 && level <= extraDep.size() + 2) {
            if (extraDep.get(level - 3).getPlaceable().equals(res)) {
                return Collections.frequency(extraDep.get(level - 3).getResources(),Resource.EMPTY) >=1;
            }
            return false;
        }
        return false; //nel caso eccezione
    }

//    @Override
//    public void switchLevels(int originLevel, int destLevel) {
//        //Se lo switch avviene interamente nel Warehouse
//        if ((originLevel >= 0 && originLevel <= 2) && (destLevel >= 0 && destLevel <= 2)) {
//            original.getBoard().getWareHouse().switchLevels(originLevel, destLevel);
//        }
//        //Se lo switch avviene dal Warehouse ad un deposito extra
//        else if((originLevel >= 0 && originLevel <= 2) && (destLevel >= 3 && destLevel <= extraDep.size() + 2)) {
//
//            int resCounter = 0;
//            int extraResCounter = 0;
//            for (Resource res : original.getBoard().getWareHouse().getLevels().get(originLevel)) {
//                if (res != Resource.EMPTY) resCounter++;
//            }
//            for (Resource res : extraDep.get(destLevel - 3).getResources()) {
//                if (res != Resource.EMPTY) extraResCounter++;
//            }
//            //Se c'è una risorsa nell'ExtraDep e la stessa risorsa nel Warehouse la switchLevel sposta
//            // la singola risorsa dal Warehouse all'ExtraDepp
//            if (resCounter == 1 && extraResCounter == 1) {
//                extraDep.get(destLevel - 3).addResource(placeableRes);
//                Arrays.fill(original.getBoard().getWareHouse().getLevels().get(originLevel), Resource.EMPTY);
//            }
//            else {
//                ArrayList<Resource> helper = new ArrayList<>();
//                helper.addAll(extraDep.get(destLevel - 3).getResources()); //Salvo le risorse nel destLevel
//                extraDep.get(destLevel - 3).getResources().clear(); //Svuoto il destLevel
//                extraDep.get(destLevel - 3).getResources().addAll(Arrays.asList(original.getBoard().getWareHouse().getLevels().get(originLevel))); //Aggiungo al dest level le risorse dell'origin level
//                while (extraDep.get(destLevel - 3).getResources().size() < 2) { //Fintanto che non ho 2 elementi nel dest level lo riempio con EMPTY
//                    extraDep.get(destLevel - 3).getResources().add(Resource.EMPTY);
//                }
//                for (int k = 0; k < getDeposits().get(destLevel).length || k < helper.size(); k++) {
//                    original.getBoard().getWareHouse().getLevels().get(originLevel)[k] = helper.get(k);
//                }
//                if (getDeposits().get(destLevel).length > helper.size()) {
//                    for (int i = helper.size(); i < getDeposits().get(destLevel).length; i++) {
//                        extraDep.get(destLevel - 3).getResources().set(i, Resource.EMPTY);
//                    }
//                }
//            }
//        }
//        //Se lo switch avviene      DA un deposito Extra     AL Warehouse
//        else if((originLevel >= 3 && originLevel <= extraDep.size() + 2) && (destLevel >= 0 && destLevel <= 2)) {
//            int resCounter = 0;
//            int extraResCounter = 0;
//            for (Resource res : original.getBoard().getWareHouse().getLevels().get(destLevel)) {
//                if (res != Resource.EMPTY) extraResCounter++;
//            }
//            for (Resource res : extraDep.get(originLevel - 3).getResources()) {
//                if (res != Resource.EMPTY) resCounter++;
//            }
//
//            if (resCounter == 1 && extraResCounter == 1) {
//                original.getBoard().getWareHouse().getLevels().get(destLevel)[1] = placeableRes;
//                extraDep.get(originLevel - 3).getResources().clear();
//                extraDep.get(originLevel - 3).getResources().add(Resource.EMPTY);
//                extraDep.get(originLevel - 3).getResources().add(Resource.EMPTY);
//            }
//            else
//            {
//                ArrayList<Resource> helper = new ArrayList<>();
//                helper.addAll(Arrays.asList(original.getBoard().getWareHouse().getLevels().get(destLevel))); //Salvo le risorse del destLevel
//                Arrays.fill(original.getBoard().getWareHouse().getLevels().get(destLevel), Resource.EMPTY); //Svuoto il destLevel
//
//                for (int j = 0; j < getDeposits().get(originLevel).length; j++) { //Aggiungo al dest level le risorse dell'origin level
//                    if (!getDeposits().get(originLevel)[j].equals(Resource.EMPTY)) { //If necessario perché level.get(originLevel) potrebbe essere più lungo di levels.get(destLevel), ma avendo già controllato prima, gli elementi in più sarebbero solo gli EMPTY
//                        original.getBoard().getWareHouse().getLevels().get(destLevel)[j] = getDeposits().get(originLevel)[j];
//                    }
//                }
//                extraDep.get(originLevel - 3).getResources().clear();
//                extraDep.get(originLevel - 3).getResources().addAll(helper);
//                while (extraDep.get(originLevel).getResources().size() < 2) {
//                    extraDep.get(originLevel).getResources().add(Resource.EMPTY);
//                }
//            }
//        }
//        //Lo switch non può avvenire da un deposito extra ad un altro deposito extra in quanto le risorse piazzabili in essi si escludono a vicenda
//    }

     @Override
     public void switchLevels(int originLevel, int destLevel) {

         //Da Warehouse...
         if (originLevel <= 2) {
             // ... a Warehouse
             if (destLevel <= 2) {
                 original.switchLevels(originLevel, destLevel);
             }
             // ... a ExtraDep
             else {
                 for (int i = Math.min(original.getDeposits().get(originLevel).length, extraDep.get(destLevel - 3).getResources().size()); i > 0 ; i--) {
                     extraDep.get(destLevel - 3).getResources().set(i, original.getDeposits().get(originLevel)[i]);
                     original.getBoard().getWareHouse().getLevels().get(originLevel)[i] = Resource.EMPTY;
                 }
             }
         }
         // Da ExtraDep...
         else {
             // ... a Warehouse
             ArrayList<Resource> tmp = new ArrayList<>();
             tmp.addAll(extraDep.get(originLevel - 3).getResources());
             for (int i = 0; i < Math.min(original.getDeposits().get(destLevel).length, extraDep.get(originLevel - 3).getResources().size()); i++) {
                 extraDep.get(originLevel - 3).getResources().set(i, original.getDeposits().get(destLevel)[i]);
                 original.getBoard().getWareHouse().getLevels().get(destLevel)[i] = tmp.get(i);
             }
         }
     }



    private void add (Resource res, int level) {
        extraDep.get(level - 3).addResource(res);
    }

    private void remove (Resource res, int level) {
        extraDep.get(level).removeResource(res);
    }

    @Override
    public boolean checkSwitch(int originLevel, int destLevel)
    {
        //The player can't remove something the extra dep level but just put it in it or can't get from a level that he does'nt have
        if(originLevel >= 3 || destLevel > extraDep.size()+2)
            return false;
        //In case there are no extradep required
        if (originLevel <= 2 && destLevel <=2)
            return original.checkSwitch(originLevel,destLevel);
        else
            if()


    }

    @Override
    public void addResource(int level, Resource res) {
        if (level >= 0 && level <= 2) {
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
        } else if (place.equals("extradeposit")) {
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
        ArrayList<Resource[]> deposits = new ArrayList<>();
        deposits.addAll(original.getBoard().getWareHouse().getArrayListWareHouse());

        for(ExtraDepositLevel e : extraDep) {
            Resource[] extraLevel = e.getResources().toArray(new Resource[2]);
            deposits.add(extraLevel);
            }
        return deposits;
    }

    public ArrayList<ExtraDepositLevel> getExtraDep() {
        return extraDep;
    }
}



