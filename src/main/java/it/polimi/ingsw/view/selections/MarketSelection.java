package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.MarketRequest;
import it.polimi.ingsw.Request.MarketResource;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.view.data.PlayerData;
import java.util.ArrayList;


public class MarketSelection extends Selection {


    @Override
    public void handleSelection(PlayerData data) {
        ArrayList<MarketResource> marketRes = new ArrayList<MarketResource>();

        MarketArray marketArray =  data.getPrinter().printMatrix(data.getMarket());
        marketRes.addAll(data.handleWarehouse(marketArray.getResources()));
        Request marketReq = new MarketRequest(marketArray.getDimension(), marketArray.getIndex(), data.getGameID(), data.getPlayerID(), marketRes);
        sendToConnection(marketReq);
    }
}