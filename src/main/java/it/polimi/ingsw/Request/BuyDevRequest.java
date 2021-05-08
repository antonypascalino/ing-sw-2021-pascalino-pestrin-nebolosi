package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Cards.DevCard;
import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

public class BuyDevRequest implements Request {
    private String cardID;
    private ArrayList<MappedResource> resources;
    private int slot;

    @Override
    public String getClassName() {
        return "BuyDevRequest";
    }

    @Override
    public void handle(Player player) {
        for(MappedResource mappedRes : resources) {
            player.removeResource(mappedRes.getResource(), mappedRes.getPlace());
        }
        DevCard devcard = player.getTable().buyDev(player.getTable().getDevFromID(cardID).getColor(),player.getTable().getDevFromID(cardID).getLevel());
        player.getBoard().getSlot().placeCard(devcard, slot);
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return !(turnStates.contains(TurnState.BUY_DEV_CARD) || turnStates.contains(TurnState.PRODUCE) || turnStates.contains(TurnState.GET_FROM_MARKET));
    }

    @Override
    public boolean canBePlayed(Player player) {
        DevCard devCard = player.getTable().getDevFromID(cardID);
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
    public TurnState nextTurnState() {
        return TurnState.BUY_DEV_CARD;
    }

    @Override
    public int getMyFPSteps() {
        return 0;
    }

    @Override
    public int getDiscardedSteps() {
        return 0;
    }

    @Override
    public int getPlayerChoices() {
        return 0;
    }
}
