package it.polimi.ingsw.view.data;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.MarketResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.view.Printer;

import java.util.ArrayList;
import java.util.Arrays;

public class ExtraDepData extends PlayerData {

    private ArrayList<TurnState> turnStates;
    private TurnState turnState;
    private ArrayList<Resource[]> wareHouse;
    private ArrayList<Resource> strongBox;
    private ArrayList<String> tableCardsID; //just the front table cards
    private int faithPoints;
    private int victoryPoints;
    private ArrayList<String> cardsID;  //3 front cards + basic + extraProd
    private ArrayList<String> leadersID;
    private ArrayList<String> leadersPlayedID;
    private Resource[][] market;
    private Printer printer;
    private ArrayList<Resource[]> extraDep;


    public ExtraDepData(ArrayList<Resource[]> extraDep, ArrayList<String> cardID, ArrayList<TurnState> turnStates, TurnState turnState, ArrayList<Resource[]> wareHouse, ArrayList<Resource> strongBox, int faithPoints, int victoryPoints, ArrayList<String> cardsID, ArrayList<String> leadersID, Resource[][] market, ArrayList<String> tableCardsID) {
        this.turnStates = turnStates;
        this.turnState = turnState;
        this.wareHouse = wareHouse;
        this.strongBox = strongBox;
        this.faithPoints = faithPoints;
        this.victoryPoints = victoryPoints;
        this.cardsID = cardsID;
        this.leadersID = leadersID;
        this.market = market;
        this.tableCardsID = tableCardsID;
        this.extraDep = extraDep;
    }

    public ArrayList<MappedResource> allResources() {
        ArrayList<MappedResource> tmp = new ArrayList<MappedResource>();
        Resource[] level = new Resource[3];
        for (Resource[] l : wareHouse) {
            for (Resource w : level) {
                MappedResource mappedW = new MappedResource(w, "warehouse");
                tmp.add(mappedW);
            }
        }
        for (Resource s : strongBox) {
            MappedResource mappedS = new MappedResource(s, "strongbox");
            tmp.add(mappedS);
        }

        for (Resource[] y : extraDep) {
            for (Resource e : level) {
                MappedResource mappedE = new MappedResource(e, "extradeposit");
                tmp.add(mappedE);
            }
        }


        return tmp;
    }

    public void removeMappedResource(ArrayList<MappedResource> mapped) {
        for (MappedResource m : mapped) {
            if (m.getPlace().equals("warehouse")) {
                for (Resource[] l : wareHouse) {
                    Resource[] current = l;
                    for (int j = 0; j < current.length; j++)
                        if (current[j].equals(m.getResource())) {
                            current[j] = null;
                        }
                }
            }
            if (m.getPlace().equals("extradeposit")) {
                for (Resource[] y : extraDep) {
                    Resource[] currentE = y;
                    for (int d = 0; d < currentE.length; d++)
                        if (currentE[d].equals(m.getResource())) {
                            currentE[d] = null;
                        }
                }
            }
            if (m.getPlace().equals("strongbox")) {
                strongBox.remove(m.getResource());
            }
        }
    }

    public ArrayList<MarketResource> handleWarehouse(ArrayList<Resource> res) {
        ArrayList<MarketResource> marketRes = new ArrayList<MarketResource>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        ArrayList<Resource> depositRes = new ArrayList<Resource>();
        ArrayList<Resource> leaderRes = new ArrayList<Resource>();

        for (Resource[] lv : wareHouse) {
            depositRes.addAll(Arrays.asList(lv));
        }
        for (Resource[] ly : extraDep) {
            depositRes.addAll(Arrays.asList(ly));
        }
        for (String s : leadersPlayedID) {
            leaderRes.add(getLeaderFromID(s).getDeposit());
        }

        Resource[] level = new Resource[3];
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
                if (Arrays.stream(wareHouse.get(l)).anyMatch(null) && !depositRes.contains(res.get(p))) {
                    tmp.add(l);
                }
                if (!Arrays.stream(wareHouse.get(l)).anyMatch(null) && Arrays.stream(wareHouse.get(l)).anyMatch(x -> x.equals(resource)) && wareHouse.get(l).length < 3) {
                    tmp.add(l);
                } else {
                    System.out.println("The resource" + "" + res.get(p) + "" + "was discarded");
                }
            }
            for (int y = 0; y < extraDep.size(); y++) {
                Resource resource = res.get(p);
                if (extraDep.get(y).length < 2 && leaderRes.contains(res.get(p))) {
                    tmp.add(y);
                } else {
                    System.out.println("The resource" + "" + res.get(p) + "" + "was discarded");
                }
            }
            int wareHouseLevel = printer.printIntegers(tmp, false);
            MarketResource mr = new MarketResource(res.get(p), wareHouseLevel);
            System.out.println("The resource " + res.get(p) + "" + "was put in level " + wareHouseLevel);
            marketRes.add(mr);
        }
        return marketRes;
    }

    public int switchLevels(int origin) {

        ArrayList<Integer> levels = new ArrayList<Integer>();
        int l = 0;
        for (int i = 0; i < wareHouse.size(); i++) {
            if (wareHouse.get(origin).length <= wareHouse.get(i).length) {
                levels.add(i);
                l = i;
            }
        }
        for (int i = 0; i < wareHouse.size(); i++) {
            for (int j = 0; j < extraDep.size(); j++) {
                Resource[] dep = extraDep.get(j);
                if (Arrays.stream(wareHouse.get(i)).anyMatch(x -> x.equals(dep))) {
                    levels.add(j);
                }
            }


        }

        return printer.printIntegers(levels, false);
    }
}

