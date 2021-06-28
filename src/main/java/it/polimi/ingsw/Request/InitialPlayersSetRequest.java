package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Updates.StartGameUpdate;
import it.polimi.ingsw.model.Updates.Update;
import it.polimi.ingsw.model.card.LeaderCard;

import java.util.ArrayList;

/**
 * The {@link Request} sent by a player after the {@link Game} starts. It contains the {@link LeaderCard}s
 * and the resources the player chose.
 *
 */
public class InitialPlayersSetRequest implements Request {
    private int gameID;
    private String playerID;
    private ArrayList<MarketResource> marketRes;
    private ArrayList<String> leadersChosen;
    private String className;

    /**
     * Instantiates a new {@link InitialPlayersSetRequest} setting the information for handle the specific actions:
     * the {@link Game}'s ID, the player's nickname, the {@link LeaderCard}s and the resources he chose.
     *
     * @param gameID        the {@link Game}'s ID.
     * @param playerID      the {@link Player}'s ID.
     * @param marketRes     all the {@link MarketResource}s.
     * @param leadersChosen all the {@link LeaderCard}s' ID the player chosen.
     */
    public InitialPlayersSetRequest(int gameID, String playerID, ArrayList<MarketResource> marketRes, ArrayList<String> leadersChosen) {
        this.gameID = gameID;
        this.playerID = playerID;
        this.marketRes = marketRes;
        className = this.getClass().getName();
        this.leadersChosen = leadersChosen;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public TurnState handle(Player curr, Game game) {
        for (Player player : game.getPlayers()) {
            if (playerID.equals(player.getNickName())) {
                int discardedSteps = 0;
                for (MarketResource mRes : marketRes) {
                    if (mRes.getLevel() != -1) {
                        player.addResource(mRes.getLevel(), mRes.getResource());
                    } else discardedSteps++;
                }
                if (discardedSteps > 0) game.fpAdvancement(discardedSteps, 0);
                for (String cardID : leadersChosen) {
                    DefaultCreator.getLeaderFromID(cardID).assignTo(player);
                }
                game.playerReady++;
                //If all the players are ready
                if (game.playerReady == game.maxPlayer)
                    game.start();
            }
        }
        return null;
    }

    @Override
    public int getGameID() {
        return gameID;
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return true;
    }

    @Override
    public boolean canBePlayed(Player player) {
        return true;
    }

    @Override
    public Update createUpdate(Player player, Game game) {
        return new StartGameUpdate(game.getCurrPlayer().getNickName());
    }

    @Override
    public String getPlayerID() {
        return playerID;
    }
}
