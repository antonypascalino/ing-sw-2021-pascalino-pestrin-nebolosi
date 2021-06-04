package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.view.data.PlayerData;

public class StartGameUpdate implements Update {

    private String playerID;
    private final String className;


    public StartGameUpdate(String playerID) {

        className = this.getClass().getName();
        this.playerID = playerID;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        if(data.getPlayerID().equals(playerID)) {
            data.getMenu().menuMaker();
        }
    }

    @Override
    public String getClassName() {
        return className;
    }
}
