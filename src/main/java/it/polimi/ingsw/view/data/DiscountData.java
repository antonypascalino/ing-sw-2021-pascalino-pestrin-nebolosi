package it.polimi.ingsw.view.data;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.clientCards.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * It's the player with the {@link ClientDiscount} {@link ClientLeaderCard} (it extends {@link PlayerData}).
 */
public class DiscountData extends PlayerData{
    private ArrayList<Resource> discount;


    /**
     * Instantiates a new {@link DiscountData}.
     *
     * @param discount     the discount
     * @param originalData the original data
     */
    public DiscountData(ArrayList<Resource> discount, PlayerData originalData) {
        this.discount = new ArrayList<>();
        if (originalData instanceof DiscountData) {
            this.discount.addAll(((DiscountData) originalData).getDiscount());
            ((DiscountData) originalData).getDiscount().clear();
        }
        this.originalData = originalData;
        this.discount.addAll(discount);
    }


    /**
     * Needs to be overrided so it count the discount
     * @return
     */
    @Override
    public ClientDevCard getCardFromID(String cardID) {
        ClientDevCard tmp = originalData.getCardFromID(cardID);
        ArrayList<Resource> newPrice = (ArrayList<Resource>) tmp.getPrice().clone();
        for(Resource res : discount)
            if(newPrice.contains(res))
                newPrice.remove(res);
        ClientDevCard discounted =  new ClientDevCard(tmp.getCardID(),tmp.getColor(),tmp.getLevel(), tmp.getLevel(), tmp.getRequired(), tmp.getProduces(), newPrice);
        return discounted;
    }

    @Override
    public ArrayList<String> tableCardsFilter(ArrayList<MappedResource> mapped){
        ArrayList<String> available = new ArrayList<>();
        ArrayList<Integer> cardLevel = new ArrayList<>(); // the levels of the cards which the player can buy
        cardLevel.add(1); //added because if there is no card I can't add its level+1
        ArrayList<Resource> allRes = new ArrayList<Resource>();
        for(MappedResource m : mapped){
            allRes.add(m.getResource());
        }
        for (String devCardID : getFrontCardsID()) {
            cardLevel.add(getCardFromID(devCardID).getLevel() + 1);
        }
        if (cardLevel.size() == 4) {
            cardLevel.remove(0);
        }
        //For every resource i have to check if the occurences match
        for (String card : getFrontTableCardsID()) {
            boolean canBeBought = true;
            if (!cardLevel.contains(getCardFromID(card).getLevel())) {
                canBeBought = false;
                continue;
            }
            for (Resource res : getCardFromID(card).getPrice()) {
                //Check if they have the same number of res for every tipe, if it doesn't have the resource remove them
                if (Collections.frequency(allRes, res) < Collections.frequency(getCardFromID(card).getPrice(), res)) {
                    canBeBought = false;
                    break;
                }
            }
            if (canBeBought) available.add(card);
        }
        return available;
    }

    /**
     * Gets discount.
     *
     * @return the discount
     */
    public ArrayList<Resource> getDiscount() {
        return discount;
    }
}
