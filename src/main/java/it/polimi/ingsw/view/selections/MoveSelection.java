package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.Request.MoveRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;
import java.util.Scanner;

public class MoveSelection extends Selection{

    public void handleSelection(PlayerData data){

        Scanner inputs = new Scanner(System.in);
        String selection = "";
        ArrayList<Integer> l = new ArrayList<Integer>();
        ArrayList<Resource[]> levels = data.getDeposits();
        for(int i = 0; i < levels.size(); i++){
            l.add(i);
        }
        int origin = printer.printIntegers(l, false);
        int destination = data.switchLevels(origin);

        Request moveReq = new MoveRequest();
    }
}
