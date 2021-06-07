package it.polimi.ingsw.view.clientCards;


import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

/**
 * The type Client dev card.
 */
public class ClientDevCard {
    private ArrayList<Resource> required;
    private ArrayList<Resource> price;
    private int level;
    private String cardID;
    private Colors color;
    private int victoryPoints;
    /**
     * The Produces.
     */
    ArrayList<Resource> produces;


    /**
     * Instantiates a new Client dev card.
     *
     * @param cardID        the card id
     * @param color         the color
     * @param level         the level
     * @param victoryPoints the victory points
     * @param required      the required
     * @param produces      the produces
     * @param price         the price
     */
    public ClientDevCard(String cardID, Colors color, int level, int victoryPoints, ArrayList<Resource> required, ArrayList<Resource> produces, ArrayList<Resource> price ) {
        this.required = required;
        this.price = price;
        this.level = level;
        this.cardID = cardID;
        this.color = color;
        this.victoryPoints = victoryPoints;
        this.produces = produces;
    }

    /**
     * Get required array list.
     *
     * @return the array list
     */
    public ArrayList<Resource> getRequired(){
        return required;
    }

    /**
     * Get price array list.
     *
     * @return the array list
     */
    public ArrayList<Resource> getPrice(){
        return price;
    }

    /**
     * Get level int.
     *
     * @return the int
     */
    public int getLevel(){
        return level;
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
     * Gets card id.
     *
     * @return the card id
     */
    public String getCardID() {
        return cardID;
    }

    public String toString() {
        return ("Color: " + color + "\n" + "Level: " + level + "\n" + "Price: " + price + "\n" + "Requires: " + required + "\n" + "Produces: " + produces + "\n" + "Victory Points: " + victoryPoints + "\n");
    }
}
