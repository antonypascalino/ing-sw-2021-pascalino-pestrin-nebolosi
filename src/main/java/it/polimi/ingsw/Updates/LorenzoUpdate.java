package it.polimi.ingsw.Updates;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.controller.SinglePlayer.SinglePlayerGame;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The {@link Update} sent after every Lorenzo's action in a {@link SinglePlayerGame}.
 */
public class LorenzoUpdate implements Update {
    private String className;
    private int lorenzoFaithPoints;
    private int playersVP;
    private ArrayList<String> frontTableCards;
    private String playerID;
    private String actionDone;
    private ArrayList<TurnState> turnStates;

    /**
     * Instantiates a new {@link LorenzoUpdate} setting everything can change with this actions: the Lorenzo's victory points,
     * {@link Player}'s victory points and the development card on the table. Set even a message about what Lorenzo actually did.
     *
     * @param lorenzoFaithPoints the lorenzo faith points
     * @param playersVP          the players vp
     * @param frontTableCards    the front table cards
     * @param actionDone         the action done
     * @param turnStates         the turn states
     */
    public LorenzoUpdate(int lorenzoFaithPoints, int playersVP, ArrayList<String> frontTableCards, String actionDone, ArrayList<TurnState> turnStates) {
        className = this.getClass().getName();
        this.lorenzoFaithPoints = lorenzoFaithPoints;
        this.playersVP = playersVP;
        this.frontTableCards = frontTableCards;
        playerID = "Lorenzo";
        this.actionDone = actionDone;
        this.turnStates = turnStates;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        data.setVictoryPoints(playersVP);
        data.setFrontTableCardsID(frontTableCards);
        data.getPrinter().printMessage(actionDone);
        data.getPrinter().printMessage("Lorenzo's advancement on his Faith Path is now " + lorenzoFaithPoints);

    }

    @Override
    public String getClassName() {
        return null;
    }
}
