package it.polimi.ingsw.Updates;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The {@link Update} sent when the {@link Game} ends.
 */
public class EndgameUpdate implements Update {

    private String winner;
    private ArrayList<PlayerVP> finalPlayersVPS;
    private final String className;

    /**
     * Instantiates a new {@link EndgameUpdate} setting the winner's nickname and every {@link Player}'s victory points.
     *
     * @param winner          the winner's nickname.
     * @param finalPlayersVPS the players' nicknames and their victory points at the end of the game.
     */
    public EndgameUpdate(String winner, ArrayList<PlayerVP> finalPlayersVPS) {
        this.finalPlayersVPS = finalPlayersVPS;
        className = this.getClass().getName();
        this.winner = winner;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        data.getPrinter().endgame(this);
    }

    /**
     * Gets winner's nickname.
     *
     * @return the winner's nickname.
     */
    public String getWinner() {
        return winner;
    }

    /**
     * Gets the players' nicknames and their victory points at the end of the game.
     *
     * @return the ArrayList with all players and their victory points.
     */
    public ArrayList<PlayerVP> getFinalPlayersVPS() {
        return finalPlayersVPS;
    }

    @Override
    public String getClassName() {
        return className;
    }
}
