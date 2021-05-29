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
        ArrayList<MappedResource> mappedRes = new ArrayList<MappedResource>();
        allRes.addAll(data.allResources());

        do{
            cards.addAll(data.slotCardsFilter(allRes));
            String cardID = printer.printCardID(cards);

            if(cardID.contains("BASIC")){

                MappedResource selected1 = printer.printMappedRes(allRes);
                mappedRes.add(selected1);
                allRes.removeAll(mappedRes);

                MappedResource selected2 = printer.printMappedRes(allRes);
                mappedRes.add(selected2);
                allRes.removeAll(mappedRes);
            }

            else{
                mappedRes.addAll(data.createMappedRes(data.getCardFromID(cardID).getRequired()));
            }

            if(cardID.contains("PROD") || cardID.contains("BASIC")){
                ArrayList<MappedResource> choices = new ArrayList<MappedResource>();
                MappedResource mapped1 = new MappedResource(Resource.GOLD, "choice");
                MappedResource mapped2 = new MappedResource(Resource.SERVANT, "choice");
                MappedResource mapped3 = new MappedResource(Resource.SHIELD, "choice");
                MappedResource mapped4 = new MappedResource(Resource.STONE, "choice");
                choices.add(mapped1);
                choices.add(mapped2);
                choices.add(mapped3);
                choices.add(mapped4);
                MappedResource selected = printer.printMappedRes(choices);
                mappedRes.add(selected);
            }

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
