package it.polimi.ingsw.model.Cards;

import it.polimi.ingsw.model.Player.Player;

/**
 * The interface Leader card.
 */
public interface LeaderCard {

    /**
     * Play card.
     */
    public void playCard();

    /**
     * Can be played boolean.
     *
     * @return the boolean
     */
    public boolean canBePlayed();

    /**
     * Assign to.
     *
     * @param p the p
     */
    public void assignTo(Player p);
}
