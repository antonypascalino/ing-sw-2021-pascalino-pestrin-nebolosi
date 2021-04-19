package it.polimi.ingsw.model;

/**
 * The type Discount.
 */
public class Discount implements LeaderCard {

    private String color1;
    private String color2;
    private Resource discount;
    private int victoryPoints;
    private boolean isEnable;
    private Player player;

    /**
     * Instantiates a new Discount.
     */
    public Discount() {
        //legge da Json discount, victoryPoints, requires
    }

    public void assignTo(Player player) {
        this.player = player;
        player.addLeaderCard(this);
    }

    /**
     * Is enable boolean.
     *
     * @return the boolean
     */
    public boolean isEnable() {
        return isEnable;
    }

    public void playCard() {
        if (canBePlayed()) {
            isEnable = true;
            player.addVictoryPoints(victoryPoints);
        }
    }

    public boolean canBePlayed() {
        boolean secondColor = false;
        boolean firstColor = false;

        for (DevCard card : player.getBoard().getSlot().getAllCards()) {
            if (card.getColor().equals(color1)) firstColor = true;
            if (card.getColor().equals(color2)) secondColor = true;
        }
        return (firstColor && secondColor);
    }
}
