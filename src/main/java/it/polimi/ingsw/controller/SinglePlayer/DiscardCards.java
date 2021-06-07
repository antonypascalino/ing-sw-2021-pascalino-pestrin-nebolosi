package it.polimi.ingsw.controller.SinglePlayer;

public class DiscardCards implements Token {
    private String color;

    public DiscardCards(String color) {
        this.color = color;
    }

    @Override
    public void activateEffect(SinglePlayerGame game) {
        //Has to remove 2 cards
        for (int i = 0; i < 2; i++) {
            //Try to remove first the lower level cards
            for (int level = 1; level <= 3; level++) {
                if (game.getTable().buyDev(color, level) != null) {
                    break;
                }
            }
        }
        //Check if the are no more cards of the selected color
        for(int level = 1; level <= 3; level++) {
            if (game.getTable().seeDev(color, level) != null) {
                return;
            }
        }
        game.lorenzoWins();
    }

    public String toString(){
        return (this.getClass().getName());
    }
}

