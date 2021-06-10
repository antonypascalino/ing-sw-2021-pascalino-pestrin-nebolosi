package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.view.data.PlayerData;

public class SPEndgameUpdate implements Update {
    private boolean lorenzoWins;
    private final String className;
    private int victoryPoints;

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
