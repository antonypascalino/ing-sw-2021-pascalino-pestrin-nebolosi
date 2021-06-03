package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Updates.StartGameUpdate;
import it.polimi.ingsw.model.Updates.Update;

import java.util.ArrayList;

public class InitialPlayersSetRequest implements Request {
    private int gameID;
    private String playerID;
    private ArrayList<MarketResource> marketRes;
    private String className;

    public InitialPlayersSetRequest(int gameID, String playerID, ArrayList<MarketResource> marketRes) {
        this.gameID = gameID;
        this.playerID = playerID;
        this.marketRes = marketRes;
        className = this.getClass().getName();
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public TurnState handle(Player curr, Game game) {
        for (Player player : game.getPlayers()) {
            if (playerID.equals(player.getNickName())) {
                for(MarketResource mRes : marketRes) {
                    player.addResource(mRes.getLevel(), mRes.getResource());
                }
            }
        }
        return null;
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
}
