package it.polimi.ingsw.view.clientCards;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class ClientExtraDep extends ClientLeaderCard {

    private Resource placeableRes;
    private ArrayList<Resource> required;

    public ClientExtraDep(String leaderID, Resource placeableRes, ArrayList<Resource> required) {
        super(leaderID, "Extra Deposit");
        this.placeableRes = placeableRes;
        this.required = required;
    }

    public boolean canBePlayed(PlayerData data) {
        ArrayList<Resource> allRes = new ArrayList<Resource>();
        for (MappedResource mappedRes : data.allResources()) {
            allRes.add(mappedRes.getResource());
        }
        return allRes.containsAll(required);
    }


}
