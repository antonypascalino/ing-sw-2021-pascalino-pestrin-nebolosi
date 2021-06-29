package it.polimi.ingsw.Updates;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.controller.SinglePlayer.SinglePlayerGame;
import it.polimi.ingsw.view.data.PlayerData;

/**
 * The {@link Update} sent when a {@link SinglePlayerGame} ends. It brings a different message based on
 * the winner: Lorenzo or the {@link Player}.
 */
public class SPEndgameUpdate implements Update {
    private boolean lorenzoWins;
    private final String className;
    private int victoryPoints;

    /**
     * Instantiates a new {@link SPEndgameUpdate} setting the message and information about who won the {@link SinglePlayerGame}.
     *
     * @param lorenzoWins   true if Lorenzo won, false if the {@link Player} did.
     * @param victoryPoints the {@link Player}'s victory points.
     */
    public SPEndgameUpdate(boolean lorenzoWins, int victoryPoints) {
        this.lorenzoWins = lorenzoWins;
        className = this.getClass().getName();
        this.victoryPoints = victoryPoints;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        if (lorenzoWins) data.getPrinter().printMessage("Lorenzo won the game! You lost!");
        else data.getPrinter().printMessage("YOU WON!\nHere's your score: " + victoryPoints + " victory points");
    }
}
