package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.view.data.PlayerData;

/**
 * The {@link Update} sent when a {@link Player} tries to do something he can't do for a reason.
 * Based on the reason of the error, the update brings with him a different message.
 */
public class ErrorUpdate implements Update {
    private String error; //error description
    private String playerID;

    private final String className;


    /**
     * Instantiates a new {@link ErrorUpdate} setting the message which describes the error and the {@link Player}'s
     * ID who caused the error.
     *
     * @param error    the error message.
     * @param playerID the {@link Player}'s ID who caused the error.
     */
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
