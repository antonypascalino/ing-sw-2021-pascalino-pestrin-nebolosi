package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.view.data.PlayerData;
import it.polimi.ingsw.view.selections.Selection;

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

    public void selectionHandler(TurnState state, PlayerData data){
       Selection selection = new Selection();
       selection.handleSelection(data);
    }
}





