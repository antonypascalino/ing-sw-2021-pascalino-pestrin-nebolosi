package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.view.data.PlayerData;

/**
 * The {@link Update} sent before that the {@link Game} starts.
 */
public class LobbyUpdate implements Update {
    private final String className;
    //Max number of players
    private int playerNum;
    private int maxPlayer;
    private String nickname;

    /**
     * Instantiates a new {@link LobbyUpdate} setting the max numbers of the {@link Player}s in the {@link Game}
     * and the nickname of the last player who joined the game.
     *
     * @param nickName   the nick name
     * @param playerNum  the player num
     * @param maxPlayers the max players
     */
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
        data.getPrinter().printMessage("New game created with "+ maxPlayer+ " max players\n\rActually there are "+playerNum+" players in the lobby\n\rThe last one to join was "+nickname);

    }
}
