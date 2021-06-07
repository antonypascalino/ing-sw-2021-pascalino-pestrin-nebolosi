package it.polimi.ingsw.view.data;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.Request.MarketResource;
import it.polimi.ingsw.model.Table.Resource;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Extra dep data.
 */
public class ExtraDepData extends PlayerData {

    //    private ArrayList<TurnState> turnStates;
//    private TurnState turnState;
//    private ArrayList<Resource[]> wareHouse;
//    private ArrayList<Resource> strongBox;
//    private ArrayList<String> tableCardsID; //just the front table cards
//    private int faithPoints;
//    private int victoryPoints;
//    private ArrayList<String> cardsID;  //3 front cards + basic + extraProd
//    private ArrayList<String> leadersID;
//    private ArrayList<String> leadersPlayedID;
//    private Resource[][] market;
//    private Printer printer;
    private ArrayList<Resource[]> extraDep;
    private ArrayList<Resource> placeableRes;


    /**
     * Instantiates a new Extra dep data.
     *
     * @param originalData the original data
     * @param placeableRes the placeable res
     */
    public ExtraDepData(PlayerData originalData, ArrayList<Resource> placeableRes) {
        //        this.turnStates = turnStates;
//        this.turnState = turnState;
//        this.wareHouse = wareHouse;
//        this.strongBox = strongBox;
//        this.faithPoints = faithPoints;
//        this.victoryPoints = victoryPoints;
//        this.cardsID = cardsID;
//        this.leadersID = leadersID;
//        this.market = market;
//        this.tableCardsID = tableCardsID;
//        this.leadersPlayedID = leadersPlayedID;
        extraDep = new ArrayList<Resource[]>();
        originalData = originalData;

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
        ArrayList<Resource[]> wareHouse = new ArrayList<Resource[]>();
        wareHouse.addAll(originalData.getDeposits());
        ArrayList<MarketResource> marketRes = new ArrayList<MarketResource>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        ArrayList<Resource> wareHouseRes = new ArrayList<Resource>();
        ArrayList<Resource> depositRes = new ArrayList<Resource>();

        for (Resource[] lv : wareHouse) {
            wareHouseRes.addAll(Arrays.asList(lv));
        }
        for (Resource[] ly : extraDep) {
            depositRes.addAll(Arrays.asList(ly));
        }

        for (int p = 0; p < res.size(); p++) {
            if (res.get(p).equals(Resource.EMPTY)) {
                MarketResource m = new MarketResource(res.get(p), -2);
                marketRes.add(m);
                p++;
            }

            if (res.get(p).equals(Resource.FAITH)) {
                MarketResource m = new MarketResource(res.get(p), -1);
                marketRes.add(m);
                p++;
            }

            for (int l = 0; l < wareHouse.size(); l++) {
                Resource resource = res.get(p);
                //se è pieno
                if (!Arrays.stream(wareHouse.get(l)).anyMatch(null)) {
                    continue;
                }
                //se ha degli spazi vuoti
                if (Arrays.stream(wareHouse.get(l)).anyMatch(null)) {
                    //se è vuoto
                    if (wareHouse.get(l)[0] == null) {
                        boolean empty = true;
                        for (int x = 0; x < wareHouse.size(); x++) {
                            if (x != l) {
                                if (Arrays.stream(wareHouse.get(l)).anyMatch(z -> z.equals(resource))) {
                                    empty = false;
                                    break;
                                }
                            }
                        }
                        if (empty) {
                            tmp.add(l);
                        }
                    }
                    //se ha la mia risorsa
                    else if (wareHouse.get(l)[0] == res.get(p)) {
                        tmp.add(l);
                    }
                }
            }

            for (int d = 3; d < extraDep.size() + 3; d++) {
                if (res.get(p).equals(placeableRes.get(d - 3)) && Arrays.stream(extraDep.get(d - 3)).anyMatch(null)) {
                    tmp.add(d);
                }

            }

            int wareHouseLevel = originalData.getPrinter().printIntegers(tmp, false);
            MarketResource mr = new MarketResource(res.get(p), wareHouseLevel);
            System.out.println("The resource " + res.get(p) + "" + "was put in level " + wareHouseLevel);
            marketRes.add(mr);
        }

        return marketRes;
    }

    public int switchLevels(int origin) {
        ArrayList<Resource[]> wareHouse = new ArrayList<Resource[]>();
        wareHouse.addAll(originalData.getDeposits());
        ArrayList<Integer> levels = new ArrayList<Integer>();
        int counterOr = 0;
        int counterDes = 0;

        if (origin <= 2) {
            //conta origine
            for (int co = 0; co < wareHouse.get(origin).length; co++) {
                if (wareHouse.get(origin)[co] == null) {
                    break;
                }
                counterOr = co;
            }

            for (int i = 0; i < wareHouse.size(); i++) {
                //conta destinazione
                for (int cd = 0; cd < wareHouse.get(i).length; cd++) {
                    if (wareHouse.get(i)[cd] == null) {
                        break;
                    }
                    counterDes = cd;
                }
                if (counterOr <= wareHouse.get(i).length && counterDes <= wareHouse.get(origin).length) {
                    levels.add(i);
                }
            }
            for (int e = 3; e < extraDep.size() + 3; e++) {
                for (int cd = 0; cd < extraDep.get(e - 3).length; cd++) {
                    if (extraDep.get(e - 3)[cd] == null) {
                        break;
                    }
                    counterDes = cd;
                }
                final Resource placeRes = placeableRes.get(e - 3);
                int finalCounterOr = counterOr;
                int finalCounterDes = counterDes;
                int finalE = e;
                if (Arrays.stream(wareHouse.get(origin)).anyMatch(x -> x.equals(placeRes) && finalCounterOr <= extraDep.get(finalE - 3).length && finalCounterDes <= wareHouse.get(origin).length)) {
                    levels.add(e);
                }


            }
            levels.remove(origin);
        }
        if (origin > 3) {
            for (int co = 0; co < extraDep.get(origin).length; co++) {
                if (extraDep.get(origin)[co] == null) {
                    break;
                }
                counterOr = co;
            }
            Resource placeable = placeableRes.get(origin - 3);
            for (int k = 0; k < wareHouse.size(); k++) {
                for (int cd = 0; cd < wareHouse.get(k).length; cd++) {
                    if (wareHouse.get(k)[cd] == null) {
                        break;
                    }
                    counterDes = cd;


                }
                if (Arrays.stream(wareHouse.get(origin)).anyMatch(x -> x.equals(placeable)) && counterOr <= wareHouse.get(k).length && counterDes <= extraDep.get(origin).length) {
                    levels.add(k);
                }
            }
        }
        return originalData.getPrinter().printIntegers(levels, false);
    }

    public ArrayList<Resource[]> getDeposits() {
        ArrayList<Resource[]> allDeposits = new ArrayList<Resource[]>();
        allDeposits.addAll(originalData.getDeposits());
        allDeposits.addAll(extraDep);
        return allDeposits;
    }

    @Override
    public void setWareHouse(ArrayList<Resource[]> wareHouse) {
        if (wareHouse.size() == 3) {
            originalData.setWareHouse(wareHouse);
        } else if (wareHouse.size() == 4) {
            extraDep.get(0)[0] = wareHouse.get(3)[0];
            extraDep.get(0)[1] = wareHouse.get(3)[1];
        } else if (wareHouse.size() == 5) {
            extraDep.get(0)[0] = wareHouse.get(3)[0];
            extraDep.get(0)[1] = wareHouse.get(3)[1];

            extraDep.get(1)[0] = wareHouse.get(4)[0];
            extraDep.get(1)[1] = wareHouse.get(4)[1];
        }
    }
}




