package it.polimi.ingsw.view.data;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.Request.MarketResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.clientCards.ClientLeaderCard;
import it.polimi.ingsw.view.clientCards.ClientExtraDep;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * It's the player with the {@link ClientExtraDep} {@link ClientLeaderCard} (it extends {@link PlayerData}).
 */
public class ExtraDepData extends PlayerData {
    private ArrayList<Resource[]> extraDep;
    private ArrayList<Resource> placeableRes;

    /**
     * Instantiates a new {@link ExtraDepData}.
     *
     * @param originalData the original PlayerData
     * @param placeableRes the placeable resource
     */
    public ExtraDepData(PlayerData originalData, ArrayList<Resource> placeableRes) {

        this.originalData = originalData;
        extraDep = new ArrayList<>();
        this.placeableRes = new ArrayList<>();
        if(originalData instanceof ExtraDepData){
            extraDep.addAll(((ExtraDepData) originalData).extraDep);
            this.placeableRes.addAll(((ExtraDepData) originalData).placeableRes);
            this.originalData = originalData.originalData;
        }
        this.placeableRes.addAll(placeableRes);
        Resource[] tmp = new Resource[2];
        tmp[0] = Resource.EMPTY;
        tmp[1] = Resource.EMPTY;
        extraDep.add(tmp);
    }

    public ArrayList<MappedResource> allResources() {

        ArrayList<MappedResource> tmp = new ArrayList<MappedResource>();

        for (Resource[] l : originalData.getDeposits()) {
            for (Resource w : l) {
                MappedResource mappedW = new MappedResource(w, "warehouse");
                tmp.add(mappedW);
            }
        }
        for (Resource s : originalData.getStrongBox()) {
            MappedResource mappedS = new MappedResource(s, "strongbox");
            tmp.add(mappedS);
        }

        for (Resource[] y : extraDep) {
            for (Resource e : y) {
                MappedResource mappedE = new MappedResource(e, "extradeposit");
                tmp.add(mappedE);
            }
        }


        return tmp;
    }


    public ArrayList<MarketResource> handleWarehouse(ArrayList<Resource> res) {
        ArrayList<Resource[]> wareHouseClone = new ArrayList<Resource[]>();
        ArrayList<Resource[]> helper = new ArrayList<Resource[]>();
        ArrayList<MarketResource> marketRes = new ArrayList<MarketResource>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        ArrayList<Resource> wareHouseRes = new ArrayList<Resource>();

        wareHouseClone.addAll(this.getDeposits());
        helper.addAll(originalData.getDeposits());
        for (Resource[] lv : helper) {
            wareHouseRes.addAll(Arrays.asList(lv));
        }

        res = changeEmpty(res);

        for (Resource re : res) {
            tmp.clear();
            if (re.equals(Resource.EMPTY)) {
                MarketResource m = new MarketResource(re, -2);
                marketRes.add(m);
                continue;
            }

            if (re.equals(Resource.FAITH)) {
                MarketResource m = new MarketResource(re, -1);
                marketRes.add(m);
                continue;
            }

            for (int l = 0; l < 3; l++) {
                //se è pieno
                if (!Arrays.stream(wareHouseClone.get(l)).anyMatch(r -> r.equals(Resource.EMPTY))) {
                    continue;
                }
                //se ha degli spazi vuoti
                if (Arrays.stream(wareHouseClone.get(l)).anyMatch(r -> r.equals(Resource.EMPTY))) {
                    //se è vuoto
                    if (wareHouseClone.get(l)[0].equals(Resource.EMPTY)) {
                        if(!wareHouseRes.contains(re)){
                            tmp.add(l);
                        }
                    }
                    //se ha la mia risorsa
                    else if (wareHouseClone.get(l)[0] == re) {
                        tmp.add(l);
                    }
                }
            }
            //If the player has more than one extraDep card played each extraProd is an element of the array
            for (int d = 3; d < extraDep.size() + 3; d++) {
                if (re.equals(placeableRes.get(d - 3)) && Arrays.stream(extraDep.get(d - 3)).anyMatch(r -> r.equals(Resource.EMPTY))) {
                    tmp.add(d);
                }

            }
            if(tmp.size() == 0) {
                originalData.getPrinter().printMessage("You hav no space for " + re + ". It was discarded!");
                MarketResource mr = new MarketResource(re, -1);
                marketRes.add(mr);
                continue;
            }
            originalData.getPrinter().printMessage("\nWhere do you wanna put " + re + "?");
            int wareHouseLevel = originalData.getPrinter().printIntegers(tmp, false, true);
            MarketResource mr = new MarketResource(re, wareHouseLevel);
            if (wareHouseLevel == -1)
            {
                originalData.getPrinter().printMessage("The resource " + re + " was discarded!");
                marketRes.add(mr);
                continue;
            }
            else originalData.getPrinter().printMessage("The resource " + re + " " + "was put in level " + (wareHouseLevel + 1));
            marketRes.add(mr);

            //Insert the resource in a clone for checking the spaces
            if(wareHouseLevel <= 2){
                for(int d = 0; d < wareHouseClone.get(wareHouseLevel).length; d++){
                    if(wareHouseClone.get(wareHouseLevel)[d] == Resource.EMPTY){
                        wareHouseClone.get(wareHouseLevel)[d] = re;
                        wareHouseRes.add(re);
                        wareHouseRes.remove(Resource.EMPTY);
                        break;
                    }
                }
            }
        }
        return marketRes;
    }

    public int switchLevels(int origin) {
        ArrayList<Resource[]> wareHouse = new ArrayList<Resource[]>();
        wareHouse.addAll(originalData.getDeposits());
        ArrayList<Integer> levels = new ArrayList<Integer>();
        int counterOr = 0;

        //da Warehouse ...
        if (origin <= 2) {
            //conta origine
            for (int co = 0; co < wareHouse.get(origin).length; co++) {
                if (wareHouse.get(origin)[co] == Resource.EMPTY) {
                    break;
                }
                counterOr++;
            }
            // ... a Warehouse
            for (int i = 0; i < wareHouse.size(); i++) {
                if (i != origin) {
                    //conta destinazione
                    int counterDes = 0;
                    for (int cd = 0; cd < wareHouse.get(i).length; cd++) {
                        if (wareHouse.get(i)[cd] == Resource.EMPTY) {
                            break;
                        }
                        counterDes++;
                    }
                    if (counterOr <= wareHouse.get(i).length && counterDes <= wareHouse.get(origin).length && !(counterDes == 0 && counterOr == 0)) {
                        levels.add(i);
                    }
                }
            }
            // ... a Extra Dep
            for (int e = 3; e < extraDep.size() + 3; e++) {
                int counterDes = 0;
                for (int cd = 0; cd < extraDep.get(e - 3).length; cd++) {
                    if (extraDep.get(e - 3)[cd] == Resource.EMPTY) {
                        break;
                    }
                    counterDes++;
                }
                final Resource placeRes = placeableRes.get(e - 3);
                int finalCounterOr = counterOr;
                int finalCounterDes = counterDes;
                int finalE = e;
                if (Arrays.stream(wareHouse.get(origin)).anyMatch(x -> x.equals(placeRes) && finalCounterOr <= extraDep.get(finalE - 3).length && finalCounterDes <= wareHouse.get(origin).length) && !(counterDes == 0 && counterOr == 0)) {
                    levels.add(e);
                }
            }
            //levels.remove(origin);
        }
        // Da Extra Dep ...
        if (origin >= 3) {
            for (int co = 0; co < extraDep.get(origin - 3).length; co++) {
                if (extraDep.get(origin -3)[co] == Resource.EMPTY) {
                    break;
                }
                counterOr ++;
            }
            // ... a Warehouse
            Resource placeable = placeableRes.get(origin - 3);
            int counterDes = 0;
            for (int k = 0; k < wareHouse.size(); k++) {
                for (int cd = 0; cd < wareHouse.get(k).length; cd++) {
                    if (wareHouse.get(k)[cd] == Resource.EMPTY) {
                        break;
                    }
                    counterDes ++;
                }
                if (Arrays.stream(extraDep.get(origin-3)).anyMatch(x -> x.equals(placeable)) && counterOr <= wareHouse.get(k).length && counterDes <= extraDep.get(origin -3).length && !(counterDes == 0 && counterOr == 0)) {
                    levels.add(k);
                }
            }
        }
        if (levels.size() == 0) {
            originalData.getPrinter().printMessage("You can't switch this level!");
            return -1;
        }
        return originalData.getPrinter().printIntegers(levels, false, false);
    }

    public ArrayList<Resource[]> getDeposits() {
        ArrayList<Resource[]> allDeposits = new ArrayList<Resource[]>();
        allDeposits.addAll(originalData.getDeposits());
        allDeposits.addAll(extraDep);
        return allDeposits;
    }

    @Override
    public void setWareHouse(ArrayList<Resource[]> wareHouse) {
        if (wareHouse.size() == 4) {
            extraDep.set(0,wareHouse.remove(3));
            originalData.setWareHouse(wareHouse);
        } else if (wareHouse.size() == 5) {
            extraDep.set(0,wareHouse.remove(3));
            extraDep.set(1,wareHouse.remove(3));
            originalData.setWareHouse(wareHouse);
        }
    }

    @Override
    public ArrayList<TurnState> turnStateFilter(){
        ArrayList<TurnState> tmp = new ArrayList<TurnState>();
        //tmp.add(TurnState.QUIT);
        tmp.add(TurnState.CHECK_STATS);
        tmp.add(TurnState.PRODUCE);
        tmp.add(TurnState.BUY_DEV_CARD);
        tmp.add(TurnState.GET_FROM_MARKET);
        tmp.add(TurnState.DISCARD_LEADER_CARD);

        for(String s : originalData.getLeaders()){
            if(getLeaderFromID(s).canBePlayed(this)) {
                tmp.add(TurnState.PLAY_LEADER_CARD);
                break;
            }
        }

        if(originalData.getTurnStates().contains(TurnState.BUY_DEV_CARD) || originalData.getTurnStates().contains(TurnState.PRODUCE) || originalData.getTurnStates().contains(TurnState.GET_FROM_MARKET)){
            tmp.remove(TurnState.PRODUCE);
            tmp.remove(TurnState.BUY_DEV_CARD);
            tmp.remove(TurnState.GET_FROM_MARKET);
            tmp.add(TurnState.END_TURN);
        }

        if(originalData.getTurnStates().contains(TurnState.PLAY_LEADER_CARD)){
            tmp.remove(TurnState.PLAY_LEADER_CARD);
            tmp.remove(TurnState.DISCARD_LEADER_CARD);
        }

        if(originalData.getTurnStates().contains(TurnState.DISCARD_LEADER_CARD)){
            tmp.remove(TurnState.PLAY_LEADER_CARD);
            tmp.remove(TurnState.DISCARD_LEADER_CARD);
        }
        //Se non ha carte con cui può produrre (ha sempre almeno la basic)
        if( allResources().size() == 0) {
            tmp.remove(TurnState.PRODUCE);
        }

        boolean empty = true;
        for(int i = 0; i < getDeposits().size(); i++){

            for(int k = 0; k < getDeposits().get(i).length; k++){
                if(!getDeposits().get(i)[k].equals(Resource.EMPTY)){
                    empty = false;
                    break;
                }
            }
        }
        if(!empty){
            tmp.add(TurnState.MOVE_RESOURCE);
        }
        else{
            if(originalData.getStrongBox().size() == 0){
                tmp.remove(TurnState.PRODUCE);
                tmp.remove(TurnState.BUY_DEV_CARD);

            }
        }
        if(originalData.getLeaders().size() == 0){
            tmp.remove(TurnState.DISCARD_LEADER_CARD);
            tmp.remove(TurnState.PLAY_LEADER_CARD);
        }
        return tmp;
    }

    public ArrayList<MappedResource> createMappedRes(ArrayList<Resource> res){
        ArrayList<MappedResource> tmp = new ArrayList<MappedResource>();
        ArrayList<MappedResource> toSelect = new ArrayList<MappedResource>();
        ArrayList<MappedResource> mappedRes = new ArrayList<MappedResource>();

        //gets all the res
        tmp.addAll(allResources());

        for (Resource re : res) {
            for (MappedResource map : tmp) {
                if (map.getResource().equals(re)) {
                    toSelect.add(map);
                }
            }
            MappedResource selected = originalData.getPrinter().printMappedRes(toSelect);
            mappedRes.add(selected);
            tmp.remove(selected);
            toSelect.clear();
        }

        return mappedRes;
    }

}




