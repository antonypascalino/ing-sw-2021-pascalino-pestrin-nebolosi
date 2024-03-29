package it.polimi.ingsw.model.card;
import it.polimi.ingsw.Convertible;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.controller.Game;

/**
 * The interface implemented by all the leader cards.
 * A leader card gives the {@link Player} new abilities he can use during a {@link Game}
 */
public interface LeaderCard extends Convertible {

    /**
     * Set the {@link LeaderCard} to enable,
     * wrap the {@link Player} with a new player that correctly implements the
     * new abilities and adding him the right amount of victory points.
     */
    void playCard();

    /**
     * Used to check if the {@link Player} has the requirements for playing a {@link LeaderCard} that he already owns.
     *
     * @return true if the {@link Player} has the requirements to play the {@link LeaderCard}, false otherwise.
     */
    boolean canBePlayed();

    /**
     * Assign the {@link LeaderCard} to a {@link Player}.
     *
     * @param p the {@link Player} to whom assign the {@link LeaderCard}.
     */
    void assignTo(Player p);

    /**
     * Check if the {@link LeaderCard} has been played by the {@link Player} or not.
     *
     * @return true if the {@link LeaderCard} has been played.
     */
    boolean isEnable();

    /**
     * Compare two {@link LeaderCard}.
     *
     * @param compare the leader to compare.
     * @return true if the leadercard match, false otherwise.
     */
    boolean equals(LeaderCard compare);

    /**
     * Gets the {@link LeaderCard}'s ID.
     *
     * @return the {@link LeaderCard}'s ID.
     */
    String getID();

    /**
     * Sets the {@link Player} to this {@link LeaderCard}
     *
     * @param tmp the Player who has to be setted the card
     */
    void setPlayer(Player tmp);

    /**
     * Gets a {@link Resource} that changes by the type of leaderCard:
     * for {@link ExtraDeposit} is the placeable,
     * for {@link ChangeResource} is the changeable,
     * for {@link Discount} is the discount
     * and for {@link ExtraProd} is the required resource for produce.
     *
     * @return the {@link Resource}.
     */
    Resource getPowerResource();
}
