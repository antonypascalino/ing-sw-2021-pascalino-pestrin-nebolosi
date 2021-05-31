package it.polimi.ingsw.view.clientCards;

import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class ClientDiscount extends ClientLeaderCard {
    private Colors color1;
    private Colors color2;
    private Resource discount;

    public ClientDiscount(String leaderID, Colors color1, Colors color2, Resource discount) {
        super(leaderID, "Discount");
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
}
