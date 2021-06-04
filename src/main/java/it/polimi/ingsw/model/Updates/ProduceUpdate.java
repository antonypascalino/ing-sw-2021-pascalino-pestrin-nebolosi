package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.OtherPlayerData;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class ProduceUpdate implements Update {
    private ArrayList<TurnState> turnStates;
    private ArrayList<Resource[]> wareHouse;
    private ArrayList<Resource> strongBox;
    private int faithPoints;
    private ArrayList<PlayerVP> playersVP;
    private String playerID;
    private final String className;

    public ProduceUpdate(String playerID, ArrayList<TurnState> turnStates, ArrayList<Resource[]> wareHouse, ArrayList<Resource> strongBox, int faithPoints, ArrayList<PlayerVP> playersVP) {
        this.turnStates = turnStates;
        this.wareHouse = wareHouse;
        this.strongBox = strongBox;
        this.faithPoints = faithPoints;
        this.playersVP = playersVP;
        this.playerID = playerID;
        className = this.getClass().getName();
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        for (PlayerVP pvp : playersVP) {
            if (pvp.getPlayerID().equals(data.getPlayerID())) {
                data.setVictoryPoints(pvp.getVictoryPoints());
            } else {
                for (OtherPlayerData p : data.getOtherPlayers()) {
                    if (pvp.getPlayerID().equals(p.getPlayerID())) {
                        p.setVictoryPoints(pvp.getVictoryPoints());
                    }
                }
            }
        }
        if(playerID.equals(data.getPlayerID())){
            data.setFaithPoints(faithPoints);
            data.setTurnStates(turnStates);
            data.setWareHouse(wareHouse);
            data.setStrongBox(strongBox);
        }
        else{
            for(OtherPlayerData p: data.getOtherPlayers()){
                if(playerID.equals(p.getPlayerID())){
                    p.setFaithPoints(faithPoints);
                    p.setStrongBox(strongBox);
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
