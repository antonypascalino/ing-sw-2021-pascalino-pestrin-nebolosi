package it.polimi.ingsw.view;


import it.polimi.ingsw.model.Updates.Update;
import it.polimi.ingsw.view.data.PlayerData;


public class Updater {

    private PlayerData playerData;

    public Updater(PlayerData playerData) {
        this.playerData = playerData;
    }

    public void refresh(Update update) {
        update.handleUpdate(playerData);
    }
}
