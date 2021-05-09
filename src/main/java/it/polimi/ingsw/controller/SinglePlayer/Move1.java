package it.polimi.ingsw.controller.SinglePlayer;

import java.util.Collections;

public class Move1 implements Token {

    @Override
    public void activateEffect(SinglePlayerGame game) {
        game.getLorenzoPath().moveForward(2);
        Collections.shuffle(game.getTokenList());
        game.getLorenzoPath().checkPopeSpace(game.get)
    }
}
