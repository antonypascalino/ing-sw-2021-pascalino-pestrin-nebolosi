package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.InitialPlayersSetRequest;
import it.polimi.ingsw.Request.MarketResource;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@link Selection} that guides the player during the initial part of the game, when he has to
 * choose 2 leader cards and the resource he gained.
 */
public class StartGameSelection extends Selection {

    private int choices;
    private int faithPoints;
    private ArrayList<String> leadersToChoose;

    /**
     * Instantiates a new {@link StartGameSelection}.
     *
     * @param choices         the number of resource on choice.
     * @param faithPoints     the faith points
     * @param leadersToChoose the leader card to choose
     */
    public StartGameSelection(int choices, int faithPoints, ArrayList<String> leadersToChoose) {
        this.choices = choices;
        this.faithPoints = faithPoints;
        this.leadersToChoose = leadersToChoose;
    }

    @Override
    public void handleSelection(PlayerData data) {
        data.setLeadersID(data.getPrinter().chooseLeaderCard(leadersToChoose, data));
        data.setFaithPoints(faithPoints);
        ArrayList<Resource> chosen = new ArrayList<>();
        for (int i = choices; i > 0; i--) {
            chosen.add(data.getPrinter().chooseResource());
        }
        ArrayList<MarketResource> marketChosen = data.handleWarehouse(chosen);
        ArrayList<String> leaderChosen = data.getLeaders();
        data.getPrinter().printMessage("\nPlease wait until all players make their choices...");
        Request initialSet = new InitialPlayersSetRequest(data.getGameID(), data.getPlayerID(), marketChosen, leaderChosen);
        data.sendRequest(initialSet);
    }
}
