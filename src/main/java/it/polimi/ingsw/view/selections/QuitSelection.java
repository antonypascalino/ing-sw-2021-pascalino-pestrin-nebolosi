package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Quit selection.
 */
public class QuitSelection extends Selection{


    @Override
    public void handleSelection(PlayerData data) {
        Scanner inputs = new Scanner(System.in);
        String selection = "";
        System.out.println("Are you sure you want to quit the game?");
        System.out.println("[1] Yes\n[2] No");
        selection = inputs.nextLine();
        switch(selection){
            case "1":
                System.out.println("Bye");
                break;
            case "2":
                System.out.println("Ok");
                break;
    }


    }
}
