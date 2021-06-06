package it.polimi.ingsw.view.clientCards;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class ClientExtraDep extends ClientLeaderCard {

    private Resource placeableRes;
    private Resource required;

    public ClientExtraDep(int victoryPoints, Resource required, Resource placeableRes, String leaderID) {
        super(leaderID, "Extra Deposit", victoryPoints);
        this.placeableRes = placeableRes;
        this.required = required;
    }

    public boolean canBePlayed(PlayerData data) {
        ArrayList<Resource> allRes = new ArrayList<Resource>();
        for (MappedResource mappedRes : data.allResources()) {
            allRes.add(mappedRes.getResource());
        }
        return allRes.contains(required);
    }

    public String toString() {
        return "Extra Deposit Leader Card:\nYou will have an Extra Deposit of 2 spaces in you Warehouse; in it you can deposit " + placeableRes + "S" + "\nTo play this card you need to have 5 " + required + "S";
    }


}
