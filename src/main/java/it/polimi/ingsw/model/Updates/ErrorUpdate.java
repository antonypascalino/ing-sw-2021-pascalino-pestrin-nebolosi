package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.view.data.PlayerData;

public class ErrorUpdate implements Update {
    private String error; //una descrizione verbale dell'errore
    private String playerID;

    private final String className;


    public ErrorUpdate(String error, String playerID) {
        this.error = error;
        this.playerID = playerID;
        className = this.getClass().getName();
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        if (playerID.equals(data.getPlayerID())) {
            data.getPrinter().printMessage(error);
            data.getMenu().menuMaker();
        }
    }
}
