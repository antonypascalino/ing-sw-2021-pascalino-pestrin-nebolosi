package it.polimi.ingsw.controller.SinglePlayer;

import it.polimi.ingsw.model.card.DevCard;

import java.util.ArrayList;
import java.util.Arrays;

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

    @Override
    public String announceAction(SinglePlayerGame game) {
        return ("\nLorenzo bought 2 " + color + " development cards!\nNow remain:\n" + game.getTable().getStack()[3][0] + " PURPLE cards of level 1, " + game.getTable().getStack()[3][1] + " PURPLE cards of level 2 and " + game.getTable().getStack()[3][2] + " PURPLE cards of level 3;\n" +
                +game.getTable().getStack()[2][0] + " YELLOW cards of level 1, " + game.getTable().getStack()[2][1] + " YELLOW cards of level 2 and " + game.getTable().getStack()[2][2] + " YELLOW cards of level 3;\n" +
                +game.getTable().getStack()[1][0] + " BLUE   cards of level 1, " + game.getTable().getStack()[1][1] + " BLUE   cards of level 2 and " + game.getTable().getStack()[1][2] + " BLUE   cards of level 3;\n" +
                +game.getTable().getStack()[0][0] + " GREEN  cards of level 1, " + game.getTable().getStack()[0][1] + " GREEN  cards of level 2 and " + game.getTable().getStack()[0][2] + " GREEN  cards of level 3;\n");
    }
}

