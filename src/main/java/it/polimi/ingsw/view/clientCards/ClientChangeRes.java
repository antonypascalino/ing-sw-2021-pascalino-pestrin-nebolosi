package it.polimi.ingsw.view.clientCards;

import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class ClientChangeRes extends ClientLeaderCard {
    private Colors color1;
    private Colors color2;
    private Resource change;

    public ClientChangeRes(String leaderID, Colors color1, Colors color2, Resource change) {
        super(leaderID, "Change Resource");
        this.color1 = color1;
        this.color2 = color2;
        this.change = change;
    }

    @Override
    public boolean canBePlayed(PlayerData data) {
        ArrayList<ClientDevCard> clientDevCards = new ArrayList<>();
        for(String s : data.getAllDevID()) {
            clientDevCards.add(data.getCardFromID(s));
        }

        boolean firstColor = false;
        int secondColor = 0;

        for( ClientDevCard card : clientDevCards)
        {
            if (card.getColor().equals(color1)) firstColor = true;
            if (card.getColor().equals(color2)) secondColor++ ;
        }
        return (firstColor && secondColor >= 2);
    }
}
