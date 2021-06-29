package it.polimi.ingsw.Updates;

import it.polimi.ingsw.view.data.PlayerData;

public class ConnectionErrorUpdate implements Update {
    public final String className;
    private String nickName;

    public ConnectionErrorUpdate(String nickname) {
        this.className = this.getClass().getName();
        this.nickName = nickname;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        data.getPrinter().printMessage("The game ended because player " + nickName + " crashed");
        System.exit(0);
    }

    @Override
    public String getClassName() {
        return className;
    }
}
