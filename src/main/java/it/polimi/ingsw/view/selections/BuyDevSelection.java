package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.BuyDevRequest;
import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The {@link Selection} that guides the player when he chose to buy a development card.
 */
public class BuyDevSelection extends Selection {


    @Override
    public void handleSelection(PlayerData data) {

        ArrayList<MappedResource> mappedRes = new ArrayList<>();
        ArrayList<MappedResource> allRes = new ArrayList<>();
        allRes.addAll(data.allResources());
        ArrayList<String> cards = new ArrayList<>();
        //Calls a method that return the cards that the player can buy
        cards.addAll(data.tableCardsFilter(allRes));

        //Check if the player can't buy any cards
        if (cards.isEmpty()) {
            data.getPrinter().printMessage("You can't buy any card!");
            data.getMenu().menuMaker();
            return;
        }

        String cardID = data.getPrinter().printDevCardID(cards, data);
        mappedRes.addAll(data.createMappedRes(data.getCardFromID(cardID).getPrice()));
        int slot = data.handleSlots(cardID);

        Request buyDevReq = new BuyDevRequest(data.getGameID(), data.getPlayerID(), cardID, mappedRes, slot);
        data.sendRequest(buyDevReq);

    }

}
