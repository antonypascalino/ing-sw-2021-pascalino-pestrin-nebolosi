package it.polimi.ingsw.model.card;

import it.polimi.ingsw.model.Player.DiscountedPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Table.Table;

/**
 * The {@link LeaderCard} that allows the {@link Player} to pay a {@link Resource}
 * less when buys {@link DevCard} from the {@link Table}
 */
public class Discount implements LeaderCard {

    private final String color1;
    private final String color2;
    private final Resource discount;
    private final int victoryPoints;
    private boolean isEnable;
    private Player player;
    private final String className;
    private final String cardID;

    /**
     * Instantiates a new Discount {@link LeaderCard}, and sets enable to false.
     *
     * @param color1  the first color of a {@link DevCard} needed to play the {@link LeaderCard}.
     * @param color2  the second color of a {@link DevCard} needed to play the {@link LeaderCard}.
     * @param dis     the {@link Resource} representing the discount given to the {@link Player}.
     * @param vp     the victory points the {@link LeaderCard} is gonna give to the {@link Player} when enabled.
     * @param cardID the {@link LeaderCard}'s ID.
     */
    public Discount(String color1, String color2, Resource dis, int vp, String cardID)
    {
        this.cardID = cardID;
        className = this.getClass().getName();
        this.color1 = color1;
        this.color2 = color2;
        discount = dis;
        victoryPoints = vp;
        isEnable = false;
    }

    @Override
    public void setPlayer(Player tmp) {
        this.player = tmp;
    }

    @Override
    public void assignTo(Player player) {
        this.player = player;
        player.addLeaderCard(this);
    }

    @Override
    public boolean isEnable() {
        return isEnable;
    }

    @Override
    public boolean equals(LeaderCard compare) {
        return this.cardID.equals(compare.getID());
    }

    @Override
    public String getID() {
        return cardID;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void playCard()
    {
        if (canBePlayed())
        {
            player.addVictoryPoints(victoryPoints);
            isEnable = true;
            Player tmp = new DiscountedPlayer(player, discount);
            //Add the new powered player in substitition to the actual one if the game references
            player.getGame().changePlayer(player, tmp);
            for (LeaderCard card : player.getLeaderCards())
            {
                //Do not change the reference on this card
                if(!card.getID().equals(this.getID()))
                    card.setPlayer(tmp);
            }
            this.player = tmp;
        }
    }

    @Override
    public boolean canBePlayed()
    {
        boolean secondColor = false;
        boolean firstColor = false;
        if (isEnable) return false; //The card can't be played twice
        for (DevCard card : player.getBoard().getSlot().getAllCards()) {
            if (card.getColor().equals(color1.toUpperCase())) firstColor = true;
            if (card.getColor().equals(color2.toUpperCase())) secondColor = true;
        }
        return (firstColor && secondColor);
    }

    @Override
    public Resource getPowerResource() {
        return discount;
    }
}
