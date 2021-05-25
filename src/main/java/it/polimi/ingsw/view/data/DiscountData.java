package it.polimi.ingsw.view.data;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.view.ClientDevCard;
import it.polimi.ingsw.view.Printer;

import java.util.ArrayList;

public class DiscountData {

    private ArrayList<TurnState> turnStates;
    private TurnState turnState;
    private ArrayList<Resource[]> wareHouse;
    private ArrayList<Resource> strongBox;
    private ArrayList<String> tableCardsID; //just the front table cards
    private int faithPoints;
    private int victoryPoints;
    private ArrayList<String> cardsID;  //3 front cards + basic + extraProd
    private ArrayList<String> leadersID;
    private ArrayList<String> leadersPlayedID;
    private Resource[][] market;
    private Printer printer;
    private ArrayList<Resource> discount;

    public DiscountData(ArrayList<Resource> discount, ArrayList<String> cardID, ArrayList<TurnState> turnStates, TurnState turnState, ArrayList<Resource[]> wareHouse, ArrayList<Resource> strongBox, int faithPoints, int victoryPoints, ArrayList<String> cardsID, ArrayList<String> leadersID, Resource[][] market, ArrayList<String> tableCardsID) {
        this.turnStates = turnStates;
        this.turnState = turnState;
        this.wareHouse = wareHouse;
        this.strongBox = strongBox;
        this.faithPoints = faithPoints;
        this.victoryPoints = victoryPoints;
        this.cardsID = cardsID;
        this.leadersID = leadersID;
        this.market = market;
        this.tableCardsID = tableCardsID;
        this.discount = discount;
    }

    public ArrayList<String> tableCardsFilter(){
        //qui è da vedere un attimo se fa casini ma non dovrebbe
        ArrayList<String> cloned = new ArrayList<String>();
        cloned.addAll(tableCardsID);
        ArrayList<Resource> allRes = new ArrayList<Resource>();
        ArrayList<MappedResource> mapped = new ArrayList<MappedResource>();
        mapped.addAll(allResources());

        for(MappedResource m : mapped){
            allRes.add(m.getResource());
        }
        for(String c : cloned){
            for(Resource res : discount){
                ClientDevCard card = getCardFromID(c);
                card.getPrice().remove(res);
            }
        }
        ClientDevCard playerCard = new ClientDevCard();
        cloned.removeIf(card -> !allRes.containsAll(playerCard.getRequired()));
        return cloned;
    }

    public ArrayList<MappedResource> allResources(){
        ArrayList<MappedResource> tmp = new ArrayList<MappedResource>();
        Resource[] level = new Resource[3];
        for(Resource[] l : wareHouse) {
            for (Resource w : level){
                MappedResource mappedW = new MappedResource(w, "warehouse");
                tmp.add(mappedW);
            }
        }
        for(Resource s : strongBox){
            MappedResource mappedS = new MappedResource(s, "strongbox");
            tmp.add(mappedS);
        }
        return tmp;
    }

    public ClientDevCard getCardFromID(String cardID){
        ClientDevCard card = new ClientDevCard();
        return card;
    }
}
