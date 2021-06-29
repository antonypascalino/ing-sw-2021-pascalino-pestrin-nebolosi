package it.polimi.ingsw.controller.SinglePlayer;

import it.polimi.ingsw.Request.InitialPlayersSetRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Board.FaithPath;
import it.polimi.ingsw.Updates.*;
import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Table;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The single player game in which a single player will challenge Lorenzo De Medici.
 */
public class SinglePlayerGame extends Game {
    private FaithPath lorenzoPath;
    private ArrayList<Token> tokenList;
    private Player player;
    private Token lastToken;

    /**
     * Instantiates a new Single player game.
     *
     * @param players the list of players which, in this case, will contain only one player.
     * @param cards   the {@link DevCard}s of the game.
     * @param gameId  the ID on the server of this particular game.
     */
    public SinglePlayerGame(ArrayList<Player> players, ArrayList<DevCard> cards, int gameId) {
        super(players, cards, gameId, 1);
        player = players.get(0);
        table = new Table(DefaultCreator.produceDevCard());
        lorenzoPath = new FaithPath();
        tokenList = new ArrayList<>();
        createTokens();
        turnStates = new ArrayList<>();
    }

    /**
     * Create all the {@link Token}s of the {@link SinglePlayerGame}, shuffles them and create the stack of this game.
     */
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

    @Override
    public void changePlayer(Player original, Player newPlayer) {
        player = newPlayer;
    }

    /**
     * Gets Lorenzo path.
     *
     * @return the Lorenzo's path
     */
    public FaithPath getLorenzoPath() {
        return lorenzoPath;
    }

    @Override
    public Table getTable() {
        return table;
    }

    /**
     * Gets the {@link Token}s stack.
     *
     * @return the token stack,
     */
    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

    /**
     * Draw a {@link Token} from the top of the stack and place it to the end.
     *
     * @return the {@link Token} just drawn.
     */
    public Token drawToken() {
        Token result = tokenList.remove(0);
        tokenList.add(result);
        return result;
    }

    @Override
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

    /**
     * Create an {@link Update} in which are contained all the modifies Lorenzo did during his turn.
     *
     * @return the {@link Update} just created.
     */
    private Update createLorenzoUpdate() {
        return new LorenzoUpdate(lorenzoPath.getAdvancement(), player.getVictoryPoints(), table.getFrontIDs(), lastToken.announceAction(this), turnStates);
    }

    @Override
    public void fpAdvancement(int discardedSteps, int playerSteps) {
        //Lorenzo moves the same amount of discarded resources
        if (discardedSteps != 0) {
            lorenzoPath.moveForward(discardedSteps);
        }
        //The player moves as much as faith points are gained in their turn
        if (playerSteps != 0) {
            player.getBoard().getFaithPath().moveForward(playerSteps);
        }
        //Checks if either Lorenzo or the player reached the Pope Section
        // if so, CheckVaticanSection is called to assign the respective points
        if (player.getBoard().getFaithPath().checkPopeSpace(currPopeSpace) || lorenzoPath.checkPopeSpace(currPopeSpace)) {
            player.getBoard().getFaithPath().checkVaticanSection(currPopeSpace);
            currPopeSpace++;
            this.fpAdvancement(0, 0); //Recursive call to check another pope space
        }
    }

    /**
     * In the case of Lorenzo win the game and the {@link Player} loses create an {@link Update} in which are contained all the game's results and send it to the player.
     */
    public void lorenzoWins() {
        ArrayList<PlayerVP> playersVP = new ArrayList<>();
        playersVP.add(new PlayerVP(player.getNickName(), player.getVictoryPoints()));
        notifyAllPlayers(new SPEndgameUpdate(true, player.getVictoryPoints()));
    }

    /**
     * In the case of the Player win the game and the Lorenzo loses create an {@link Update} in which are contained all the game's results and send it to the player..
     */
    public void playerWins() {
        //Ends the match and tells the player who won
        player.addVictoryPoints(player.getAllResources().size() / 5);
        player.getVictoryPoints();
        ArrayList<PlayerVP> playersVP = new ArrayList<>();
        playersVP.add(new PlayerVP(player.getNickName(), player.getVictoryPoints()));
        notifyAllPlayers(new SPEndgameUpdate(false, player.getVictoryPoints()));
    }
}
