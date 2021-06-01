package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.view.Updater;
import it.polimi.ingsw.view.data.PlayerData;

public interface Update {

    public void handleUpdate(PlayerData data);

}