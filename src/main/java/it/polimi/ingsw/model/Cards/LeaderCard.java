package it.polimi.ingsw.model.Cards;

import it.polimi.ingsw.model.Player.Player;

/**
 * The interface Leader card implemented by all the leader cards
 */
public interface LeaderCard {

    /**
     * Play card, which means setting it to enable
     * Wrapping the player with a new player that correctly implements the
     * new powers and adding the right amount of victory points
     *
     */
    public void playCard();

    /**
     * Can be played is used to check if the player has the resource
     * or the cards for playing a card that he already owns
     *
     * @return true if the player has the resources to play the card
     */
    public boolean canBePlayed();

    /**
     * Assign to a player that's gonna be wrapped when the card is played.
     * Assign in the pre-game phase when the player picks 2 out of the 4 cards
     *
     * @param p the player using the card
     */
    public void assignTo(Player p);

    /**
     * @return true if the card is enabled
     */
    public boolean isEnable();
}
