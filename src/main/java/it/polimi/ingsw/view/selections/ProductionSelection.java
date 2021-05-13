package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductionSelection extends Selection {



    @Override
    public void handleSelection(PlayerData data) {
        Scanner inputs = new Scanner(System.in);
        ArrayList<String> cards = new ArrayList<String>();
        String selection = "";
        cards.addAll(data.cardsFilter());
        for (int i = 0; i < cards.size(); i++) {
            System.out.println("[" + (i + 1) + "]" + "" + cards.get(i));
        }
        selection = inputs.nextLine();
        int index = Integer.parseInt(selection);
        Card card = new Card();
        ArrayList<Resource> needed = new ArrayList<Resource>();
        ArrayList<MappedResource> mappedRes = new ArrayList<MappedResource>();
        card = data.fromIDtoCard(cards.get(index));
        mappedRes = data.createMappedRes(needed.addAll(card.required));
        for(int j = 0; j < mappedRes.size(); j++){
            System.out.println("[" + (j + 1) + "]" + "" + mappedRes.get(j));
        }
        String mappedSelect = "";
        do{
            //scegli la mappedSelect ogni volta
            //lui te le rimuove mano a mano dal PlayerData nei punti stabiliti
            //ti fa la printLn delle risorse a mano a mano
            //ti compone l'array finale delle mappedres
    }while(r != '0')
        //poi ti aggiunge l'id della carta + le mappedres ad un arraylist di MappedProduce


    @Override
    public void sendToConnection() {

    }


}
