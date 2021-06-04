package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.Convertible;
import it.polimi.ingsw.view.data.PlayerData;

public interface Update extends Convertible {

    public void handleUpdate(PlayerData data);

}