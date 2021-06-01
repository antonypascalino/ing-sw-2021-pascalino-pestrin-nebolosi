package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.Request.Production;
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
        String cardID = printer.printCardID(cards);
        mappedRes.addAll(data.createMappedRes(data.getCardFromID(cardID).getPrice()));
        int slot = data.handleSlots(cardID);
        //qua chiama costruttore di buy dev request passandogli le info
        //chiami un metodo che passa le request
    }

}
