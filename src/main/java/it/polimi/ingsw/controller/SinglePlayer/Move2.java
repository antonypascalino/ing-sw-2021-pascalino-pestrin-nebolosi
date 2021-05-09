package it.polimi.ingsw.controller.SinglePlayer;

public class Move2 implements Token {

    @Override
    public void activateEffect(SinglePlayerGame game) {
        game.getLorenzoPath().moveForward(2);
    }
}
