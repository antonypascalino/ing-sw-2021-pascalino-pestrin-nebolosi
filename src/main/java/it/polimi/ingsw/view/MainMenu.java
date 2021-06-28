package it.polimi.ingsw.view;

import it.polimi.ingsw.client.LineClient;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.view.data.PlayerData;
import it.polimi.ingsw.view.selections.*;

import java.util.ArrayList;

/**
 * Every player has his own reference to an object of this type. It, at the beginning of every player's turn, show him
 * the possible actions he can do, the player chooses one of them and, once the player complete the action, the
 * menu reappears, showing other actions based on the previous actions done by the player. The cycle goes on until
 * the player wants to end his turn.
 */
public class MainMenu {

    private ArrayList<TurnState> turnStates;
    private PlayerData data;
    private Printer printer;

    /**
     * Instantiates a new {@link MainMenu}.
     *
     * @param data the player owner of the new object.
     */
    public MainMenu(PlayerData data) {
        this.printer = data.getPrinter();
        this.turnStates = new ArrayList<>();
        this.data = data;
    }

    public void setData(PlayerData newData) {
        this.data = newData;
    }

    /**
     * Show the player all the action he can do, based on the action already done or not by him. This is done checking the
     * {@link TurnState} list received by turnStateFilter() method in the {@link PlayerData}.
     */
    public void menuMaker() {
        TurnState turnState;
        turnStates.clear();
        turnStates.addAll(data.turnStateFilter());
        turnState = printer.printTurnStates(turnStates);
        selectionHandler(turnState, data);
    }

    /**
     * Once the player chose the action he wants to do, this method create a new {@link Selection} based on the
     * player's choice.
     *
     * @param state the action chose by the player.
     * @param data  the reference to the {@link PlayerData}.
     */
    private void selectionHandler(TurnState state, PlayerData data) {
        Selection selection;

        switch (state) {
            case PRODUCE:
                selection = new ProductionSelection();
                selection.handleSelection(data);
                break;
            case BUY_DEV_CARD:
                selection = new BuyDevSelection();
                selection.handleSelection(data);
                break;
            case GET_FROM_MARKET:
                selection = new MarketSelection();
                selection.handleSelection(data);
                break;
            case MOVE_RESOURCE:
                selection = new MoveSelection();
                selection.handleSelection(data);
                break;
            case PLAY_LEADER_CARD:
                selection = new PlayLeaderSelection();
                selection.handleSelection(data);
                break;
            case DISCARD_LEADER_CARD:
                selection = new DiscardLeaderSelection();
                selection.handleSelection(data);
                break;
            case CHECK_STATS:
                selection = new CheckStatsSelection();
                selection.handleSelection(data);
                break;
            case END_TURN:
                selection = new EndTurnSelection();
                selection.handleSelection(data);
                break;
        }
    }
}





