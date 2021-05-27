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
        ArrayList<MappedResource> allRes = new ArrayList<MappedResource>();
        allRes.addAll(data.allResources());

        do{
            cards.addAll(data.slotCardsFilter(allRes));
            String cardID = printer.printCardID(cards);
            ArrayList<MappedResource> mappedRes = new ArrayList<MappedResource>();
            mappedRes.addAll(data.createMappedRes(data.getCardFromID(cardID).getRequired()));
            allRes.removeAll(mappedRes);
            cards.remove(cardID);
            Production p = new Production();
            mappedProduction.add(p);
            if(cards.size() == 0){
                break;
            }
        }while(printer.askQuestion());
    }

    @Override
    public void sendToConnection() {

    }


}
