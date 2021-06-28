package it.polimi.ingsw.view.clientCards;


import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Table.Resource;


import java.util.ArrayList;

/**
 * A Client development card is a light version of the Development Card on the server.
 */
public class ClientDevCard {
    private ArrayList<Resource> required;
    private ArrayList<Resource> price;
    private int level;
    private String cardID;
    private Colors color;
    private int victoryPoints;
    private ArrayList<Resource> produces;


    /**
     * Instantiates a new {@link ClientDevCard} with all its features.
     *
     * @param cardID        the {@link ClientDevCard}'s ID.
     * @param color         the {@link ClientDevCard}'s color.
     * @param level         the {@link ClientDevCard}'s level.
     * @param victoryPoints the {@link ClientDevCard}'s victory points gave to a {@link it.polimi.ingsw.view.data.PlayerData} who will buy this card.
     * @param required      the required {@link Resource} needed for producing.
     * @param produces      the {@link Resource}s produced by this {@link ClientDevCard}.
     * @param price         the price for buying this {@link Resource}.
     */
    public ClientDevCard(String cardID, Colors color, int level, int victoryPoints, ArrayList<Resource> required, ArrayList<Resource> produces, ArrayList<Resource> price) {
        this.required = required;
        this.price = price;
        this.level = level;
        this.cardID = cardID;
        this.color = color;
        this.victoryPoints = victoryPoints;
        this.produces = produces;
    }

    public ArrayList<Resource> getRequired() {
        return required;
    }

    public ArrayList<Resource> getProduces() {
        return produces;
    }

    public ArrayList<Resource> getPrice() {
        return price;
    }

    public int getLevel() {
        return level;
    }

    public Colors getColor() {
        return color;
    }

    public String getCardID() {
        return cardID;
    }

    public String toString() {
        return ("Color: " + color + "\n" + "Level: " + level + "\n" + "Price: " + price + "\n" + "Requires: " + required + "\n" + "Produces: " + produces + "\n" + "Victory Points: " + victoryPoints + "\n");
    }
}
