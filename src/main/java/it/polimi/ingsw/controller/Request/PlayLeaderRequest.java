package it.polimi.ingsw.controller.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.Updates.PlayLeaderUpdate;
import it.polimi.ingsw.Updates.Update;
import it.polimi.ingsw.model.card.LeaderCard;

import java.util.ArrayList;

/**
 * The {@link Request} sent by a player when he wants to play a {@link LeaderCard}.
 */
public class PlayLeaderRequest implements Request {

    private String playerID;
    private int gameID;
    private String cardID;
    private final String className;

    /**
     * Instantiates a new {@link PlayLeaderRequest} setting the player's nickname, the game's ID and the {@link LeaderCard}'s
     * ID to play.
     *
     * @param playerID the player id
     * @param gameID   the game id
     * @param cardID   the card id
     */
    public PlayLeaderRequest(String playerID, int gameID, String cardID) {
        this.playerID = playerID;
        this.gameID = gameID;
        this.className = this.getClass().getName();
        this.cardID = cardID;
    }

    @Override
    public TurnState handle(Player player, Game game) {
        player.getLeaderFromID(cardID).playCard();
        return TurnState.PLAY_LEADER_CARD;
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return !(turnStates.contains(TurnState.PLAY_LEADER_CARD) || turnStates.contains(TurnState.DISCARD_LEADER_CARD));
    }

    @Override
    public boolean canBePlayed(Player player) {
        return player.getLeaderFromID(cardID).canBePlayed();
    }

    @Override
    public Update createUpdate(Player player, Game game) {
        ArrayList<String> leadersPlayed = new ArrayList<>();
        ArrayList<String> leadersNOTPlayed = new ArrayList<>();
        for (LeaderCard leaderCard : player.getLeaderCards()) {
            if (leaderCard.isEnable()) {
                leadersPlayed.add(leaderCard.getID());
            } else leadersNOTPlayed.add(leaderCard.getID());
        }
        return new PlayLeaderUpdate(playerID, cardID, leadersPlayed, leadersNOTPlayed, player.getLeaderFromID(cardID).getPowerResource(), player.getVictoryPoints(), game.getTurnStates());
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getPlayerID() {
        return playerID;
    }

    @Override
    public int getGameID() {
        return gameID;
    }
}
