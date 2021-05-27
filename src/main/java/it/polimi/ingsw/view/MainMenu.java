package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.view.data.PlayerData;
import it.polimi.ingsw.view.selections.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {

    ArrayList<TurnState> turnStates = new ArrayList<TurnState>();
    Scanner inputs = new Scanner(System.in);
    PlayerData data;
    String input = "";
    Printer printer;

    public MainMenu(ArrayList<TurnState> turnStates, Scanner inputs, PlayerData data, String input) {
        this.turnStates = new ArrayList<TurnState>();
        this.turnStates.addAll(turnStates);
        this.inputs = inputs;
        this.data = data;
        this.input = input;
    }

    public void menuMaker(PlayerData data) {
        do {
            turnStates.clear();
            turnStates.addAll(data.turnStateFilter());
            selectionHandler(printer.printTurnStates(turnStates), data);
        } while (!input.equals("-1"));
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





