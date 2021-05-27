package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.Production;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductionSelection extends Selection {



    @Override
    public void handleSelection(PlayerData data) {
        ArrayList<String> cards = new ArrayList<String>();
        ArrayList<Production> mappedProduction = new ArrayList<Production>();

        do{
            cards.addAll(data.slotCardsFilter());
            String cardID = printer.printCardID(cards);
            ArrayList<MappedResource> mappedRes = new ArrayList<MappedResource>();
            mappedRes.addAll(data.createMappedRes(data.getCardFromID(cardID).getRequired()));
            data.removeMappedResource(mappedRes);
            cards.remove(cardID);
            Production p = new Production();
            mappedProduction.add(p);
        }while(cards.size() > 0);
    }

    @Override
    public void sendToConnection() {

    }


}
