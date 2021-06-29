package it.polimi.ingsw.Updates;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.card.LeaderCard;
import it.polimi.ingsw.view.data.OtherPlayerData;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The {@link Update} sent after that a {@link Player} discard a {@link LeaderCard}.
 */
public class DiscardLeaderUpdate implements Update {
    private ArrayList<TurnState> turnStates;
    private int faithPoints;
    private ArrayList<String> leadersID;
    private ArrayList<PlayerVP> playersVP;
    private String playerID;
    private final String className;


    /**
     * Instantiates a new {@link DiscardLeaderUpdate} setting everything can change with this actions:
     * the {@link TurnState} list, all {@link Player}'s victory points and the {@link Player}'s faith points.
     *
     * @param playerID    the player id
     * @param turnStates  the turn states
     * @param faithPoints the faith points
     * @param leadersID   the leaders id
     * @param playersVP   the players vp
     */
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
