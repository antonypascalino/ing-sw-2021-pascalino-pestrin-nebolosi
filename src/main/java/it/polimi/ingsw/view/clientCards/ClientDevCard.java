package it.polimi.ingsw.view.clientCards;


import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

public class ClientDevCard {
    public ArrayList<Resource> required;
    public ArrayList<Resource> price;
    public int level;
    private String cardID;
    private Colors color;

    public ClientDevCard(ArrayList<Resource> required, ArrayList<Resource> price, int level, String cardID, Colors color) {
        this.required = required;
        this.price = price;
        this.level = level;
        this.cardID = cardID;
        this.color = color;
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
}
