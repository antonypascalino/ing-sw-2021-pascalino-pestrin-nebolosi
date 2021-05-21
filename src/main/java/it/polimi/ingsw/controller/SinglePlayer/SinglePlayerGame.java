package it.polimi.ingsw.controller.SinglePlayer;

import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Board.FaithPath;
import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Table;

import java.util.ArrayList;
import java.util.Collections;

public class SinglePlayerGame {
    private FaithPath lorenzoPath;
    private ArrayList<Token> tokenList;
    private Player player;
    private Table table;
    private int currPopeSpace;
    private ArrayList<TurnState> turnStates;

    public SinglePlayerGame(String nickname) {
        table = new Table(DefaultCreator.produceDevCard());
        player = new BasicPlayer(nickname, table);
        lorenzoPath = new FaithPath();
        tokenList = new ArrayList<>();
        createTokens();
    }

    private void createTokens() {
        Move1 move1 = new Move1();
        Move2 move2 = new Move2();
        DiscardCards discardCardPurple = new DiscardCards("PURPLE");
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

    public Table getTable() {
        return table;
    }

    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

    private Token drawToken() {
        Token result = tokenList.remove(0);
        tokenList.add(result);
        return result;
    }

    public void notify(Request req) {
        int playerSteps = 0;
        int discardedSteps = 0;

        if (req.validRequest(turnStates)) {
            if (req.canBePlayed(player)) {
                turnStates.add(req.nextTurnState());
                req.handle(player);
                if (req.getDiscardedSteps() != 0 || req.getMyFPSteps() != 0) {
                    fpAdvancement(discardedSteps, playerSteps);
                }
                if (turnStates.contains(TurnState.END_TURN)) {
                    turnStates.clear();
                }
                if (req.getPlayerChoices() != 0) {
                    //chiama model che chiama view che fa scegliere al player le risorse
                }
            }
            drawToken().activateEffect(this);
        }
        if (player.getBoard().getSlot().getAllCards().size() == 7 || player.getBoard().getFaithPath().checkPopeSpace(3)) {
            playerWins();
        }
    }

    public void fpAdvancement(int discardedSteps, int playerSteps) {
        //Sposta Lorenzo per un numero di passi uguale alle risorse scartate dal giocatore
        if (discardedSteps != 0) {
            lorenzoPath.moveForward(discardedSteps);
        }
        //Sposta il player per i FAITH ottenuti nel suo turno
        if (playerSteps != 0) {
            player.getBoard().getFaithPath().moveForward(playerSteps);
        }
        //Controlla se, a seguito dei movimenti del player e/o di Lorenzo, qualcuno ha raggiunto la popeSpace corrente
        // in caso affermativo chiama la checkVaticanSection del player per vedere se assegnargli o meno i punti
        if (player.getBoard().getFaithPath().checkPopeSpace(currPopeSpace) || lorenzoPath.checkPopeSpace(currPopeSpace)) {
            player.getBoard().getFaithPath().checkVaticanSection(currPopeSpace);
            currPopeSpace++;
            this.fpAdvancement(0,0); //Richiama se stessa per verificare se qualche giocatore abbia superato più di una popeSpace
        }
    }

    public void lorenzoWins() {
        //Termina la partita e comunica al giocatore che ha perso
    }
    public void playerWins() {
        //Termina la partita e comunica al giocatore che ha vinto e il suo punteggio
        player.addVictoryPoints((int)player.getAllResources().size()/5);
        player.getVictoryPoints();
    }

}
