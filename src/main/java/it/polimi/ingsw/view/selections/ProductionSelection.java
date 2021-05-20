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
        Scanner inputs = new Scanner(System.in);
        ArrayList<String> cards = new ArrayList<String>();
        ArrayList<Production> mappedProduction = new ArrayList<Production>();
        String selection = "";

        do{
            cards.addAll(data.slotCardsFilter());
            ArrayList<MappedResource> mappedRes = new ArrayList<MappedResource>();
            for (int i = 0; i < cards.size(); i++) {
                System.out.println("[" + (i + 1) + "]" + "" + cards.get(i));
            }
            selection = inputs.nextLine();
            int index = Integer.parseInt(selection);
            mappedRes.addAll(data.createMappedRes(data.getCardFromID(cards.get(index-1)).getRequired()));
            data.removeMappedResource(mappedRes);
            cards.remove(cards.get(index-1));
            Production p = new Production();
            mappedProduction.add(p);
        }while(cards.size() > 0);


    }




    @Override
    public void sendToConnection() {

    }


}
