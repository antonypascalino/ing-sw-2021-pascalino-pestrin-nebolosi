package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.Table.Resource;

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
    public DiscountedPlayer(Player ori, Resource dis)
    {
        original = ori;
        discount = new ArrayList<Resource>();
        discount.add(dis);
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
//        DevCard card;
//        int slot=2;
//        //Setted to 2 for debug purposes
//
//        card = original.getGame().getTable().buyDev(color, level);
//        ArrayList<Resource> tmp ;
//        tmp = card.getPrice();
//
//        //Remove the discount
//        for (Resource r: discount)
//            tmp.remove(r);
//        if(original.getBoard().hasResources(tmp))
//        {
//            card.setOwner(this);
//            original.getBoard().getSlot().purchaseCard(card,slot);
//        }

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

    @Override
    public boolean canBuy(DevCard devCard, ArrayList<Resource> allPlayerRes) {
        ArrayList<Resource> discountedRes = new ArrayList<Resource>();
        discountedRes.addAll(allPlayerRes);
        discountedRes.addAll(discount);
        if (discountedRes.containsAll(devCard.getPrice())) {
            return true;
        }
        //else lancia eccezione: non hai risorse per comprare questa carta.
        return false;
    }
}
