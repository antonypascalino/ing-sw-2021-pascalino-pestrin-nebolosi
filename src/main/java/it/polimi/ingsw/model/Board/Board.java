package it.polimi.ingsw.model.Board;

import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

/**
 * The player's Board with the references to player's {@link WareHouse}, {@link StrongBox}, {@link Slot}, {@link FaithPath} and {@link BasicDevSpace}
 */
public class Board {
    private WareHouse wareHouse;
    private StrongBox strongBox;
    private Slot slot;
    private TempBox tempBox;
    private FaithPath faithPath;
    private BasicDevSpace basicDevSpace;
    private Player player;

    /**
     * Instantiates a new Board for the {@link Player} building the objects it needs:
     * {@link WareHouse}, {@link StrongBox}, {@link Slot}, {@link FaithPath}, {@link BasicDevSpace} and {@link TempBox}.
     *
     * @param player the {@link Player} who owns the Board.
     */
    public Board(Player player) {
        wareHouse = new WareHouse();
        strongBox = new StrongBox();
        slot = new Slot();
        tempBox = new TempBox(strongBox);
        faithPath = new FaithPath(this);
        basicDevSpace = new BasicDevSpace(this);
        this.player = player;
    }

    /**
     * Gets the {@link Player}.
     *
     * @return the {@link Player}.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets {@link Player}.
     *
     * @param player the {@link Player} to set.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets the {@link WareHouse} linked to the {@link Board}.
     *
     * @return the reference to the {@link WareHouse} of the board.
     */
    public WareHouse getWareHouse() {
        return wareHouse;
    }

    /**
     * Gets the {@link StrongBox} linked to the {@link Board}.
     *
     * @return the reference to the {@link StrongBox} of the board
     */
    public StrongBox getStrongBox() {
        return strongBox;
    }

    /**
     * Gets the {@link Slot}s linked to the {@link Board}.
     *
     * @return the reference to the {@link Slot} of the board
     */
    public Slot getSlot() {
        return slot;
    }

    /**
     * Gets the {@link TempBox} linked to the {@link Board}.
     *
     * @return the reference to the {@link TempBox} of the board
     */
    public TempBox getTempBox() {
        return tempBox;
    }

    /**
     * Gets the {@link FaithPath} linked to the {@link Board}.
     *
     * @return the reference to the {@link FaithPath} of the board.
     */
    public FaithPath getFaithPath() {
        return faithPath;
    }

    /**
     * Check if {@link Player} has all the {@link Resource}s received as parameters,
     * watching in both {@link WareHouse} and {@link StrongBox}.
     *
     * @param needed the ArrayList with all the resources to check
     * @return true if the player has all needed resources, false otherwise.
     */
    public boolean hasResources(ArrayList<Resource> needed) {
        ArrayList<Resource> tmp = new ArrayList<>();
        tmp.addAll(strongBox.getResources());
        tmp.addAll(wareHouse.getResources());
        return tmp.containsAll(needed);
    }

    /**
     * Gets the {@link DevCard} with the cardID received as parameter, searching among player's DevCards.
     *
     * @param cardID the card's ID of the {@link DevCard} it wants.
     * @return the {@link DevCard}, null
     */
    public DevCard getDevFromID(String cardID) {
        for (DevCard devCard : this.slot.getAllCards()) {
            if (devCard.getCardID().equals(cardID)) {
                return devCard;
            }
        }
        return null;
    }

    /**
     * Gets the IDs of the {@link DevCard}s with which the {@link Player} can produce.
     *
     * @return an ArrayList with all the IDs of the {@link DevCard}s.
     */
    public ArrayList<String> getProdID() {
        ArrayList<String> prodID = new ArrayList<>();
        DevCard[] tmp = slot.getFrontCards();

        prodID.add(basicDevSpace.getCardID());
        for (DevCard dev : tmp) {
            //if it contains an empty space it can't get any card Id
            if (dev != null)
                prodID.add(dev.getCardID());
        }
        return prodID;
    }
}
