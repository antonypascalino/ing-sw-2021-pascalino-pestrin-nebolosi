package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.MarketResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

public class MarketRequest implements Request {
    private Dimension dimension;
    private int number;
    private final String className;
    private Player player;
    private ArrayList<MarketResource> marketResources;
    private int myFPSteps;
    private int discardedSteps;

    public MarketRequest() {
        this.className = this.getClass().getName();
        myFPSteps = 0;
        discardedSteps = 0;
    }

    @Override
    public void handle() {
        for (MarketResource marketRes : marketResources) {

            //Check if the Resource is a FAITH
            if(marketRes.getResource() == Resource.FAITH) {
                myFPSteps++;
            }
            else if (marketRes.getLevel() == -1) {
                discardedSteps++;
            }
            else if (!player.checkSpace(marketRes)) {
                discardedSteps++;
            }
            else player.addToWareHouse(marketRes.getLevel(), marketRes.getResource());
        }
    }

    @Override
    public boolean validRequest(TurnState turnState, Player currPlayer) {
        return (currPlayer == player) &&
                (turnState == TurnState.Initial || turnState == TurnState.playLeaderCard || turnState == TurnState.moveResource);
    }

    @Override
    public boolean canBePlayed(Player player) {
        return true;
    }

    @Override
    public String getClassName() {
        return className;
    }

    private ArrayList<Resource> requiredRes() {
        ArrayList<Resource> requiredRes = new ArrayList<Resource>();
        for (MarketResource marketRes : marketResources) {
            requiredRes.add(marketRes.getResource());
        }
        return requiredRes;
    }

    public int getMyFPSteps() {
        return myFPSteps;
    }

    public int getDiscardedSteps() {
        return discardedSteps;
    }

    public ArrayList<Resource> requestedRes(){
        ArrayList<Resource> requestedRes = new ArrayList<Resource>();
        for(MarketResource marketRes : marketResources) {
            requestedRes.add(marketRes.getResource());
        }
        return requestedRes;
    }

    @Override
    public TurnState nextTurnState() {
        return TurnState.getFromMarket;
    }
}
