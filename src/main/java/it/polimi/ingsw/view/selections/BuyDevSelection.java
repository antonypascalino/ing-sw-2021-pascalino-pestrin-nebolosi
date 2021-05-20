package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.Production;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;
import java.util.Scanner;

public class BuyDevSelection extends Selection {

    @Override
    public void handleSelection(PlayerData data){
        Scanner inputs = new Scanner(System.in);
        ArrayList<String> cards = new ArrayList<String>();
        String selection = "";

        cards.addAll(data.tableCardsFilter());
        ArrayList<MappedResource> mappedRes = new ArrayList<MappedResource>();
        for (int i = 0; i < cards.size(); i++) {
            System.out.println("[" + (i + 1) + "]" + "" + cards.get(i));
        }
        selection = inputs.nextLine();
        int index = Integer.parseInt(selection);
        mappedRes.addAll(data.createMappedRes(data.getCardFromID(cards.get(index-1)).getRequired()));
        int slot = data.handleSlots(cards.get(index-1));
        //ora ho l'id + lo slot + la mapped di quella carta
    }

}
