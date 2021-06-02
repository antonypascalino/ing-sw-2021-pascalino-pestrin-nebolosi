package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.MarketRequest;
import it.polimi.ingsw.Request.MarketResource;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;
import java.util.Scanner;

public class MarketSelection extends Selection {


    @Override
    public void handleSelection(PlayerData data) {
        ArrayList<MarketResource> marketRes = new ArrayList<MarketResource>();


        MarketArray marketArray =  printer.printMatrix(data.getMarket());
        marketRes.addAll(data.handleWarehouse(marketArray.getResources()));
        Request marketReq = new MarketRequest(marketRes, marketArray.getIndex(), marketArray.getDimension());
    }

}