package it.polimi.ingsw.Updates;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.view.data.PlayerData;

/**
 * The {@link Update} sent after that a {@link Player} reached the conditions to make start the last turn of the {@link Game}.
 */
public class LastTurnUpdate implements Update {
    private String lastTurnAnnouncement;
    private String className;

    /**
     * Instantiates a new {@link LastTurnUpdate} setting the message to show.
     *
     * @param lastTurnAnnouncement the last turn announcement
     */
    public LastTurnUpdate(String lastTurnAnnouncement) {
        this.lastTurnAnnouncement = lastTurnAnnouncement;
        this.className = this.getClass().getName();
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        data.getPrinter().printMessage(lastTurnAnnouncement);
    }
}
