package it.polimi.ingsw.model;

import java.util.ArrayList;

public class DiscountedPlayer extends Player
{
    ArrayList<Resource> discount;
    Player original;

    public DiscountedPlayer(Player ori, ArrayList<Resource> dis)
    {
        original=ori;
        //super(original);
        discount=dis;
    }

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
