package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.view.data.PlayerData;

public class lastTurnUpdate implements Update {
    private String lastTurnAnnouncement;
    private String className;

    public lastTurnUpdate(String lastTurnAnnouncement) {
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
