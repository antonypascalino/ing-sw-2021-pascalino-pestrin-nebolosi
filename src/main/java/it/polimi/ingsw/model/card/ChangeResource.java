package it.polimi.ingsw.model.card;

import it.polimi.ingsw.model.Player.ChangeResPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;

/**
 * The {@link LeaderCard} that gives the {@link Player} the possibility to change an white marble with an other {@link Resource}.
 */
public class ChangeResource implements LeaderCard {
    private int victoryPoints;
    private boolean isEnable;
    private Player player;
    private String color1;
    private String color2;
    private Resource change;
    private final String className;
    public String cardID;

    /**
     * Instantiates a new ChangeResource {@link LeaderCard} and sets enable to false.
     *
     * @param victoryPoints the victory points the {@link LeaderCard} gives the {@link Player} when played.
     * @param color1        the color of a {@link DevCard} required for being played.
     * @param color2        the color of a 2 {@link DevCard}s required for being played.
     * @param change        the {@link Resource} in which the new {@link Player} can turn the white marbles.
     * @param cardID        the unique id for the card
     */
    public ChangeResource(int victoryPoints, String color1, String color2, Resource change, String cardID) {
        this.cardID = cardID;
        className = this.getClass().getName();
        this.victoryPoints = victoryPoints;
        this.color1 = color1;
        this.color2 = color2;
        this.change = change;
        isEnable = false;
    }

    @Override
    public void setPlayer(Player tmp) {
        this.player = tmp;
    }

    @Override
    public Resource getPowerResource() {
        return change;
    }

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
    public void assignTo(Player player) {
        this.player = player;
        player.addLeaderCard(this);
    }

    @Override
    public boolean canBePlayed() {
        int secondColor = 0;
        boolean firstColor = false;

        for (DevCard card : player.getBoard().getSlot().getAllCards()) {
            if (card.getColor().equals(color1.toUpperCase())) firstColor = true;
            if (card.getColor().equals(color2.toUpperCase())) secondColor++;
        }

        return (firstColor && secondColor >= 2);
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void playCard() {
        if (canBePlayed()) {
            player.addVictoryPoints(victoryPoints);
            isEnable = true;
            Player tmp = new ChangeResPlayer(player, change);
            //Add the new powered player in substitution to the actual one if the game references
            player.getGame().changePlayer(player, tmp);
            for (LeaderCard card : player.getLeaderCards()) {
                //Do not change the reference on this card
                if (!card.getID().equals(this.getID()))
                    card.setPlayer(tmp);
            }
            this.player = tmp;
        }

    }
}
