package it.polimi.ingsw.model.card;
import it.polimi.ingsw.Convertible;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;

/**
 * The interface Leader card implemented by all the leader cards
 */
public interface LeaderCard extends Convertible {

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
    
    public Boolean equals(LeaderCard compare);

    public String getID();

    public void setPlayer(Player tmp);

    public Resource getPowerResource(); // return a resource that changes by the type of leaderCard: for ExtraDep is the placeable, for Change is the changeable, for Discount is the discount and for ExtraProd is the required Resource for produce

    //public Resource getPlaceableRes();

}
