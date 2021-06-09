package it.polimi.ingsw.view.data;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.client.Client;
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


    /**
     * Needs to be overrided so it count the discount
     * @return
     */
    @Override
    public ClientDevCard getCardFromID(String cardID) {
        ClientDevCard tmp = super.getCardFromID(cardID);
        ArrayList<Resource> newPrice = tmp.getPrice();
        for(Resource res : discount)
            if(newPrice.contains(res))
                newPrice.remove(res);
        ClientDevCard discounted =  new ClientDevCard(tmp.getCardID(),tmp.getColor(),tmp.getLevel(), tmp.getLevel(), tmp.getRequired(), tmp.getProduces(), newPrice);
        return discounted;
    }

    @Override
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
