package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.view.data.PlayerData;

public class PlayLeaderUpdate implements Update {

    private final String className;

    public PlayLeaderUpdate() {
        className = this.getClass().getName();
    }

    @Override
    public String getClassName() {
        return className;
    }
    @Override
    public void handleUpdate(PlayerData data) {

    }
}
