package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.MarketResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;
import java.util.Scanner;

public class MarketSelection extends Selection {


    @Override
    public void handleSelection(PlayerData data) {
        ArrayList<Resource> resFromMarket = new ArrayList<Resource>();
        ArrayList<MarketResource> marketRes = new ArrayList<MarketResource>();
        resFromMarket.addAll(printer.printMatrix(data.getMarket()));
        marketRes.addAll(data.handleWarehouse(resFromMarket));
    }

    @Override
    public void sendToConnection() {

    }
}
