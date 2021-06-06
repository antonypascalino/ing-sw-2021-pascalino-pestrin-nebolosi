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
    private final String className;


    public DiscardLeaderUpdate(String playerID, ArrayList<TurnState> turnStates, int faithPoints, ArrayList<String> leadersID, ArrayList<PlayerVP> playersVP) {
        this.turnStates = turnStates;
        this.faithPoints = faithPoints;
        this.leadersID = leadersID;
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
        if (playerID.equals(data.getPlayerID())) {
            data.setTurnStates(turnStates);
            data.setFaithPoints(faithPoints);
            data.setLeadersID(leadersID);
            data.getMenu().menuMaker();
        } else {
            for (OtherPlayerData p : data.getOtherPlayers()) {
                if (p.getPlayerID().equals(playerID)) {
                    p.setFaithPoints(faithPoints);
                }
            }

        }
    }
}
