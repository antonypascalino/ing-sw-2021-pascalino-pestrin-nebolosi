package it.polimi.ingsw.view;

import it.polimi.ingsw.client.LineClient;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.view.data.PlayerData;
import it.polimi.ingsw.view.selections.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {

    private ArrayList<TurnState> turnStates;
    private PlayerData data;
    private Printer printer;
    private LineClient connection;

    public MainMenu(LineClient thisPlayer, PlayerData data) {
        this.printer = data.getPrinter();
        this.connection = thisPlayer;
        this.turnStates = new ArrayList<TurnState>();
        this.data = data;
    }

    public void menuMaker() {
        TurnState turnState;
        do {
            turnStates.clear();
            turnStates.addAll(data.turnStateFilter());
            turnState = printer.printTurnStates(turnStates);
            selectionHandler(turnState, data);
        } while (!turnState.equals(TurnState.END_TURN));

    }

    private void selectionHandler(TurnState state, PlayerData data){
        Selection selection;

        switch(state){
           case PRODUCE:
               selection = new ProductionSelection();
               selection.handleSelection(data);
           case BUY_DEV_CARD:
               selection = new BuyDevSelection();
               selection.handleSelection(data);
           case GET_FROM_MARKET:
               selection = new MarketSelection();
               selection.handleSelection(data);
           case MOVE_RESOURCE:
               selection = new MoveSelection();
               selection.handleSelection(data);
           case PLAY_LEADER_CARD:
               selection = new PlayLeaderSelection();
               selection.handleSelection(data);
           case DISCARD_LEADER_CARD:
               selection = new DiscardLeaderSelection();
               selection.handleSelection(data);
           case CHECK_STATS:
               selection = new CheckStatsSelection();
               selection.handleSelection(data);
       }
    }
}





