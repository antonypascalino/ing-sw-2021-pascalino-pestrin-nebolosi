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
    private ArrayList<MarketResource> marketResources;
    private int myFPSteps;
    private int discardedSteps;

    public MarketRequest() {
        this.className = this.getClass().getName();
        myFPSteps = 0;
        discardedSteps = 0;
    }

    @Override
    public void handle(Player player) {
        for (MarketResource marketRes : marketResources) {
            if (!marketRes.getResource().equals(Resource.EMPTY)) {
                if (marketRes.getResource().equals(Resource.FAITH)) {
                    myFPSteps++;
                } else if ((marketRes.getLevel() == -1) || (!player.checkSpace(marketRes.getResource(), marketRes.getLevel()))) {
                    discardedSteps++;
                } else player.addToWareHouse(marketRes.getLevel(), marketRes.getResource());
            }
        }

        if (dimension.equals(Dimension.ROW)) {
            player.getTable().market.getRow(number);
        } else if (dimension.equals(Dimension.COL)) {
            player.getTable().market.getColumn(number);
        }
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return !(turnStates.contains(TurnState.BUY_DEV_CARD) || turnStates.contains(TurnState.PRODUCE) || turnStates.contains(TurnState.GET_FROM_MARKET));
    }

    @Override
    public boolean canBePlayed(Player player) {
        ArrayList<Resource> fromMarket = new ArrayList<Resource>();
        boolean canBePlayed;

        if (dimension.equals(Dimension.ROW)) {
            fromMarket = player.getTable().market.seeRow(number);
        } else if (dimension.equals(Dimension.COL)) {
            fromMarket = player.getTable().market.seeColumn(number);
        }
        //check if the Required resources match the relative market resources and if the empty marbles have been correctly indicated
        if(!player.checkMarketRes(this.requestedRes(), fromMarket)) {
            return false;
        }
        //check if the indicated levels are compatible with the player's level in his WareHouse
        for (MarketResource marketRes : marketResources) {
            if (!player.checkLevel(marketRes.getLevel())) {
                return false;
            }
        }
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
        return TurnState.GET_FROM_MARKET;
    }
}
