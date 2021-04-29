package it.polimi.ingsw.Request;

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

            if (marketRes.getResource() == Resource.EMPTY) {
                continue;
            }
            else if(marketRes.getResource() == Resource.FAITH) {
                myFPSteps++;
            }
            else if ((marketRes.getLevel() == -1) || (!player.checkSpace(marketRes))) {
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
        ArrayList<Resource> fromMarket = new ArrayList<Resource>();
        boolean canBePlayed;

        if (dimension == Dimension.ROW) {
            fromMarket = player.getTable().market.seeRow(number);
        } else if (dimension == Dimension.COL) {
            fromMarket = player.getTable().market.seeColumn(number);
        }
        //check if the Required resources match the relative market resources and if the empty marbles have been correctly indicated
        if(!player.checkMarketRes(this.requestedRes(), fromMarket)) {
            return false;
        }
        //check if the indicated levels are compatible with the player's level in his WareHouse
        return player.checkLevel(marketResources);
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
