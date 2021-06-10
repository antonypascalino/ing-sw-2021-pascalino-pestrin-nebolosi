package it.polimi.ingsw.view.clientCards;

import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The type Client extra prod.
 */
public class ClientExtraProd extends ClientLeaderCard {
    private Colors color;
    private Resource prodRequired;


    /**
     * Instantiates a new Client extra prod.
     *
     * @param victoryPoints the victory points
     * @param color         the color
     * @param prodRequired  the prod required
     * @param leaderID      the leader id
     */
    public ClientExtraProd(int victoryPoints, Colors color, Resource prodRequired, String leaderID) {
        super(leaderID, victoryPoints, prodRequired);
        this.prodRequired = prodRequired;
        this.color = color;
    }

    @Override
    public boolean canBePlayed(PlayerData data) {
        return true;
//        ArrayList<ClientDevCard> clientDevCards = new ArrayList<>();
//        for(String s : data.getAllDevID()) {
//            clientDevCards.add(data.getCardFromID(s));
//        }
//        for( ClientDevCard card : clientDevCards)
//        {
//            if (card.getLevel() == 2 && card.getColor().equals(color)) return true;
//        }
//        return false;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Colors getColor() {
        return color;
    }

    /**
     * Gets prod required.
     *
     * @return the prod required
     */
    public Resource getProdRequired() {
        return prodRequired;
    }

    public String toString() {
        return "Extra Production Leader Card:\nYou will have an Extra Production power which you can use to produce a CHOICE resource and a FAITH point, paying a " + prodRequired + "\nTo play this card you need to have a " + color + " Development card of level 2";
    }
}
