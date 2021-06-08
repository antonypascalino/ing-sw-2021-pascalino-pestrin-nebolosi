package it.polimi.ingsw.view.data;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.clientCards.ClientDevCard;
import it.polimi.ingsw.view.Printer;

import java.util.ArrayList;

/**
 * The type Discount data.
 */
public class DiscountData extends PlayerData{


    private ArrayList<Resource> discount;

    public DiscountData(ArrayList<Resource> discount, PlayerData originalData) {
        this.originalData = originalData;
        this.discount = discount;
    }

    public ArrayList<String> tableCardsFilter(ArrayList<MappedResource> mapped){

        ArrayList<String> cloned = new ArrayList<String>();
        cloned.addAll(originalData.getTableCardsID());
        ArrayList<Resource> allRes = new ArrayList<Resource>();
        for(MappedResource m : mapped){
            allRes.add(m.getResource());
        }
        allRes.addAll(discount);
        cloned.removeIf(card -> !allRes.containsAll(getCardFromID(card).getPrice()));
        return cloned;
    }

}
