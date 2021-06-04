package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.OtherPlayerData;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class BuyDevUpdate implements Update {

    private ArrayList<TurnState> turnStates;
    private String playerID;
    private ArrayList<Resource[]> wareHouse;
    private ArrayList<Resource[]> extraDep;
    private ArrayList<Resource> strongBox;
    private ArrayList<String> tableCardsID; //just the front table cards
    private int victoryPoints;
    private ArrayList<String> cardsID;  //3 front cards + basic + extraProd
    private final String className;



    public BuyDevUpdate(String playerID, ArrayList<TurnState> turnStates, ArrayList<Resource[]> wareHouse, ArrayList<Resource> strongBox, ArrayList<String> tableCardsID, int victoryPoints, ArrayList<String> cardsID) {
        this.turnStates = turnStates;
        this.wareHouse = wareHouse;
        this.strongBox = strongBox;
        this.tableCardsID = tableCardsID;
        this.victoryPoints = victoryPoints;
        this.cardsID = cardsID;
        this.playerID = playerID;
        className = this.getClass().getName();
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void handleUpdate(PlayerData data) {

        data.setFrontTableCardsID(tableCardsID);
        if(playerID.equals(data.getPlayerID())){
            data.setTurnStates(turnStates);
            data.setWareHouse(wareHouse);
            data.setStrongBox(strongBox);
            data.setFrontCardsID(cardsID);
        }
        else {
            for (OtherPlayerData p : data.getOtherPlayers()) {
                if (playerID.equals(p.getPlayerID())) {
                    p.setStrongBox(strongBox);
                    p.setSlotFrontCards(cardsID);
                    p.getWareHouse().clear();
                    for(Resource[] l : wareHouse){
                        for(Resource r: l){
                            p.getWareHouse().add(r);
                        }
                    }
                }
            }
        }



    }
}
