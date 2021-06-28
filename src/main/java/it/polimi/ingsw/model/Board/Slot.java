package it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.card.DevCard;

import java.util.ArrayList;

/**
 * Contains an ArrayList of Arrays with all the {@link DevCard} in possessions by the {@link Player}.
 */
public class Slot {
    private ArrayList<DevCard[]> slots;

    /**
     * Instantiates the ArrayList with the 3 empty lots: every one of the 3 slots is an array of 3 {@link DevCard}
     */
    public Slot() {
        slots = new ArrayList<>();
        DevCard[] tmp = new DevCard[3];
        slots.add(tmp);
        tmp = new DevCard[3];
        slots.add(tmp);
        tmp = new DevCard[3];
        slots.add(tmp);
    }

    /**
     * Receive a {@link DevCard} and add it in the first empty space in the slot chosen by the {@link Player}.
     *
     * @param card the {@link DevCard} to add into the Slot.
     * @param slot the slot in which add the {@link DevCard}
     */
    public void placeCard(DevCard card, int slot) {
        //if checkSpace is true
        DevCard[] currSlot = slots.get(slot);

        //the card with the highest level will be at the end
        for (int i = 0; i < currSlot.length; i++) {
            if (currSlot[i] == null) {
                currSlot[i] = card;
                break;
            }
        }
    }

    /**
     * Check if the {@link DevCard} it wants to add can be add in the chosen slot according to the Slot's rules.
     *
     * @param card the {@link DevCard} it wants to add.
     * @param slot the slot where it wants add the {@link DevCard}.
     * @return true if the {@link DevCard} can be add in the chosen slot, false otherwise.
     */
    public boolean checkSpace(DevCard card, int slot) {
        //gets the array of cards on the address "slot"
        DevCard[] currSlot = slots.get(slot);
        //current card in the selected slot
        DevCard currCard;

        //checks if the slot is empty or not
        if (card.getLevel() == 1) {
            for (int i = 0; i < currSlot.length; i++) {
                if (currSlot[i] != null) {
                    return false;
                }
            }
            return true;
        } else {
            //checks slot card level
            for (int i = 0; i < currSlot.length; i++) {
                if (currSlot[i] != null) {
                    currCard = currSlot[i];
                    if (card.getLevel() == currCard.getLevel() + 1) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /**
     * Get an array with the {@link DevCard} on the top of every slot: the only usable to produce.
     *
     * @return an Array with all the {@link DevCard} usable to produce;
     */
    public DevCard[] getFrontCards() {
        //looks at the last element of each slot
        DevCard[] currSlot;
        DevCard[] frontCards = new DevCard[3];
        for (int i = 0; i < slots.size(); i++) {
            //frontCards is updated if either there's a card or the index is null
            currSlot = slots.get(i);
            for (int j = 0; j < currSlot.length - 1; j++) {
                //if one of the 3 indexes of frontCards is null it means that in a slot
                //there are no cards
                if (currSlot[j + 1] == null) {
                    frontCards[i] = currSlot[j];
                    break;
                }
            }
            //If after scanning a whole stack the front card is still null it means that the top one is on the top is in 0
            if (frontCards[i] == null) {
                frontCards[i] = currSlot[2];
            }

        }
        return frontCards;
    }

    /**
     * Get an ArrayList with all the {@link DevCard} in possession by the {@link Player} (even if not usable to produce).
     *
     * @return An ArrayList with all the {@link DevCard} in possession by the {@link Player}.
     */
    public ArrayList<DevCard> getAllCards() {
        //searches through all the slots and returns all the cards
        ArrayList<DevCard> allCards = new ArrayList<>();
        DevCard[] currSlot;
        for (int i = 0; i < slots.size(); i++) {
            currSlot = slots.get(i);
            for (int j = 0; j < currSlot.length; j++)
                if (currSlot[j] != null)
                    allCards.add(currSlot[j]);
        }
        return allCards;
    }
}
