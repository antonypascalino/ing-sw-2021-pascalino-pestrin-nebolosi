package it.polimi.ingsw.Updates;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.view.data.PlayerData;
import it.polimi.ingsw.controller.Game;

/**
 * The {@link Update} sent when a {@link Game} starts to announce its start.
 */
public class StartGameUpdate implements Update {

    private String playerID;
    private final String className;


    /**
     * Instantiates a new {@link StartGameUpdate} setting the {@link Player}'s nickname who starts.
     *
     * @param playerID the {@link Player}'s ID who starts.
     */
    public StartGameUpdate(String playerID) {

        className = this.getClass().getName();
        this.playerID = playerID;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        data.getPrinter().printMessage("The game has started and the first one to play is "+playerID);
        if(data.getPlayerID().equals(playerID))
        {
            data.getMenu().menuMaker();
        }
    }

    @Override
    public String getClassName() {
        return className;
    }
}
