package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.Request.ProduceRequest;
import it.polimi.ingsw.Request.Production;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;


/**
 * The {@link Selection} that guides the player when he chose to produce.
 */
public class ProductionSelection extends Selection {


    @Override
    public void handleSelection(PlayerData data) {
        ArrayList<String> cards = new ArrayList<>();
        ArrayList<String> usedCards = new ArrayList<>();
        ArrayList<Production> mappedProduction = new ArrayList<>();
        ArrayList<MappedResource> allRes = new ArrayList<>();
        ArrayList<MappedResource> mappedRes;
        //Add the basic prod since the players always has it
        allRes.addAll(data.allResources());

        do {
            mappedRes = new ArrayList<>();
            cards.clear();
            cards.addAll(data.slotCardsFilter(allRes));
            cards.removeAll(usedCards);
            if (cards.size() == 0) {
                data.getPrinter().printMessage("You have no more card which can produce!");
                break;
            }
            String cardID = data.getPrinter().printDevCardID(cards, data);

            if (cardID.contains("BASIC")) {

                //If the non empty resources are more than two
                if (allRes.stream().map(MappedResource::getResource).filter(x -> !x.equals(Resource.EMPTY)).count() <= 1) {
                    data.getPrinter().printMessage("You don't have enough resource for using the basic production");
                    break;
                }

                ArrayList<MappedResource> emptyToRemove = new ArrayList<>();
                for (MappedResource mappedResource : allRes) {
                    if (mappedResource.getResource().equals(Resource.EMPTY)) {
                        emptyToRemove.add(mappedResource);
                    }
                }
                allRes.removeAll(emptyToRemove);
                MappedResource selected1 = data.getPrinter().printMappedRes(allRes);
                mappedRes.add(selected1);
                allRes.removeAll(mappedRes);

                MappedResource selected2 = data.getPrinter().printMappedRes(allRes);
                mappedRes.add(selected2);
            } else if (cardID.contains("dev")) {
                mappedRes.addAll(data.createMappedRes(data.getCardFromID(cardID).getRequired()));
            }
            //se la carta è una leader extra prod (avrà una sola res)
            else {
                ArrayList<Resource> tmp = new ArrayList<>();
                tmp.add(data.getLeaderFromID(cardID).getPowerResource());
                mappedRes.addAll(data.createMappedRes(tmp));
            }

            if (cardID.contains("PROD") || cardID.contains("BASIC")) {
                ArrayList<MappedResource> choices = new ArrayList<>();
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
            //For every resource in the selected one check if it's contained in the all res and removes it
            if (!cardID.equals("BASIC")) {
                for (MappedResource res : mappedRes) {
                    boolean removed = false;
                    for (MappedResource playerRes : allRes) {
                        if (removed) break;
                        if (res.getResource().equals(playerRes.getResource())) {
                            allRes.set(allRes.indexOf(playerRes), new MappedResource(Resource.EMPTY, "daddy"));
                            removed = true;
                        }
                    }
                }
            }
            usedCards.add(cardID);
            Production p = new Production(mappedRes, cardID);
            mappedProduction.add(p);
        } while (data.getPrinter().askQuestion());

        //If the user choose what to send
        if (mappedProduction.size() != 0) {
            Request produceReq = new ProduceRequest(data.getGameID(), data.getPlayerID(), mappedProduction);
            data.sendRequest(produceReq);
        }

        //if there's nothing to send recreate a menu
        else
            data.getMenu().menuMaker();

    }
}
