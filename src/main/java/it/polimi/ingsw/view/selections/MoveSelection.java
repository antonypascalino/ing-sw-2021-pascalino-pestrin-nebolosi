package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;
import java.util.Scanner;

public class MoveSelection extends Selection{

    public void handleSelection(PlayerData data){

        Scanner inputs = new Scanner(System.in);
        String selection = "";

        ArrayList<Resource[]> levels = data.getWareHouse();

        for(int i = 0; i < levels.size(); i++){
            System.out.print("[" + (i + 1) + "]" + "" + "level " + (i + 1));
        }

        System.out.println("Enter first level: ");
        selection = inputs.nextLine();

        int origin = Integer.parseInt(selection) - 1;
        int destination = data.switchLevels(origin);
    }
}
