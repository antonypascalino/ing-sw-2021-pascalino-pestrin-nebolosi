package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Updates.DiscardLeaderUpdate;
import it.polimi.ingsw.model.Updates.PlayerVP;
import it.polimi.ingsw.model.Updates.Update;
import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.card.LeaderCard;

import java.util.ArrayList;

/**
 * The {@link Request} sent by a player when he wants to discard a {@link LeaderCard}.
 */
public class DiscardLeaderRequest implements Request{

    private final String className;
    private String cardID;
    private String playerID;
    private int gameID;

    /**
     * Instantiates a new {@link DiscardLeaderRequest} setting the information for handle the specific actions:
     * the {@link Game}'s ID, the player's nickname, the {@link LeaderCard}'ID the player wants to discard.
     *
     * @param gameID   the game id
     * @param playerID the player id
     * @param cardID   the card id
     */
    public DiscardLeaderRequest(int gameID, String playerID, String cardID) {
        this.gameID = gameID;
        this.playerID = playerID;
        className = this.getClass().getName();
        this.cardID = cardID;
    }

    @Override
    public boolean canBePlayed(Player player) {
        return !player.getLeaderFromID(cardID).isEnable();
    }

    @Override
    public TurnState handle(Player player, Game game) {
        //Forse non lo stiamo rimuovendo dal player ma solo da un clone di quell'arrayList
        player.getLeaderCards().remove(player.getLeaderFromID(cardID));
        //Notify all the players that this handle didn't discard any steps but moved the player forward by one
        game.fpAdvancement(0,1);
        return TurnState.DISCARD_LEADER_CARD;
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return !(turnStates.contains(TurnState.PLAY_LEADER_CARD) || turnStates.contains(TurnState.DISCARD_LEADER_CARD));
    }

    @Override
    public int getGameID() {
        return gameID;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public Update createUpdate(Player player, Game game) {
        ArrayList<PlayerVP> playersVP = new ArrayList<PlayerVP>();
        for (Player p : game.getPlayers()) {
            playersVP.add(new PlayerVP(p.getNickName(), p.getVictoryPoints()));
        }

        return new DiscardLeaderUpdate(player.getNickName(), game.getTurnStates(), player.getBoard().getFaithPath().getAdvancement(), player.getLeadersID(), playersVP);
    }

    @Override
    public String getPlayerID() {
        return playerID;
    }
}
