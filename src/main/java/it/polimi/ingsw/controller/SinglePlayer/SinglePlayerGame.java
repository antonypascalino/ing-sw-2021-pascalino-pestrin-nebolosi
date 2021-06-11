package it.polimi.ingsw.controller.SinglePlayer;

import it.polimi.ingsw.Request.InitialPlayersSetRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Board.FaithPath;
import it.polimi.ingsw.model.Updates.*;
import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Table;
import it.polimi.ingsw.model.card.LeaderCard;

import java.util.ArrayList;
import java.util.Collections;

public class SinglePlayerGame extends Game {
    private FaithPath lorenzoPath;
    private ArrayList<Token> tokenList;
    private Player player;
    private Token lastToken;

    public SinglePlayerGame(ArrayList<Player> players, ArrayList<DevCard> cards, int gameId) {
        super(players, cards, gameId, 1);
        player = players.get(0);
        table = new Table(DefaultCreator.produceDevCard());
        lorenzoPath = new FaithPath();
        tokenList = new ArrayList<>();
        createTokens();
        turnStates = new ArrayList<>();
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

    public void changePlayer(Player original, Player newPlayer)
    {
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

    public Token drawToken() {
        Token result = tokenList.remove(0);
        tokenList.add(result);
        return result;
    }

//    public Update createNewGameUpdate() {
//        ArrayList<LeaderCard> allLeaderCards = new ArrayList<LeaderCard>();
//        allLeaderCards.addAll(DefaultCreator.produceLeaderCard()); //Produce tutte le Leader del gioco
//        Collections.shuffle(allLeaderCards); //Le mischia
//
//        //Crea un elenco di players e attibuisce ad ognungo di loro 4 leaderCard diverse
//        ArrayList<PlayerLC> playersLC = new ArrayList<PlayerLC>();
//
//        player.setTable(table);
//        ArrayList<String> leadersToChoose = new ArrayList<String>();
//        for (int addedCard = 0; addedCard < 4; addedCard++) {
//            leadersToChoose.add(allLeaderCards.remove(0).getID());
//        }
//        playersLC.add(new PlayerLC(player.getNickName(), leadersToChoose));
//
//
//        ArrayList<PlayerST> playersST = new ArrayList<>();
//
//        PlayerST player1 = new PlayerST(player.getNickName(), 0, 0);
//        playersST.add(player1);
//
//        return new NewGameUpdate(getGameId(), table.getFrontIDs(), table.market.getMarket(), playersLC, playersST);
//    }

    public void notify(Request req) {
        if (req.validRequest(turnStates)) {
            if (req.canBePlayed(player)) {
                turnStates.add(req.handle(player, this));
                if (turnStates.contains(TurnState.END_TURN)) {
                    lastToken = drawToken();
                    lastToken.activateEffect(this);
                    turnStates.clear();
                    notifyAllPlayers(createLorenzoUpdate());
                }
                if (!(req instanceof InitialPlayersSetRequest)) {
                    notifyAllPlayers(req.createUpdate(player, this));
                }
                if (player.getBoard().getSlot().getAllCards().size() == 7 || player.getBoard().getFaithPath().checkPopeSpace(3)) {
                    playerWins();
                }
            } else {
                Update error = new ErrorUpdate("You can't do that!", req.getPlayerID());
                notifyAllPlayers(error);
            }
        } else {
            Update error = new ErrorUpdate("You can't do this action in this moment!", req.getPlayerID());
            notifyAllPlayers(error);
        }
    }

    private Update createLorenzoUpdate() {
        return new LorenzoUpdate(lorenzoPath.getAdvancement(), player.getVictoryPoints(), table.getFrontIDs(), lastToken.announceAction(this), turnStates);
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
            this.fpAdvancement(0, 0); //Richiama se stessa per verificare se qualche giocatore abbia superato più di una popeSpace
        }
    }

    public void lorenzoWins() {
        ArrayList<PlayerVP> playersVP = new ArrayList<>();
        playersVP.add(new PlayerVP(player.getNickName(), player.getVictoryPoints()));
        notifyAllPlayers(new SPEndgameUpdate(true, player.getVictoryPoints()));
    }

    public void playerWins() {
        //Termina la partita e comunica al giocatore che ha vinto e il suo punteggio
        player.addVictoryPoints((int) player.getAllResources().size() / 5);
        player.getVictoryPoints();
        ArrayList<PlayerVP> playersVP = new ArrayList<>();
        playersVP.add(new PlayerVP(player.getNickName(), player.getVictoryPoints()));
        notifyAllPlayers(new SPEndgameUpdate(false, player.getVictoryPoints()));
    }
}
