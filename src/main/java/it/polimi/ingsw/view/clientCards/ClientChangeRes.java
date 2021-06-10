package it.polimi.ingsw.view.clientCards;

import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The type Client change res.
 */
public class ClientChangeRes extends ClientLeaderCard {
    private Colors color1;
    private Colors color2;
    private Resource change;

    /**
     * Instantiates a new Client change res.
     *
     * @param victoryPoints the victory points
     * @param color1        the color 1
     * @param color2        the color 2
     * @param change        the change
     * @param leaderID      the leader id
     */
    public ClientChangeRes(int victoryPoints, Colors color1, Colors color2, Resource change, String leaderID) {
        super(leaderID, victoryPoints, change);
        this.color1 = color1;
        this.color2 = color2;
        this.change = change;

    }

    @Override
    public boolean canBePlayed(PlayerData data) {
        return true;
//        ArrayList<ClientDevCard> clientDevCards = new ArrayList<>();
//        for(String s : data.getAllDevID()) {
//            clientDevCards.add(data.getCardFromID(s));
//        }
//
//        boolean firstColor = false;
//        int secondColor = 0;
//
//        for( ClientDevCard card : clientDevCards)
//        {
//            if (card.getColor().equals(color1)) firstColor = true;
//            if (card.getColor().equals(color2)) secondColor++ ;
//        }
//        return (firstColor && secondColor >= 2);
    }

    public String toString() {
        return "Change Resource Leader Card:\nYou can change an EMPTY from market with a " + change + "\nTo play this card you need to have 2 " + color2 + " Developments card and 1 " + color1 + " Development card" ;
    }
}
