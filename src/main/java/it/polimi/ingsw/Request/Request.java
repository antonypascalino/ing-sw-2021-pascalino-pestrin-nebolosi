package it.polimi.ingsw.Request;

import it.polimi.ingsw.Convertable;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

/**
 * The interface used by all type of request the player could send
 */
public interface Request extends Convertable {
    /**
     * Activate the request calling the right methods in the controller classes
     */

    //The player trying to request
    Player player = null;

    /**
     * Compie l'azione indicata dalla Request e restituisce il TurnState appena cffetettuato
     */
    public void handle(Player player);

    /**
     *
     * @return true se l'azione che vuole compiere la request è compatibile con il TurnState corrente e il giocatore corrente
     */
    public boolean validRequest(ArrayList<TurnState> turnStates);


    /**
     * Controlla che il giocatore abbaia tutti i requisiti per compiere la request.
     * Calcola la nuova posizione su cui il giocatore si troverà sul FaithPath
     *
     * @param player
     *
     * @return int la posizione su cui si treoverà il giocatore sul FaithPath
     */
    public boolean canBePlayed(Player player);

    public TurnState nextTurnState();

    public int getMyFPSteps();

    public int getDiscardedSteps();


}
