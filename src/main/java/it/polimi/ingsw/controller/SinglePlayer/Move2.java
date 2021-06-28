package it.polimi.ingsw.controller.SinglePlayer;

import it.polimi.ingsw.model.Board.FaithPath;

/**
 * That type of {@link Token} that, if drawn, makes Lorenzo move forward of two step on his {@link FaithPath} and shuffle the stack of tokens in the game;
 */
public class Move2 implements Token {

    @Override
    public void activateEffect(SinglePlayerGame game) {
        game.getLorenzoPath().moveForward(2);
        if (game.getLorenzoPath().checkPopeSpace(3)) {
            game.lorenzoWins();
        }
        game.fpAdvancement(0, 0);
    }

    public String toString() {
        return (this.getClass().getName());
    }

    @Override
    public String announceAction(SinglePlayerGame game) {
        return ("Lorenzo moved of 2 step on his Faith Path");
    }
}
