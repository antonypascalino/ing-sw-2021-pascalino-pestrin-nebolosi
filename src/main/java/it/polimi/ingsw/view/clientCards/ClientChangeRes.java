package it.polimi.ingsw.view.clientCards;

import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The {@link ClientLeaderCard} that gives the {@link PlayerData} the possibility to change an white marble with an other {@link Resource}.
 */
public class ClientChangeRes extends ClientLeaderCard {
    private Colors color1;
    private Colors color2;
    private Resource change;

    /**
     * Instantiates a new {@link ClientChangeRes} setting the requirements to be be played and the changeable {@link Resource}.
     *
     * @param victoryPoints the victory points
     * @param color1        the color of a {@link ClientDevCard} required for being played.
     * @param color2        the color of a 2 {@link ClientDevCard}s required for being played.
     * @param change        the {@link Resource} in which the new {@link PlayerData} can turn the white marbles.
     * @param leaderID      the leader card's ID.
     */
    public ClientChangeRes(int victoryPoints, Colors color1, Colors color2, Resource change, String leaderID) {
        super(leaderID, victoryPoints, change);
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

    public String toString() {
        return "Change Resource Leader Card:\nYou can change an EMPTY from market with a " + change + "\nTo play this card you need to have 2 " + color2 + " Developments card and 1 " + color1 + " Development card" ;
    }
}
