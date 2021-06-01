package it.polimi.ingsw.view.clientCards;

import java.util.ArrayList;

public class AllGameCards {
    private ArrayList<ClientDevCard> allDevCards;
    private ArrayList<ClientLeaderCard> allLeaderCards;

    public AllGameCards(ArrayList<ClientDevCard> allDevCards, ArrayList<ClientLeaderCard> allLeaderCards) {
        this.allDevCards = allDevCards;
        this.allLeaderCards = allLeaderCards;
    }

    public ArrayList<ClientDevCard> getAllDevCards() {
        return allDevCards;
    }

    public ArrayList<ClientLeaderCard> getAllLeaderCards() {
        return allLeaderCards;
    }
}
