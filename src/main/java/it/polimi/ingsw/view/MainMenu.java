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

    public MainMenu(ArrayList<TurnState> turnStates, Scanner inputs, PlayerData data, String input) {
        this.turnStates = turnStates;
        this.inputs = inputs;
        this.data = data;
        this.input = input;
    }

    public void menuMaker(PlayerData data) {
        do {
            turnStates.clear();
            turnStates.addAll(data.turnStateFilter());
            for (int i = 0; i < turnStates.size(); i++) {
                System.out.println("[" + (i + 1) + "]" + "" + turnStates.get(i));
            }
            System.out.println("Enter selection: ");
            //va in ciascuno stato
            input = inputs.nextLine();
            int index = Integer.parseInt(input); //converts String into int
            for (int i = 0; i < index; i++) {
                selectionHandler(turnStates.get(i), data);
                if (turnStates.get(i).equals(TurnState.QUIT)) {
                    input = "-1";
                }
            }
        } while (!input.equals("-1"));
    }

    public void selectionHandler(TurnState state, PlayerData data){
       Selection selection = new Selection();
       selection.handleSelection(data);
    }
}





