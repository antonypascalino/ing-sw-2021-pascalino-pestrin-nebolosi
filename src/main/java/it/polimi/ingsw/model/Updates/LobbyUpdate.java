package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.view.data.PlayerData;

public class LobbyUpdate implements Update {
    private final String className;
    //Max number of players
    private int playerNum;
    private int maxPlayer;
    private String nickname;
    public LobbyUpdate(String nickName, int playerNum, int maxPlayers){
        className = this.getClass().getName();
        this.nickname = nickName;
        this.maxPlayer = maxPlayers;
        this.playerNum = playerNum;
    }
    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        data.getPrinter().printMessage("New game created wwith "+ maxPlayer+ " max players\n\rActually there are "+playerNum+" players in the lobby\n\rThe last one to join was "+nickname);

    }
}
