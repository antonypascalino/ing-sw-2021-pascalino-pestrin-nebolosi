package it.polimi.ingsw.view.clientCards;

import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The type Client discount.
 */
public class ClientDiscount extends ClientLeaderCard {
    private Colors color1;
    private Colors color2;
    private Resource discount;

    /**
     * Instantiates a new Client discount.
     *
     * @param color1        the color 1
     * @param color2        the color 2
     * @param discount      the discount
     * @param victoryPoints the victory points
     * @param leaderID      the leader id
     */
    public ClientDiscount(Colors color1, Colors color2, Resource discount, int victoryPoints, String leaderID) {
        super(leaderID, "Discount", victoryPoints);
        this.color1 = color1;
        this.color2 = color2;
        this.discount = discount;
    }

    @Override
    public boolean canBePlayed(PlayerData data) {
        ArrayList<ClientDevCard> clientDevCards = new ArrayList<>();
        for(String s : data.getAllDevID()) {
            clientDevCards.add(data.getCardFromID(s));
        }

        boolean firstColor = false;
        boolean secondColor = false;

        for( ClientDevCard card : clientDevCards)
        {
            if (card.getColor().equals(color1)) firstColor = true;
            if (card.getColor().equals(color2)) secondColor = true ;
        }
        return (firstColor && secondColor);
    }

    public String toString() {
        return "Discount Leader Card:\nWhen you buy a Developement Card from table you will pay one " + discount + " less" + "\nTo play this card you need to have 1 " + color2 + " Developments card and 1 " + color1 + " Development card" ;
    }
}
