package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * The type Discounted player.
 */
public class DiscountedPlayer extends Player
{
    /**
     * The Discount.
     */
    ArrayList<Resource> discount;
    /**
     * The Original.
     */
    Player original;

    /**
     * Instantiates a new Discounted player.
     *
     * @param ori the ori
     * @param dis the dis
     */
    public DiscountedPlayer(Player ori, ArrayList<Resource> dis)
    {
        original=ori;
        //super(original);
        discount=dis;
    }

    /**
     * Gets dev card.
     *
     * @param color the color
     * @param level the level
     */
    @Override
    public void getDevCard(String color, int level)
    {
        DevCard card;
        int slot;

        //Dev'essere cambiato in modo che sia gestito in qualche modo dal game, tipo assegnando al giocatore un riferimento al game in cui si trova
        card = Table.buyDev(color, level);
        if(super.getBoard().hasResources(card.getPrice().remove(discount)))

            card.setOwner(this);
        //richiesta al giocatore in quale slot mettere la card
        super.getBoard().getSlot().purchaseCard(card,slot);
    }
}
