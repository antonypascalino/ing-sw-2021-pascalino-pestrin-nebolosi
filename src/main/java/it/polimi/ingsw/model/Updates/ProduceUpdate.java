package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.GameHub;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class ProduceUpdate implements Update {
    private ArrayList<TurnState> turnStates;
    private ArrayList<Resource[]> wareHouse;
    private ArrayList<Resource> strongBox;
    private int faithPoints;
    private ArrayList<PlayerVP> playersVP;

    public ProduceUpdate(ArrayList<TurnState> turnStates, ArrayList<Resource[]> wareHouse, ArrayList<Resource> strongBox, int faithPoints, ArrayList<PlayerVP> playersVP) {
        this.turnStates = turnStates;
        this.wareHouse = wareHouse;
        this.strongBox = strongBox;
        this.faithPoints = faithPoints;
        this.playersVP = playersVP;
    }

    @Override
    public void handleUpdate(GameHub game) {
        for(PlayerVP pvp : playersVP){
            for(PlayerData pd : game.getPlayers()){
                if(pvp.getPlayerID().equals(pd.getPlayerID())){
                    pd.setVictoryPoints(pvp.getVictoryPoints());

                }
            }
        }
        game.getCurrData().setFaithPoints(faithPoints);
        game.getCurrData().setTurnStates(turnStates);
        game.getCurrData().setWareHouse(wareHouse);
        game.getCurrData().setStrongBox(strongBox);
    }
}
