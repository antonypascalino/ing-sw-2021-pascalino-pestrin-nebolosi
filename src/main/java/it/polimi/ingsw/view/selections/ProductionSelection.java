package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.Request.ProduceRequest;
import it.polimi.ingsw.Request.Production;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
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
            String cardID = data.getPrinter().printCardID(cards);

            if(cardID.contains("BASIC")){

                MappedResource selected1 = data.getPrinter().printMappedRes(allRes);
                mappedRes.add(selected1);
                allRes.removeAll(mappedRes);

                MappedResource selected2 = data.getPrinter().printMappedRes(allRes);
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
                MappedResource selected = data.getPrinter().printMappedRes(choices);
                mappedRes.add(selected);
            }

            allRes.removeAll(mappedRes);
            cards.remove(cardID);
            Production p = new Production(mappedRes,cardID);
            mappedProduction.add(p);
            if(cards.size() == 0){
                break;
            }
        }while(data.getPrinter().askQuestion());

        Request produceReq = new ProduceRequest(data.getGameID(), data.getPlayerID(), mappedProduction );
        sendToConnection(produceReq);
    }

    @Override
    public void sendToConnection(Request request) {


    }


}
