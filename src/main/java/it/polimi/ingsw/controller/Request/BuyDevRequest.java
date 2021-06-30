package it.polimi.ingsw.controller.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.Updates.BuyDevUpdate;
import it.polimi.ingsw.Updates.Update;
import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;


/**
 * The {@link Request} sent by a player when he wants to buy a {@link DevCard}.
 */
public class BuyDevRequest implements Request {
    //The player on which the request is done
    private String playerID;
    private int gameID;
    private String cardID;
    private ArrayList<MappedResource> resources;
    private int slot;
    private DevCard devCard;
    private final String className = this.getClass().getName();

    /**
     * Instantiates a new {@link BuyDevRequest} setting the information for handle the specific actions:
     * the {@link Game}'s ID, the player's nickname, the {@link DevCard}'ID the player wants to buy, all the {@link MappedResource}
     * needed to buy the card and the number of the slot in which the player wants to put the card.
     *
     * @param gameID          the {@link Game}'s ID.
     * @param playerID        the {@link Player}'s ID.
     * @param cardID          the card's ID.
     * @param mappedResources the {@link MappedResource}s.
     * @param slot            the number of the slot.
     */
    public BuyDevRequest(int gameID, String playerID, String cardID, ArrayList<MappedResource> mappedResources, int slot) {
        this.gameID = gameID;
        this.playerID = playerID;
        this.cardID = cardID;
        this.resources = mappedResources;
        this.slot = slot;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public int getGameID() {
        return gameID;
    }

    @Override
    public boolean canBePlayed(Player player) {
        //Get from id returns null if the card is not on the top of the table
        devCard = player.getTable().getDevFromID(cardID);
        if (devCard == null)
            return false;
        boolean hasResource = true;
        boolean checkSpace = true;

        if (!player.canBuy(devCard, player.getAllResources())) {
            hasResource = false;
        }
        if (!player.getBoard().getSlot().checkSpace(devCard, slot)) {
            checkSpace = false;
        }
        return hasResource && checkSpace;
    }

    @Override
    public TurnState handle(Player player, Game game) {
        for (MappedResource mappedRes : resources) {
            player.removeResource(mappedRes.getResource(), mappedRes.getPlace());
        }
        DevCard devcard = game.getTable().buyDev(devCard.getColor(), devCard.getLevel());
        player.getBoard().getSlot().placeCard(devcard, slot);
        player.addVictoryPoints(devcard.getVictoryPoints());
        return TurnState.BUY_DEV_CARD;
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return !(turnStates.contains(TurnState.BUY_DEV_CARD) || turnStates.contains(TurnState.PRODUCE) || turnStates.contains(TurnState.GET_FROM_MARKET));
    }

    @Override
    public Update createUpdate(Player player, Game game) {
        return new BuyDevUpdate(player.getNickName(), game.getTurnStates(), player.getDeposits(), player.getBoard().getStrongBox().getResources(), game.getTable().getFrontIDs(), player.getVictoryPoints(), player.getProductionID());
    }

    @Override
    public String getPlayerID() {
        return playerID;
    }
}
