package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.InitialPlayersSetRequest;
import it.polimi.ingsw.Request.MarketResource;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.card.LeaderCard;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The type Start game selection.
 */
public class StartGameSelection extends Selection {

    private int choices;
    private int faithPoints;
    private ArrayList<String> leadersToChoose;

    /**
     * Instantiates a new Start game selection.
     *
     * @param choices         the choices
     * @param faithPoints     the faith points
     * @param leadersToChoose the leaders to choose
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
        ArrayList<Resource> chosen = new ArrayList<Resource>();
        for (int i = choices; i > 0; i--) {
            chosen.add(data.getPrinter().chooseResource());
        }
        ArrayList<MarketResource> marketChosen = data.handleWarehouse(chosen);
        ArrayList<String> leaderChosen = data.getLeaders();
        Request initialSet = new InitialPlayersSetRequest(data.getGameID(), data.getPlayerID() , marketChosen, leaderChosen);
        data.sendRequest(initialSet);
    }


}
