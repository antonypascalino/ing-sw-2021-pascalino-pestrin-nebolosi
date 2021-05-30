package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

public class BuyDevRequest implements Request {
    //The player on which the request is done
    private String playerID;
    private int gameID;
    private String cardID;
    private ArrayList<MappedResource> resources;
    private int slot;

    DevCard devCard;
    private final String className = this.getClass().getName();

    public BuyDevRequest(int gameID, String playerID, String cardID, ArrayList<MappedResource> mappedResources, int slot)
    {
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

    public boolean canBePlayed(Player player) {
        //Get from id returns null if the card is not on the top of the table
        devCard = player.getTable().getDevFromID(cardID);
        if (devCard == null)
            return false;
        boolean hasResource = true;
        boolean checkSpace = true;

        if (!player.canBuy(devCard, player.getAllResources())) {
            //lancia eccezione: non hai risorse per comprare questa carta
            hasResource = false;
        }
        if (!player.getBoard().getSlot().checkSpace(devCard, slot)) {
            //lancia eccezione: questa carta non può essere messa in questo slot
            checkSpace = false;
        }
        //else Lancia eccezione; non la puoi mettere qua

        return hasResource && checkSpace;
    }

    @Override
    public TurnState handle(Player player, Game game) {
        for (MappedResource mappedRes : resources) {
            player.removeResource(mappedRes.getResource(), mappedRes.getPlace());
        }
        DevCard devcard = game.getTable().buyDev(devCard.getColor(), devCard.getLevel());
        player.getBoard().getSlot().placeCard(devcard, slot);
        return TurnState.BUY_DEV_CARD;
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return !(turnStates.contains(TurnState.BUY_DEV_CARD) || turnStates.contains(TurnState.PRODUCE) || turnStates.contains(TurnState.GET_FROM_MARKET));
    }

}
