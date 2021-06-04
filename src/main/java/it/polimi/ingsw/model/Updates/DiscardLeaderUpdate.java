package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.view.data.OtherPlayerData;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class DiscardLeaderUpdate implements Update {
    private ArrayList<TurnState> turnStates;
    private int faithPoints;
    private ArrayList<String> leadersID;
    private ArrayList<PlayerVP> playersVP;
    private String playerID;

    public DiscardLeaderUpdate(String playerID, ArrayList<TurnState> turnStates, int faithPoints, ArrayList<String> leadersID, ArrayList<PlayerVP> playersVP) {
        this.turnStates = turnStates;
        this.faithPoints = faithPoints;
        this.leadersID = leadersID;
        this.playersVP = playersVP;
        this.playerID = playerID;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        if (playerID.equals(data.getPlayerID())) {
            data.setTurnStates(turnStates);
            data.setFaithPoints(faithPoints);
            data.setLeadersID(leadersID);
        } else {
            for (OtherPlayerData p : data.getOtherPlayers()) {
                if (p.getPlayerID().equals(playerID)) {
                    p.setFaithPoints(faithPoints);
                }
            }

        }
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
    }
}
