package it.polimi.ingsw.controller.SinglePlayer;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Board.FaithPath;
import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Table;

import java.util.ArrayList;
import java.util.Collections;

public class SinglePlayerGame extends Game {
    private FaithPath lorenzoPath;
    private ArrayList<Token> tokenList;
    private Player player;

    public SinglePlayerGame() {
        player = new BasicPlayer();
        lorenzoPath = new FaithPath();
        tokenList = new ArrayList<>();
        Move1 move1 = new Move1();
        Move2 move2 = new Move2();
        DiscardCards discardCardPurple = new DiscardCards("PURPLE)";
        DiscardCards discardCardYellow = new DiscardCards("YELLOW");
        DiscardCards discardCardGreen = new DiscardCards("GREEN");
        DiscardCards discardCardBlue = new DiscardCards("BLUE");
        tokenList.add(move1);
        tokenList.add(move2);
        tokenList.add(discardCardPurple);
        tokenList.add(discardCardYellow);
        tokenList.add(discardCardGreen);
        tokenList.add(discardCardBlue);
        Collections.shuffle(tokenList);
    }

    public void changePlayer(Player newPlayer) {
        player = newPlayer;
    }

    public FaithPath getLorenzoPath() {
        return lorenzoPath;
    }

    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

    private Token drawToken() {
        Token result = tokenList.remove(0);
        tokenList.add(result);
        return result;
    }
}
