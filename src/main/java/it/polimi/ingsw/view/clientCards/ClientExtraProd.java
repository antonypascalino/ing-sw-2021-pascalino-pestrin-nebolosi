package it.polimi.ingsw.view.clientCards;

import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class ClientExtraProd extends ClientLeaderCard {
    private Colors color;
    private Resource prodRequired;


    public ClientExtraProd(int victoryPoints, Colors color, Resource prodRequired, String leaderID) {
        super(leaderID, "Extra Production", victoryPoints);
        this.prodRequired = prodRequired;
        this.color = color;
    }

    @Override
    public boolean canBePlayed(PlayerData data) {
        ArrayList<ClientDevCard> clientDevCards = new ArrayList<>();
        for(String s : data.getAllDevID()) {
            clientDevCards.add(data.getCardFromID(s));
        }
        for( ClientDevCard card : clientDevCards)
        {
            if (card.getLevel() == 2 && card.getColor().equals(color)) return true;
        }
        return false;
    }

    public Colors getColor() {
        return color;
    }

    public Resource getProdRequired() {
        return prodRequired;
    }
}
