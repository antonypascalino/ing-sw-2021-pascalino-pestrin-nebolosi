package it.polimi.ingsw.Updates;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.view.data.PlayerData;


/**
 * The {@link Update} sent after that a {@link Player} ends his turn.
 */
public class EndTurnUpdate implements Update {
    private String nextPlayer;
    private final String className;


    /**
     * Instantiates a new {@link EndTurnUpdate} setting next {@link Player}'s nickname.
     *
     * @param nextPlayer the next player
     */
    public EndTurnUpdate(String nextPlayer) {
        this.nextPlayer = nextPlayer;
        className = this.getClass().getName();
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        if (data.getPlayerID().equals(nextPlayer)) {
            data.newTurn();
            data.getPrinter().printMessage("\nNow it's your turn!");
            data.getMenu().menuMaker();
        } else {
            data.getPrinter().printMessage("\nIt's " + nextPlayer + "'s turn!");
        }
    }
}
