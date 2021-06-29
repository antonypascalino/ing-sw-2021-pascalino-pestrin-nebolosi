package it.polimi.ingsw.Updates;

import it.polimi.ingsw.view.data.PlayerData;

/**
 * Update used for checking the status of the client
 */
public class CheckUpdate implements Update {
    private final String className;

    public CheckUpdate() {
        this.className = this.getClass().getName();
    }

    @Override
    public void handleUpdate(PlayerData data) {
        //System.out.println("I'm still connected");
        //data.sendRequest(new PongRequest());
    }

    @Override
    public String getClassName() {
        return className;
    }
}
