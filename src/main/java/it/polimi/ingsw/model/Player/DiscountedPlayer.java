package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.Cards.DevCard;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.Table.Table;

import java.util.ArrayList;

/**
 * The type Discounted player (it extends {@link Player}).
 * It's the player with a discount leader card.
 */
public class DiscountedPlayer extends Player
{
    /**
     * The Discount.
     */
    ArrayList<Resource> discount;
    /**
     * reference to the original {@link Player} abstract class.
     */
    Player original;

    /**
     * Instantiates a new Discounted player.
     *
     * @param ori original player (without "mods")
     * @param dis array list of resources indicating the discount
     */
    public DiscountedPlayer(Player ori, ArrayList<Resource> dis)
    {
        original = ori;
        discount = new ArrayList<Resource>();
        discount.addAll(dis);
        //If the original already had a discount it counts its discounts as well
        if(original instanceof DiscountedPlayer)
        {
            discount.addAll(((DiscountedPlayer) original).getDiscount());
        }
    }

    /**
     * Gets dev card.
     *
     * @param color dev card color
     * @param level dev card level
     *
     */
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

    /**
     * Gets discount
     *
     * @return the discount
     */
    public ArrayList<Resource> getDiscount()
    {
        return discount;
    }
}
