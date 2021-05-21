package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
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

    //Non controlla che il player abbia effettivamente spazio per tutte le risorse
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
        //Passa dal player perchè potrebbero essere livelli extra
        for (MarketResource marketRes : marketResources) {
            if (!player.checkLevel(marketRes.getLevel())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public TurnState handle(Player player, Game game) {
        //It has already checked that is the same array as the market row or column
        for (MarketResource marketRes : marketResources) {
            if (!marketRes.getResource().equals(Resource.EMPTY)) {
                if (marketRes.getResource().equals(Resource.FAITH)) {
                    myFPSteps++;
                    //Level = -1 means the player wants to discard it
                } else if ((marketRes.getLevel() == -1) || (!player.checkSpace(marketRes.getResource(), marketRes.getLevel()))) {
                    discardedSteps++;
                } else player.addResource(marketRes.getLevel(), marketRes.getResource());
            }
        }
        //Abbiamo già confrontato che le risorse richieste dal player matchano le corrispondenti risorse del mercato (tenenedo anche conto delle Changes)
        // per cui si possono usare quelle, già matchate e changeate per aggiungerle al Player e modificare il mercato di conseguenza.
        // altrimenti si dovrebbe fare un altro giro di chiamate per prendere le risorse dal mercato cambiare le empty e aggiungerle al player.
        if (dimension.equals(Dimension.ROW)) {
            player.getTable().market.getRow(number);
        } else if (dimension.equals(Dimension.COL)) {
            player.getTable().market.getColumn(number);
        }

        game.fpAdvancement(discardedSteps,myFPSteps);
        return TurnState.GET_FROM_MARKET;
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return !(turnStates.contains(TurnState.BUY_DEV_CARD) || turnStates.contains(TurnState.PRODUCE) || turnStates.contains(TurnState.GET_FROM_MARKET));
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

}
