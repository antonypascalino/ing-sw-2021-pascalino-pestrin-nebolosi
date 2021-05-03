package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Cards.DevCard;
import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Player.Player;

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

        player.getBoard().removeResources(resources);
        DevCard devcard = player.getTable().buyDev(player.getTable().getDevFromID(cardID).getColor(),player.getTable().getDevFromID(cardID).getLevel());
        player.getBoard().getSlot().placeCard(devcard, slot);
    }

    @Override
    public boolean validRequest(TurnState turnState) {
        return (turnState.equals(TurnState.INITIAL) || turnState.equals(TurnState.PLAY_LEADER_CARD_START) || turnState.equals(TurnState.MOVE_RESOURCE));
    }

    @Override
    public boolean canBePlayed(Player player) {
        DevCard devCard = player.getTable().getDevFromID(cardID);
        boolean hasResource = true;
        boolean checkResource = true;

        if (!player.getAllResources().containsAll(devCard.getPrice())) {
            //lancia eccezione: nonhai risorse per comprare carta
            hasResource = false;
        }
        if (!player.getBoard().getSlot().checkSpace(devCard, slot)) {
            //lancia eccezione: questa carta non può essere messa in questo slot
            checkResource = false;
        }
        //else Lancia eccezione; non la puoi mettere qua

        return hasResource && checkResource;
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
}
