package it.polimi.ingsw.view;

import it.polimi.ingsw.view.data.PlayerData;
import it.polimi.ingsw.view.selections.Selection;

public class MainView {
    public static void main(String[] args){
        PlayerData data = null;
        Selection selection = null;
        MainMenu menu = null;
        menu.menuMaker(data);
    }
}
