package it.polimi.ingsw.view.clientCards;

import java.util.ArrayList;

/**
 * The type containg all the {@link ClientDevCard} and all the {@link ClientLeaderCard}.
 */
public class AllGameCards {
    private ArrayList<ClientDevCard> allDevCards;
    private ArrayList<ClientLeaderCard> allLeaderCards;

    /**
     * Instantiates a new {@link AllGameCards} setting all the cards of the game.
     */
    public AllGameCards() {
        this.allDevCards = ClientDefaultCreator.produceClientDevCard();
        this.allLeaderCards = ClientDefaultCreator.produceClientLeaderCard();
    }

    public ArrayList<ClientDevCard> getAllDevCards() {
        return allDevCards;
    }

    public ArrayList<ClientLeaderCard> getAllLeaderCards() {
        return allLeaderCards;
    }
}
