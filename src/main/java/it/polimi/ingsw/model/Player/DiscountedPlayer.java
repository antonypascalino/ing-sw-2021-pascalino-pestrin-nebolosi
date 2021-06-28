package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.card.LeaderCard;
import it.polimi.ingsw.model.card.Discount;

import java.util.ArrayList;

/**
 * The type Discounted player (it extends {@link Player}).
 * It's the player with a {@link Discount} {@link LeaderCard}.
 */
public class DiscountedPlayer extends Player {
    ArrayList<Resource> discount;

    /**
     * Instantiates a new Discounted player.
     *
     * @param ori original player.
     * @param dis array list of resources indicating the discount.
     */
    public DiscountedPlayer(Player ori, Resource dis)
    {
        original = ori;
        discount = new ArrayList<>();
        discount.add(dis);
        //If the original already had a discount it counts its discounts as well
        if(original instanceof DiscountedPlayer)
        {
            discount.addAll(((DiscountedPlayer) original).getDiscount());
        }
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
        ArrayList<Resource> discountedRes = new ArrayList<>();
        discountedRes.addAll(allPlayerRes);
        discountedRes.addAll(discount);
        return discountedRes.containsAll(devCard.getPrice());
    }
}
