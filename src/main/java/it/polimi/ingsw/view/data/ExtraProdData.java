package it.polimi.ingsw.view.data;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.clientCards.ClientDevCard;
import it.polimi.ingsw.view.Printer;

import java.util.ArrayList;

/**
 * The type Extra prod data.
 */
public class ExtraProdData extends PlayerData{
    private ArrayList<String> extraProdID;
    private ArrayList<Resource> prodRequired;

    /**
     * Instantiates a new Extra prod data.
     *
     * @param extraProdID  the extra prod id
     * @param prodRequired the prod required
     */
    public ExtraProdData(ArrayList<String> extraProdID, ArrayList<Resource> prodRequired, PlayerData original) {
        this.extraProdID = new ArrayList<>();
        this.extraProdID = extraProdID;
        this.prodRequired = new ArrayList<>();
        if (original instanceof ExtraProdData) {
            prodRequired.addAll(((ExtraProdData) original).getProdRequired());
            extraProdID.addAll(((ExtraProdData) original).getExtraProdID());
        }
        this.prodRequired.addAll(prodRequired);
        this.extraProdID.addAll(extraProdID);
        this.originalData = original;
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
        clonedDev.add("BASIC");
        return clonedDev;
    }

    public ArrayList<String> getExtraProdID() {
        return extraProdID;
    }

    public ArrayList<Resource> getProdRequired() {
        return prodRequired;
    }
}
