package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.view.GameHub;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class DiscardLeaderUpdate implements Update {
    private ArrayList<TurnState> turnStates;
    private int faithPoints;
    private ArrayList<String> leadersID;
    private ArrayList<PlayerVP> playersVP;

    public DiscardLeaderUpdate(ArrayList<TurnState> turnStates, int faithPoints, ArrayList<String> leadersID, ArrayList<PlayerVP> playersVP) {
        this.turnStates = turnStates;
        this.faithPoints = faithPoints;
        this.leadersID = leadersID;
        this.playersVP = playersVP;
    }

    @Override
    public void handleUpdate(GameHub game) {
        game.getCurrData().setTurnStates(turnStates);
        game.getCurrData().setFaithPoints(faithPoints);
        game.getCurrData().setLeadersID(leadersID);

        //aggiorna i victory points di tutti
        for(PlayerVP pvp : playersVP){
            for(PlayerData pd : game.getPlayers()){
                if(pvp.getPlayerID().equals(pd.getPlayerID())){
                    pd.setVictoryPoints(pvp.getVictoryPoints());
                }
            }
        }

    }
}
