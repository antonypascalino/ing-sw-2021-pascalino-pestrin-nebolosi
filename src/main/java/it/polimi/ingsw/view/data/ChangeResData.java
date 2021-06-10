package it.polimi.ingsw.view.data;

import it.polimi.ingsw.Request.MarketResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.ChangeResPlayer;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.Printer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Change res data.
 */
public class ChangeResData extends PlayerData
{
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
    private ArrayList<Resource> changes;

    /**
     * Instantiates a new Change res data.
     *
     * @param changes      the changes
     * @param originalData the original data
     */
    public ChangeResData(ArrayList<Resource> changes, PlayerData originalData) {
        this.originalData = originalData;
        this.changes = new ArrayList<>();
        if(originalData instanceof ChangeResData)
            changes.addAll(((ChangeResData) originalData).getChanges());
        this.changes.addAll(changes);
    }

    public ArrayList<Resource> getChanges()
    {
        return changes;
    }

    public ArrayList<MarketResource> handleWarehouse(ArrayList<Resource> res) {
        return originalData.handleWarehouse(changeEmpty(res));
    }

    public ArrayList<Resource> changeEmpty(ArrayList<Resource> res) {
        ArrayList<Resource> result = new ArrayList<>();
        for (Resource re : res) {
            //If the resource is an empty resource convert it
            if (re.equals(Resource.EMPTY)) {
                if (changes.size() == 1) {
                    re = changes.get(0);
                } else {
                    //Maybe the same thing can be done using the printResources method
                    re = originalData.getPrinter().printResource(changes);
                }

            }
            result.add(re);
        }
        return result;
    }

    /*
    public ArrayList<MarketResource> oldHandle(ArrayList<Resource> res){
        ArrayList<MarketResource> marketRes = new ArrayList<MarketResource>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        ArrayList<Resource> wareHouseRes = new ArrayList<Resource>();

        for(Resource[] lv : originalData.getDeposits()){
            wareHouseRes.addAll(Arrays.asList(lv));
        }
        Resource[] level = new Resource[3];
        for(int p = 0; p < res.size(); p++) {
            if (res.get(p).equals(Resource.EMPTY)) {
                ArrayList<Resource> convert = new ArrayList<Resource>();
                convert.addAll(changes);
                res.set(p, originalData.getPrinter().printResources(convert)); //converts the resource selected on the spot
            }
            if (res.get(p).equals(Resource.FAITH)) {
                MarketResource m = new MarketResource(res.get(p), -1);
                marketRes.add(m);
                p++;
            }

            for(int l = 0; l < originalData.getDeposits().size(); l++){
                Resource resource = res.get(p);
                if(Arrays.stream(originalData.getDeposits().get(l)).anyMatch(null) && !wareHouseRes.contains(res.get(p))) {
                    tmp.add(l);
                }
                if(!Arrays.stream(originalData.getDeposits().get(l)).anyMatch(null) && Arrays.stream(originalData.getDeposits().get(l)).anyMatch(x -> x.equals(resource)) && originalData.getDeposits().get(l).length < 3){
                    tmp.add(l);
                }
                else{
                    System.out.println("The resource" + "" + res.get(p) + "" + "was discarded");
                }
            }
            int wareHouseLevel = originalData.getPrinter().printIntegers(tmp, false);
            MarketResource mr = new MarketResource(res.get(p), wareHouseLevel);
            System.out.println("The resource " + res.get(p) + "" + "was put in level " + wareHouseLevel);
            marketRes.add(mr);
        }
        return marketRes;
    }
    */

}
