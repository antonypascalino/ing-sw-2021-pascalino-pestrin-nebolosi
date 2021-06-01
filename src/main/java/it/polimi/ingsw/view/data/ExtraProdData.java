package it.polimi.ingsw.view.data;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.clientCards.ClientDevCard;
import it.polimi.ingsw.view.Printer;

import java.util.ArrayList;

public class ExtraProdData extends PlayerData{
    private ArrayList<String> extraProdID;
    private ArrayList<Resource> prodRequired;

    public ExtraProdData(ArrayList<String> extraProdID, ArrayList<Resource> prodRequired) {
        this.extraProdID = extraProdID;
        this.prodRequired = prodRequired;
    }

    public ArrayList<String> slotCardsFilter(ArrayList<MappedResource> mapped){
        ArrayList<String> clonedDev = new ArrayList<String>();
        ArrayList<String> clonedLeader = new ArrayList<String>();

        clonedDev.addAll(originalData.getFrontCardsID());
        clonedLeader.addAll(extraProdID);
        ArrayList<Resource> allRes = new ArrayList<Resource>();
        for(MappedResource m : mapped){
            allRes.add(m.getResource());
        }

        clonedDev.removeIf(card -> !allRes.containsAll(originalData.getCardFromID(card).getRequired()));
        for(int i = 0; i < prodRequired.size(); i++){
            if(allRes.contains(prodRequired.get(i))){
                clonedDev.add(clonedLeader.get(i));
            }
        }
        return clonedDev;
    }
}
