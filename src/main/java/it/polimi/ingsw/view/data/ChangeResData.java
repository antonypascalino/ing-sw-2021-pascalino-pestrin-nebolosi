package it.polimi.ingsw.view.data;

import it.polimi.ingsw.controller.MarketResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.view.Printer;

import java.util.ArrayList;
import java.util.Arrays;

public class ChangeResData extends PlayerData
{
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
    private ArrayList<Resource> changes;

    public ChangeResData(ArrayList<Resource> changes, ArrayList<String> cardID, ArrayList<TurnState> turnStates, TurnState turnState, ArrayList<Resource[]> wareHouse, ArrayList<Resource> strongBox, int faithPoints, int victoryPoints, ArrayList<String> cardsID, ArrayList<String> leadersID, Resource[][] market, ArrayList<String> tableCardsID) {
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
        this.changes = changes;
    }

    public ArrayList<MarketResource> handleWarehouse(ArrayList<Resource> res){
        ArrayList<MarketResource> marketRes = new ArrayList<MarketResource>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        ArrayList<Resource> wareHouseRes = new ArrayList<Resource>();

        for(Resource[] lv : wareHouse){
            wareHouseRes.addAll(Arrays.asList(lv));
        }
        Resource[] level = new Resource[3];
        for(int p = 0; p < res.size(); p++) {
            if (res.get(p).equals(Resource.EMPTY)) {
                ArrayList<Resource> convert = new ArrayList<Resource>();
                convert.addAll(changes);
                res.set(p, printer.printResources(convert)); //converts the resource selected on the spot
            }
            if (res.get(p).equals(Resource.FAITH)) {
                MarketResource m = new MarketResource(res.get(p), -1);
                marketRes.add(m);
                p++;
            }

            for(int l = 0; l < wareHouse.size(); l++){
                Resource resource = res.get(p);
                if(Arrays.stream(wareHouse.get(l)).anyMatch(null) && !wareHouseRes.contains(res.get(p))) {
                    tmp.add(l);
                }
                if(!Arrays.stream(wareHouse.get(l)).anyMatch(null) && Arrays.stream(wareHouse.get(l)).anyMatch(x -> x.equals(resource)) && wareHouse.get(l).length < 3){
                    tmp.add(l);
                }
                else{
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
}
