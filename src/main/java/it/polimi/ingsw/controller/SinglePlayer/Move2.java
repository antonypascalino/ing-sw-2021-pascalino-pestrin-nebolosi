package it.polimi.ingsw.controller.SinglePlayer;

public class Move2 implements Token {

    @Override
    public void activateEffect(SinglePlayerGame game) {
        game.getLorenzoPath().moveForward(2);
        if (game.getLorenzoPath().checkPopeSpace(3)) {
            game.lorenzoWins();
        }
        game.fpAdvancement(0,0);
    }
}
