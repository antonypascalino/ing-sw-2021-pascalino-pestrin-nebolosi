package it.polimi.ingsw.view.clientCards;

import java.util.ArrayList;

/**
 * The type All game cards.
 */
public class AllGameCards {
    private ArrayList<ClientDevCard> allDevCards;
    private ArrayList<ClientLeaderCard> allLeaderCards;

    /**
     * Instantiates a new All game cards.
     *
     * @param allDevCards    the all dev cards
     * @param allLeaderCards the all leader cards
     */
    public AllGameCards(ArrayList<ClientDevCard> allDevCards, ArrayList<ClientLeaderCard> allLeaderCards) {
        this.allDevCards = allDevCards;
        this.allLeaderCards = allLeaderCards;
    }

    /**
     * Gets all dev cards.
     *
     * @return the all dev cards
     */
    public ArrayList<ClientDevCard> getAllDevCards() {
        return allDevCards;
    }

    /**
     * Gets all leader cards.
     *
     * @return the all leader cards
     */
    public ArrayList<ClientLeaderCard> getAllLeaderCards() {
        return allLeaderCards;
    }
}
