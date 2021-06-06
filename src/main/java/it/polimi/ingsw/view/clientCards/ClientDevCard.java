package it.polimi.ingsw.view.clientCards;


import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

public class ClientDevCard {
    private ArrayList<Resource> required;
    private ArrayList<Resource> price;
    private int level;
    private String cardID;
    private Colors color;
    private int victoryPoints;
    ArrayList<Resource> produces;


    public ClientDevCard(String cardID, Colors color, int level, int victoryPoints, ArrayList<Resource> required, ArrayList<Resource> produces, ArrayList<Resource> price ) {
        this.required = required;
        this.price = price;
        this.level = level;
        this.cardID = cardID;
        this.color = color;
        this.victoryPoints = victoryPoints;
        this.produces = produces;
    }

    public ArrayList<Resource> getRequired(){
        return required;
    }

    public ArrayList<Resource> getPrice(){
        return price;
    }

    public int getLevel(){
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
