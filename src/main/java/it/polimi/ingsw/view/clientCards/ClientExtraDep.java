package it.polimi.ingsw.view.clientCards;

import it.polimi.ingsw.controller.Request.MappedResource;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The {@link ClientLeaderCard} that gives to the {@link PlayerData} an extra level in his warehouse.
 * In that extra level the player can only place a single type of {@link Resource}.
 */
public class ClientExtraDep extends ClientLeaderCard {

    private Resource placeableRes;
    private Resource required;

    /**
     * Instantiates a new Extra deposit {@link ClientLeaderCard}.
     *
     * @param victoryPoints the victory points the {@link ClientLeaderCard} gives the {@link PlayerData} when played.
     * @param required      the 5 required {@link Resource} the {@link PlayerData} has to own to play the {@link ClientLeaderCard}.
     * @param placeableRes  the {@link Resource} which can be placed in this extra level.
     * @param leaderID      the {@link ClientLeaderCard}'s ID.
     */
    public ClientExtraDep(int victoryPoints, Resource required, Resource placeableRes, String leaderID) {
        super(leaderID, victoryPoints, placeableRes);
        this.placeableRes = placeableRes;
        this.required = required;
    }

    public boolean canBePlayed(PlayerData data) {
        ArrayList<Resource> allRes = new ArrayList<>();
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
