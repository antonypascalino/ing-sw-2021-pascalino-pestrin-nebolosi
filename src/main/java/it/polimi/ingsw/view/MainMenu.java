package it.polimi.ingsw.view;

import it.polimi.ingsw.client.LineClient;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.view.data.PlayerData;
import it.polimi.ingsw.view.selections.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Main menu.
 */
public class MainMenu {

    private ArrayList<TurnState> turnStates;
    private PlayerData data;
    private Printer printer;
    private LineClient connection;

    /**
     * Instantiates a new Main menu.
     *
     * @param data the data
     */
    public MainMenu(PlayerData data) {
        this.printer = data.getPrinter();
        this.turnStates = new ArrayList<TurnState>();
        this.data = data;
    }


    public void setData(PlayerData newData)
    {
        this.data = newData;
    }
    /**
     * Menu maker.
     */
    public void menuMaker() {
        TurnState turnState;
        turnStates.clear();
        turnStates.addAll(data.turnStateFilter());
        turnState = printer.printTurnStates(turnStates);
        selectionHandler(turnState, data);
    }

    private void selectionHandler(TurnState state, PlayerData data){
        Selection selection;

        switch(state){
           case PRODUCE:
               selection = new ProductionSelection();
               selection.handleSelection(data);
               break;
           case BUY_DEV_CARD:
               selection = new BuyDevSelection();
               selection.handleSelection(data);
               break;
           case GET_FROM_MARKET:
               selection = new MarketSelection();
               selection.handleSelection(data);
               break;
           case MOVE_RESOURCE:
               selection = new MoveSelection();
               selection.handleSelection(data);
               break;
           case PLAY_LEADER_CARD:
               selection = new PlayLeaderSelection();
               selection.handleSelection(data);
               break;
           case DISCARD_LEADER_CARD:
               selection = new DiscardLeaderSelection();
               selection.handleSelection(data);
               break;
           case CHECK_STATS:
               selection = new CheckStatsSelection();
               selection.handleSelection(data);
               break;
           case END_TURN:
                selection = new EndTurnSelection();
                selection.handleSelection(data);
                break;
       }
    }
}





