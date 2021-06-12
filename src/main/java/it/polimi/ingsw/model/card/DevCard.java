package it.polimi.ingsw.model.card;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.controller.Game;

import java.util.ArrayList;


/**
 * The card that allows the {@link Player} to produce {@link Resource} from other {@link Resource}.
 */
public class DevCard {
    private final String color;
    private final int level;
    private final int victoryPoint;
    private boolean isEnable;
    private String cardID;
    private Player owner;
    private ArrayList<Resource> requires;
    private final ArrayList<Resource> produces;
    private final ArrayList<Resource> price;

    /**
     * Instantiates a new {@link DevCard} with all its features and set its enable to false.
     *
     * @param id  the {@link DevCard}'s ID.
     * @param col the {@link DevCard}'s color.
     * @param lev the {@link DevCard}'s level.
     * @param vp  the {@link DevCard}'s victory points gave to a {@link Player} who will buy this card.
     * @param req the required {@link Resource} needed for producing.
     * @param pro the {@link Resource}s produced by this {@link DevCard}.
     * @param pri the price for buying this {@link Resource}.
     */
    public DevCard(String id, String col, int lev, int vp, ArrayList<Resource> req, ArrayList<Resource> pro, ArrayList<Resource> pri) {
        cardID = id;
        color = col;
        level = lev;
        victoryPoint = vp;
        requires = req;
        produces = pro;
        price = pri;
        isEnable = false;
        owner = null;
        cardID = id;
    }

    /**
     * Sets owner when the {@link Resource} buys a {@link DevCard}.
     *
     * @param owner the owner
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Gets the {@link DevCard}'s color.
     *
     * @return the {@link DevCard}'s color
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets the {@link DevCard}'s level.
     *
     * @return the {@link DevCard}'s level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the produced {@link Resource}s.
     *
     * @return the array list of the produced {@link Resource}.
     */

    public ArrayList<Resource> producedResources() {
        return (ArrayList<Resource>) produces.clone();
    }

    /**
     * Gets the {@link DevCard}'s price
     *
     * @return the price that the {@link Player} needs to buy the {@link DevCard}.
     */
    public ArrayList<Resource> getPrice() {
        return price;
    }

    /**
     * Gets {@link DevCard}'s ID.
     *
     * @return the {@link DevCard}'s ID.
     */
    public String getCardID() {
        return cardID;
    }

    /**
     * Get the required {@link Resource} to produce whit this {@link DevCard}.
     *
     * @return the resources needed for using this {@link DevCard}.
     */
    public ArrayList<Resource> getRequirements(){
        return requires;
    }

    /**
     * Gets the {@link DevCard}'s victory points.
     *
     * @return the victory points
     */
    public int getVictoryPoints() {
        return victoryPoint;
    }
}
