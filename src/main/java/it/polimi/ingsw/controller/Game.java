package it.polimi.ingsw.controller;

import it.polimi.ingsw.Request.InitialPlayersSetRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.model.Board.FaithPath;
import it.polimi.ingsw.model.Updates.*;
import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Table;
import it.polimi.ingsw.model.card.LeaderCard;

import java.util.ArrayList;
import java.util.Collections;

/**
 * An object {@link Game} is created every time some players start a game. It contains the references to all the {@link Player}s
 * and the information about the game in progress, such for example the {@link DevCard}s available on the {@link Table}
 * or the actions done by the players during their turns.
 * <p>
 * It also receive the information from the connection containing what a player wants to do and, in case it is possible,
 * handle them modifying the players' status on the server.
 */
public class Game {
    public int maxPlayer;
    private ArrayList<Player> players;
    private int currPlayerInt;
    protected Table table;
    protected ArrayList<TurnState> turnStates;
    private Player currPlayer;
    private Player nextPlayer;
    protected int currPopeSpace;
    private final int gameId;
    private boolean lastTurn;
    public int playerReady; //Players ready to start the game that have chosen their leaderCards

    /**
     * Instantiates a new {@link Game} saving all the information about it, such for example the reference to the {@link Player}s,
     * and handing out all the necessary for the game like the table's development card or the market's resources.
     *
     * @param players   the players joined to this game.
     * @param cards     the development cards to place on the {@link Table}.
     * @param gameId    the ID on the server of this particular game.
     * @param maxPlayer the number of {@link Player}s in this game.
     */
    public Game(ArrayList<Player> players, ArrayList<DevCard> cards, int gameId, int maxPlayer) {
        playerReady = 0;
        currPlayerInt = 0;
        currPlayer = players.get(currPlayerInt);
        this.maxPlayer = maxPlayer;
        this.gameId = gameId;
        this.players = players;
        Collections.shuffle(cards);
        this.table = new Table(cards);
        this.turnStates = new ArrayList<>();
        this.currPlayer = players.get(0);
        this.currPopeSpace = 1;
    }

    /**
     * Gets the GameID.
     *
     * @return the GameID.
     */
    public int getGameId()
    {
        return gameId;
    }

    /**
     * When a new {@link Player} with a new abilities gets created, replace him, in the
     * {@link Game}'s list of players, in the same position of the original one.
     *
     * @param original  the {@link Player} that is going to be substituted.
     * @param newPlayer the new {@link Player}'s reference.
     */
    public void changePlayer(Player original, Player newPlayer)
    {
        int pos = players.indexOf(original);
        players.set(pos, newPlayer);
        if(original.getNickName().equals(currPlayer.getNickName()))
            currPlayer = newPlayer;
    }

    /**
     * Gets {@link Table}.
     *
     * @return the {@link Table}
     */
    public Table getTable()
    {
        return table;
    }

    /**
     * Receives from the {@link Player}, client side, a {@link Request} containing what the player would like to do.
     * For each of them controls: if the player who send the request is the current player on the game,
     * if the player can do those actions in this moment if the player can do those action according to his
     * status' possibilities.
     * <p>
     * If all the conditions are satisfied modifies the player's status on the server and create an {@link Update}
     * containing all the modifies bring by the player's action. Otherwise if only one of the requirements isn't
     * satisfied create an {@link ErrorUpdate}.
     * <p>
     * It also controls if the requirements for ending the game are satisfied by the current player.
     *
     * @param req the request received from the {@link Player}, client side.
     */
    public synchronized void notify(Request req) {
        //In the case the game is starting every game can send the request
        if (req.getPlayerID().equals(currPlayer.getNickName())  || req instanceof InitialPlayersSetRequest) {
            if (req.validRequest(turnStates)) {
                if (req.canBePlayed(currPlayer)) {
                    turnStates.add(req.handle(getPlayerFromID(req.getPlayerID()), this));
                    if (turnStates.contains(TurnState.END_TURN)) {
                        turnStates.clear();
                        currPlayer = nextPlayer;
                    }
                    if (lastTurn) {
                        if (currPlayerInt == 0) {
                            endgame();
                        }
                    }
                    //Notify all players except for the newGame req which is handled separately
                    if(!(req instanceof InitialPlayersSetRequest))
                        notifyAllPlayers(req.createUpdate(currPlayer, this));
                    //Check if the game is finished

                    if ((currPlayer.getBoard().getSlot().getAllCards().size() == 7 || currPlayer.getBoard().getFaithPath().checkPopeSpace(3)) && !lastTurn) {
                        lastTurn = true;
                        if (currPlayer.getBoard().getSlot().getAllCards().size() == 7) notifyAllPlayers(new LastTurnUpdate("\n" + currPlayer.getNickName() + " has bought 7 cards, it's last turn!\n"));
                        if (currPlayer.getBoard().getFaithPath().checkPopeSpace(3)) notifyAllPlayers(new LastTurnUpdate("\n" + currPlayer.getNickName() + " has reached 30 Faith Points, it's last turn!\n"));
                    }
                } else {
                    Update error = new ErrorUpdate("You can't do that!", req.getPlayerID());
                    notifyAllPlayers(error);
                }
            } else {
                Update error = new ErrorUpdate("You can't do this action in this moment!", req.getPlayerID());
                notifyAllPlayers(error);
            }
        } else {
            Update error = new ErrorUpdate("It's not your turn", req.getPlayerID());
            notifyAllPlayers(error);
        }
    }

    /**
     * Calls all the {@link Player}s, different by the curr player, to make them moveForward on their {@link FaithPath} of a number
     * of steps equal to the discarded resources by the current player in this turn;
     * calls the current player to make him move forward of a number of steps equal to the number of all faith points
     * obtained by the player in his turn.
     * <p>
     * After every movement of someone, check if it has reached a Pope Space, in this case check the advancement of
     * every player on their Faith Path and add them or not some Victory Points.
     *
     * @param discardedSteps the number of FAITH resources discarded by the current player that make other players move.
     * @param playerSteps    the number of FAITH resources obtained by the player in his turn that make him move.
     */
    public void fpAdvancement(int discardedSteps, int playerSteps) {
        //Sposta gli altri giocatori per le risorse scartate dal current player
        if (discardedSteps != 0) {
            for (Player player : players) {
                if (player != currPlayer) {
                    player.getBoard().getFaithPath().moveForward(discardedSteps);
                    player.getBoard().getFaithPath().checkVictoryPoints();
                }
            }
        }
        //Sposta il curr player per i FAITH ottenuti nel suo turno
        if (playerSteps != 0) {
            currPlayer.getBoard().getFaithPath().moveForward(playerSteps);
            currPlayer.getBoard().getFaithPath().checkVictoryPoints();
        }

        boolean popeSpace = false;
        //Controlla se, a seguito dei movimenti di tutti, qualcuno ha raggiunto la popeSpace corrente
        for (Player player : players) {
            if (player.getBoard().getFaithPath().checkPopeSpace(currPopeSpace)) {
                popeSpace = true;
                break;
            }
        }
        //Se qualcuno ha raggiunto la pope space corrente chiamo la checkVaticanSection per tutti
        if (popeSpace) {
            for (Player player : players) {
                player.getBoard().getFaithPath().checkVaticanSection(currPopeSpace);
            }
            currPopeSpace++;
            //Chiamata ricorsiva nel caso in cui un giocatore abbia ottenuto abbastanza punti da superare più di una Pope space nello stesso turno.
            this.fpAdvancement(0, 0);
        }
    }

    /**
     * Add {@link Player} to this {@link Game}.
     *
     * @param newPlayer the new player
     */
//Synchronized player because two players can't register at the same time
    public synchronized void addPlayer(Player newPlayer) {
        if(players.size() < maxPlayer)
            players.add(newPlayer);
    }

    /**
     * Called when the last turn of the {@link Game} is finished. Create an {@link EndgameUpdate} containing the winner
     * and the victory points of each {@link Player}s.
     */
    public void endgame() {
        int winnerPoints = 0;
        String winnerNickname = null;
        ArrayList<PlayerVP> playersVP = new ArrayList<>();
        for (Player player : players) {
            player.addVictoryPoints(player.getAllResources().size() /5);
            playersVP.add(new PlayerVP(player.getNickName(), player.getVictoryPoints()));
            if (player.getVictoryPoints() > winnerPoints) {
                winnerPoints = player.getVictoryPoints();
                winnerNickname = player.getNickName();
            }
        }
        notifyAllPlayers(new EndgameUpdate(winnerNickname, playersVP));
    }

    /**
     * Gets the {@link Player}s in this {@link Game}.
     *
     * @return the {@link Player}s' list.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Gets the number of {@link Player}s in this {@link Game}.
     *
     * @return the number of {@link Player}.
     */
    public int getMax() {
        return maxPlayer;
    }

    /**
     * Gets all the action done by the current {@link Player} on his turn till this moment.
     *
     * @return the list of done actions.
     */
    public ArrayList<TurnState> getTurnStates() {
        return turnStates;
    }

    /**
     * When tha {@link Game} starts create an {@link NewGameUpdate} containing for each {@link Player}, 4 different
     * {@link LeaderCard}s from which he has to choose 2 and, according to the number of players in the game,
     * some resources of their choice and some Faith Points.
     *
     * @return the {@link NewGameUpdate}.
     */
    public Update createNewGameUpdate() {
        ArrayList<LeaderCard> allLeaderCards = new ArrayList<>();
        allLeaderCards.addAll(DefaultCreator.produceLeaderCard()); //Produce tutte le Leader del gioco
        Collections.shuffle(allLeaderCards); //Le mischia

        //Crea un elenco di players e attibuisce ad ognungo di loro 4 leaderCard diverse
        ArrayList<PlayerLC> playersLC = new ArrayList<>();
        for (Player player : players) {
            player.setTable(table);
            ArrayList<String> leadersToChoose = new ArrayList<>();
            for (int addedCard = 0; addedCard < 4; addedCard++) {
                leadersToChoose.add(allLeaderCards.remove(0).getID());
                //Qui si potrebbe aggiungere anche la carta al player nel model e poi la request successiva ne rimuoverebbe 2
                //Oppure (come ora) non aggiungerle al player nel model ma nelle request successiva aggiungere le uniche 2
            }
            playersLC.add(new PlayerLC(player.getNickName(), leadersToChoose));
        }

        ArrayList<PlayerST> playersST = new ArrayList<>();
        switch (players.size()) {
            case (1):
                PlayerST player11 = new PlayerST(players.get(0).getNickName(), 0, 0);
                playersST.add(player11);
                break;
            case (2):
                PlayerST player21 = new PlayerST(players.get(0).getNickName(), 0, 0);
                PlayerST player22 = new PlayerST(players.get(1).getNickName(), 1, 0);
                playersST.add(player21);
                playersST.add(player22);
                break;

            case (3):
                PlayerST player31 = new PlayerST(players.get(0).getNickName(), 0, 0);
                PlayerST player32 = new PlayerST(players.get(1).getNickName(), 1, 0);
                PlayerST player33 = new PlayerST(players.get(2).getNickName(), 1, 1);
                players.get(2).addVictoryPoints(1);
                playersST.add(player31);
                playersST.add(player32);
                playersST.add(player33);
                break;

            case (4):
                PlayerST player41 = new PlayerST(players.get(0).getNickName(), 0, 0);
                PlayerST player42 = new PlayerST(players.get(1).getNickName(), 1, 0);
                PlayerST player43 = new PlayerST(players.get(2).getNickName(), 1, 1);
                PlayerST player44 = new PlayerST(players.get(3).getNickName(), 2, 1);
                players.get(2).addVictoryPoints(1);
                players.get(3).addVictoryPoints(1);
                playersST.add(player41);
                playersST.add(player42);
                playersST.add(player43);
                playersST.add(player44);
                break;
        }
        return new NewGameUpdate(this.gameId, table.getFrontIDs(), table.market.getMarket(), playersLC, playersST);
    }

    /**
     * Gets the position of the curr {@link Player} on the {@link Game}'s list of players.
     *
     * @return the position.
     */
    public int getCurrPlayerInt() {
        return currPlayerInt;
    }

    /**
     * Sets the position of the curr {@link Player} on the {@link Game}'s list of players.
     *
     * @param currPlayerInt the position tu set.
     */
    public void setCurrPlayerInt(int currPlayerInt) {
        this.currPlayerInt = currPlayerInt;
    }

    /**
     * Gets curr {@link Player}.
     *
     * @return the curr {@link Player}.
     */
    public Player getCurrPlayer() {
        return currPlayer;
    }

    /**
     * Sends to all the {@link Player}s the last {@link Update} created by the {@link Game}.
     *
     * @param update the {@link Update} to send.
     */
    public void notifyAllPlayers(Update update){
        System.out.println("Sending "+update+ "to all players");
        for (Player p: players)
            p.notifyView(update);
    }

    /**
     * Sets next {@link Player}.
     *
     * @param nextPlayer the next {@link Player}
     */
    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    /**
     * Gets the reference of a {@link Player} having the received ID.
     *
     * @param playerID the {@link Player}'s ID.
     * @return the reference to the {@link Player}.
     */
    public Player getPlayerFromID(String playerID) {
        for (Player p : players) {
            if (p.getNickName().equals(playerID)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Start the {@link Game}.
     */
    public void start() {
        currPlayer = players.get(0);
        notifyAllPlayers(new StartGameUpdate(players.get(0).getNickName()));
    }
}
