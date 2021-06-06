package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.BuyDevRequest;
import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.Request.Production;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.view.Printer;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;
import java.util.Scanner;

public class BuyDevSelection extends Selection {


    @Override
    public void handleSelection(PlayerData data) {

        ArrayList<MappedResource> mappedRes = new ArrayList<MappedResource>();
        ArrayList<MappedResource> allRes = new ArrayList<MappedResource>();
        allRes.addAll(data.allResources());
        ArrayList<String> cards = new ArrayList<String>();
        cards.addAll(data.tableCardsFilter(allRes));
        if (cards.isEmpty()) {
            data.getPrinter().printMessage("You can't buy any card!");
            data.getMenu().menuMaker();
            return;
        }
        String cardID = data.getPrinter().printCardID(cards, data);
        mappedRes.addAll(data.createMappedRes(data.getCardFromID(cardID).getPrice()));
        int slot = data.handleSlots(cardID);

        Request buyDevReq = new BuyDevRequest(data.getGameID(), data.getPlayerID(), cardID, mappedRes, slot);
        //CHIAMATA A METODO PER INVIARE REQUEST
        data.sendRequest(buyDevReq);

    }

}
