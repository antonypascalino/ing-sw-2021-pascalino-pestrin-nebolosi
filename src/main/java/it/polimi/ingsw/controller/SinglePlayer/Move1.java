package it.polimi.ingsw.controller.SinglePlayer;

import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.Board.FaithPath;

import java.util.Collections;

/**
 * That type of {@link Token} that, if drawn, makes Lorenzo move forward of one step on his {@link FaithPath}.
 */
public class Move1 implements Token {

    @Override
    public void activateEffect(SinglePlayerGame game) {
        game.getLorenzoPath().moveForward(2);
        if (game.getLorenzoPath().checkPopeSpace(3)) {
            game.lorenzoWins();
        }
        Collections.shuffle(game.getTokenList());
        game.fpAdvancement(0,0);
    }

    @Override
    public String announceAction(SinglePlayerGame game) {
        return ("Lorenzo moved of 1 step on his Faith Path");
    }

    public String toString(){
        return (this.getClass().getName());
    }
}
