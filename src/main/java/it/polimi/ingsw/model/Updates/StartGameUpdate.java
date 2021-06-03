package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.view.data.PlayerData;

public class StartGameUpdate implements Update {

    private String playerID;

    public StartGameUpdate(String playerID) {
        this.playerID = playerID;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        if(data.getPlayerID().equals(playerID)) {
            data.getMenu().menuMaker();
        }
    }
}
