package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.view.data.PlayerData;

public class ErrorUpdate implements Update {
    private String error; //una descrizione verbale dell'errore
    private String playerID;

    public ErrorUpdate(String error, String playerID) {
        this.error = error;
        this.playerID = playerID;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        if (playerID.equals(data.getPlayerID())) {
            data.getPrinter().printMessage(error);
        }
    }
}
