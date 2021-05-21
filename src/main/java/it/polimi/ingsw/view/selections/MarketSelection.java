package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.controller.MarketResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.view.data.PlayerData;
import jdk.vm.ci.code.site.Mark;

import java.util.ArrayList;
import java.util.Scanner;

public class MarketSelection extends Selection {


    @Override
    public void handleSelection(PlayerData data) {
        Scanner inputs = new Scanner(System.in);
        ArrayList<Resource> resFromMarket = new ArrayList<Resource>();
        ArrayList<MarketResource> marketRes = new ArrayList<MarketResource>();
        String selection = "";
        Resource[][] market = data.getMarket();
        int i = 0;
        //da fare meglio la matrice a schermo
        System.out.println("    1  2   3  4");
        for(int w = 0; w < market.length; w++) {
                System.out.print(w + 1);
                for (int j = 0; j < market[w].length; j++) {
                    System.out.print(market[i][j] + " ");
                }
                System.out.println("");

            }
        selection = inputs.nextLine();
        int index = Integer.parseInt(selection);
        //devo capire come selezionare effettivamente una riga o una colonna


        marketRes.addAll(data.handleWarehouse(resFromMarket));


    }


    @Override
    public void sendToConnection() {

    }
}
