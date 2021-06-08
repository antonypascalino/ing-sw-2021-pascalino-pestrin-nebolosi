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

/**
 * The type Production selection.
 */
public class ProductionSelection extends Selection {



    @Override
    public void handleSelection(PlayerData data) {
        ArrayList<String> cards = new ArrayList<String>();
        ArrayList<String> usedCards = new ArrayList<String>();
        ArrayList<Production> mappedProduction = new ArrayList<Production>();
        ArrayList<MappedResource> allRes = new ArrayList<MappedResource>();
        ArrayList<MappedResource> mappedRes = new ArrayList<MappedResource>();
        //Add the bsdic prod since the players always has it
        allRes.addAll(data.allResources());

        do{
            cards.clear();
            cards.addAll(data.slotCardsFilter(allRes));
            cards.removeAll(usedCards);
            if(cards.size() == 0){
                data.getPrinter().printMessage("You have no more card which can produce!");
                break;
            }
            String cardID = data.getPrinter().printDevCardID(cards, data);

            if(cardID.contains("BASIC")){

                //If the non empty resources are more than two
                if(data.allResources().stream().map(x -> x.getResource()).filter(x -> !x.equals(Resource.EMPTY)).count() <=1)
                {
                    data.getPrinter().printMessage("You don't have enough resource for using the basic production");
                    break;
                }

                MappedResource selected1 = data.getPrinter().printMappedRes(allRes);
                mappedRes.add(selected1);
                allRes.removeAll(mappedRes);

                MappedResource selected2 = data.getPrinter().printMappedRes(allRes);
                mappedRes.add(selected2);
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
            //For every resource in the selected one check if it's contiained in the all res and removes it
            //AS IT IS NOW IT'S NOT WORKING: CAN'T REMOVE INSIDE A FOR EACH
            for(MappedResource res : mappedRes)
                for (MappedResource playerRes : allRes)
                    if(res.getResource().equals(playerRes.getResource()))
                    {
                        allRes.remove(playerRes);
                        break;
                    }
            usedCards.add(cardID);
            Production p = new Production(mappedRes,cardID);
            mappedProduction.add(p);
        }while(data.getPrinter().askQuestion());

        //If the userce chose what to send
        if(mappedProduction.size() != 0)
        {
            Request produceReq = new ProduceRequest(data.getGameID(), data.getPlayerID(), mappedProduction );
            data.sendRequest(produceReq);
        }

        //if there's nothing to send recreate a menu
        else
            data.getMenu().menuMaker();

    }
}
