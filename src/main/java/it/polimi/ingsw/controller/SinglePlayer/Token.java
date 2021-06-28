package it.polimi.ingsw.controller.SinglePlayer;

import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.Table.Table;

/**
 * This object is used on a {@link SinglePlayerGame}. A token is drawn every Lorenzo's turn and it bring changes to the game
 * such for example remove {@link DevCard} from the {@link Table} or make Lorenzo move forward on his Faith Path
 */
public interface Token {

    /**
     * Activate the effect of the specific {@link Token}.
     *
     * @param game the game
     */
    void activateEffect(SinglePlayerGame game);

    /**
     * Create a string in which is explained the action just done by the token that is been drawn and Lorenzo's status.
     *
     * @param game the {@link SinglePlayerGame} relative of this {@link Token}
     * @return the string explaining what Lorenzo did on his turn.
     */
    String announceAction(SinglePlayerGame game);
}
