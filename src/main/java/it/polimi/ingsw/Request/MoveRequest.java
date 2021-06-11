package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Updates.MoveUpdate;
import it.polimi.ingsw.model.Updates.Update;
import it.polimi.ingsw.model.card.LeaderCard;

import java.util.ArrayList;
import java.util.Arrays;


public class MoveRequest implements Request {
    private int originLevel;
    private int destLevel;
    private final String className;
    private String playerID;
    private int gameID;

    public MoveRequest(String playerID, int gameID, int originLevel, int destLevel) {
        this.originLevel = originLevel;
        this.destLevel = destLevel;
        this.playerID = playerID;
        this.gameID = gameID;
        className = this.getClass().getName();
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public boolean canBePlayed(Player player) {
        //Controlla che il giocatore possegga il livello di destinazione
        if (!player.checkLevel(destLevel)) {
            return false;
        }
        //Da Warehouse a...
        if (originLevel <= 2) {
            // ... a WareHouse
            if (destLevel <= 2) {
                long originCount = Arrays.stream(player.getDeposits().get(originLevel)).filter(resource -> !resource.equals(Resource.EMPTY)).count();
                long destCount = Arrays.stream(player.getDeposits().get(destLevel)).filter(resource -> !resource.equals(Resource.EMPTY)).count();
                if (!(originCount == 0 && destCount == 0)) {
                    return originCount <= player.getDeposits().get(destLevel).length && destCount <= player.getDeposits().get(originLevel).length;
                }
            } else {
                if (Arrays.stream(player.getDeposits().get(destLevel)).anyMatch(x -> x.equals(Resource.EMPTY))) {
                    ArrayList<LeaderCard> leaderCards = new ArrayList<>();
                    for (LeaderCard card : player.getLeaderCards()) {
                        if (card.getID().contains("DEP")) {
                            leaderCards.add(card);
                        }
                    }
                    return player.getDeposits().get(originLevel)[0] == leaderCards.get(destLevel - 3).getPowerResource();
                }
            }
        }
        //Da ExtraDep a...
        else {
            for (int i = 0; i < 3; i++) {
                if (i != destLevel) {
                    if (Arrays.stream(player.getDeposits().get(i)).anyMatch(x -> x.equals(player.getDeposits().get(originLevel)[0]))) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int getGameID() {
        return gameID;
    }
    @Override
    public TurnState handle(Player player, Game game) {
        player.switchLevels(originLevel, destLevel);
        return TurnState.MOVE_RESOURCE;
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return true; //perché le risorse si possono spostare nel WareHouse in ogni momento
    }

    @Override
    public Update createUpdate(Player player, Game game) {
        return new MoveUpdate(player.getNickName(), game.getTurnStates(), player.getDeposits());
    }

    @Override
    public String getPlayerID() {
        return playerID;
    }
}
