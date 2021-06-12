package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Table.Table;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Table.Market;
import it.polimi.ingsw.view.data.OtherPlayerData;
import it.polimi.ingsw.view.data.PlayerData;
import it.polimi.ingsw.view.selections.Selection;
import it.polimi.ingsw.view.selections.StartGameSelection;
import it.polimi.ingsw.model.card.DevCard;

import java.util.ArrayList;

/**
 * The {@link Update} sent when a new {@link Game} starts.
 */
public class NewGameUpdate implements Update{

    private ArrayList<String> frontTableCardsID;
    private Resource[][] market;
    private ArrayList<PlayerLC> playersLC; // una mapped che per ogni playerID associa un arrayList di 4 leaderID random importante è che non si ripetano le carte
    private ArrayList<PlayerST> playersST; // una mapped che per ogni playerID indica il numero di risorse a scelta a inizio game
    private final String className;
    private int gameID;

    /**
     * Instantiates a new {@link NewGameUpdate} setting the initial situation of both {@link Market} and {@link Table}
     * and every initial faith point or {@link Resource}s the players receive when the game starts.
     *
     * @param gameID            the {@link Game}'s ID.
     * @param frontTableCardsID the front table {@link DevCard} ID.
     * @param market            the {@link Market}.
     * @param playersLC         all the {@link PlayerLC}.
     * @param playersST         all the {@link PlayerST}.
     */
    public NewGameUpdate(int gameID, ArrayList<String> frontTableCardsID, Resource[][] market, ArrayList<PlayerLC> playersLC, ArrayList<PlayerST> playersST) {
        this.frontTableCardsID = frontTableCardsID;
        this.market = market;
        this.playersLC = playersLC;
        this.playersST = playersST;
        className = this.getClass().getName();
        this.gameID = gameID;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        data.setFrontTableCardsID(frontTableCardsID);
        data.setMarket(market);
        data.setGameID(gameID);

        int dataFaithPoint = 0;
        int dataChoices = 0;
        ArrayList<String> leadersToChoose = new ArrayList<String>();


        for (PlayerLC p : playersLC) {

            if (p.getPlayerID().equals(data.getPlayerID())) {
                leadersToChoose.addAll(p.getLeadersToChoose());
            }
            else data.getOtherPlayers().add(new OtherPlayerData(p.getPlayerID()));
        }
        for (PlayerST p : playersST) {
            if (p.getPlayerID().equals(data.getPlayerID())) {
                dataChoices = p.getChoices();
                dataFaithPoint = p.getFaithPoint();
            }
        }
        Selection startGameSelection = new StartGameSelection(dataChoices, dataFaithPoint, leadersToChoose);
        startGameSelection.handleSelection(data);
    }


}
