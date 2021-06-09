package it.polimi.ingsw.view.clientCards;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Client extra dep.
 */
public class ClientExtraDep extends ClientLeaderCard {

    private Resource placeableRes;
    private Resource required;

    /**
     * Instantiates a new Client extra dep.
     *
     * @param victoryPoints the victory points
     * @param required      the required
     * @param placeableRes  the placeable res
     * @param leaderID      the leader id
     */
    public ClientExtraDep(int victoryPoints, Resource required, Resource placeableRes, String leaderID) {
        super(leaderID, victoryPoints, placeableRes);
        this.placeableRes = placeableRes;
        this.required = required;
    }

    public boolean canBePlayed(PlayerData data) {
        ArrayList<Resource> allRes = new ArrayList<Resource>();
        for (MappedResource mappedRes : data.allResources()) {
            allRes.add(mappedRes.getResource());
        }
        ArrayList<Resource> allDataRes = new ArrayList<>();
        for (MappedResource mappedRes : data.allResources()) {
            allDataRes.add(mappedRes.getResource());
        }
        return (Collections.frequency(allDataRes, required) >= 5);
    }

    public String toString() {
        return "Extra Deposit Leader Card:\nYou will have an Extra Deposit of 2 spaces in you Warehouse; in it you can deposit " + placeableRes + "S" + "\nTo play this card you need to have 5 " + required + "S";
    }


}
