package it.polimi.ingsw.view.data;

import it.polimi.ingsw.controller.Request.MappedResource;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.clientCards.ClientLeaderCard;
import it.polimi.ingsw.view.clientCards.ClientExtraProd;

import java.util.ArrayList;

/**
 * It's the player with the {@link ClientExtraProd} {@link ClientLeaderCard} (it extends {@link PlayerData}).
 */
public class ExtraProdData extends PlayerData {
    private ArrayList<String> extraProdID;
    private ArrayList<Resource> prodRequired;

    /**
     * Instantiates a new {@link ExtraDepData}
     *
     * @param extraProdID  the extra prod id
     * @param prodRequired the prod required
     * @param original     the original PlayerData
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

    public ArrayList<String> slotCardsFilter(ArrayList<MappedResource> mapped) {
        ArrayList<String> clonedDev = new ArrayList<>();
        ArrayList<String> clonedLeader = new ArrayList<>();

        clonedDev.addAll(originalData.getFrontCardsID());
        clonedLeader.addAll(extraProdID);
        ArrayList<Resource> allRes = new ArrayList<>();
        for (MappedResource m : mapped) {
            allRes.add(m.getResource());
        }

        clonedDev.removeIf(card -> !allRes.containsAll(originalData.getCardFromID(card).getRequired()));
        for (int i = 0; i < prodRequired.size(); i++) {
            if (allRes.contains(prodRequired.get(i))) {
                clonedDev.add(clonedLeader.get(i));
            }
        }
        clonedDev.add("BASIC");
        return clonedDev;
    }

    /**
     * Gets extra prod id.
     *
     * @return the extra prod id
     */
    public ArrayList<String> getExtraProdID() {
        return extraProdID;
    }

    /**
     * Gets prod required.
     *
     * @return the prod required
     */
    public ArrayList<Resource> getProdRequired() {
        return prodRequired;
    }
}
