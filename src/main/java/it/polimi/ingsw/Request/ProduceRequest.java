package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.Updates.PlayerVP;
import it.polimi.ingsw.Updates.ProduceUpdate;
import it.polimi.ingsw.Updates.Update;
import it.polimi.ingsw.model.card.ExtraProd;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The {@link Request} sent by a player when he wants to produce. It contains an ArrayList of {@link Production}s:
 * one for each production power.
 */
public class ProduceRequest implements Request {

    private String playerID;
    private int gameID;
    private ArrayList<Production> productions;
    private int playerSteps;
    private final String className;

    /**
     * Instantiates a new {@link BuyDevRequest} setting the information for handle the specific actions:
     * the {@link Game}'s ID, the player's nickname and all the {@link Production}s to use.
     *
     * @param gameID   the game id
     * @param playerID the player id
     * @param prod     the prod
     */
    public ProduceRequest(int gameID, String playerID, ArrayList<Production> prod) {
        className = this.getClass().getName();
        this.gameID = gameID;
        this.playerID = playerID;
        this.productions = prod;
    }

    @Override
    public boolean canBePlayed(Player player) {

        //checks if the player has production cards
        for (Production prod : productions) {
            if (!player.getProductionID().contains(prod.getCardID())) {
                return false;
            }
        }

        //checks if the player has the resources by placing them in a temporary array
        ArrayList<Resource> resTemp = new ArrayList<>();
        for (Production prod : productions) {
            for (MappedResource map : prod.getMappedResources()) {
                if (!map.getPlace().equals("choice")) {
                    resTemp.add(map.getResource());
                }
            }
        }
        for (Resource res : resTemp) {
            if (Collections.frequency(resTemp, res) > Collections.frequency(player.getAllResources(), res)) {
                return false;
            }
        }

        //compares player resources to card's required resources
        for (Production pr : productions) {
            for (MappedResource mp : pr.getMappedResources()) {
                if (pr.getCardID().contains("dev")) {
                    if (player.getBoard().getDevFromID(pr.getCardID()).getRequirements().size() != pr.getMappedResources().size() || !player.getBoard().getDevFromID(pr.getCardID()).getRequirements().contains(mp.getResource())) {
                        return false;
                    }
                }
                if (pr.getCardID().contains("PROD")) {
                    if (((ExtraProd) (player.getLeaderFromID(pr.getCardID()))).getProducedRes().contains(mp.getResource())) {
                        return false;
                    }
                }
            }
        }

        //checks whether duplicate cards are present
        return productions.stream().map(Production::getCardID).distinct().count() == productions.size();
    }

    @Override
    public TurnState handle(Player player, Game game) {
        for (Production prodReq : productions) {
            for (MappedResource mapRes : prodReq.getMappedResources()) {
                player.removeResource(mapRes.getResource(), mapRes.getPlace());
            }
        }
        for (Production prod : productions) {
            if (prod.getCardID().equals("BASIC") || prod.getCardID().contains("PROD")) {
                //Add to the temp box all the resource that
                //Are received as choices
                ArrayList<Resource> list = new ArrayList<>();

                for (MappedResource x : prod.getMappedResources()) {
                    if (x.getPlace().equals("choice")) {
                        list.add(x.getResource());
                    }
                }
                //if extra production : it adds an additional faith point
                if (prod.getCardID().contains("PROD")) {
                    ArrayList<Resource> faithPoint = new ArrayList<>();
                    faithPoint.add(Resource.FAITH);
                    player.getBoard().getTempBox().addResource(faithPoint);
                }
                player.getBoard().getTempBox().addResource(list);
            } else
                player.produce(prod.getCardID());
        }

        playerSteps = player.getBoard().getTempBox().filterFaithPoints();
        game.fpAdvancement(0, playerSteps);
        player.getBoard().getTempBox().moveToStrongBox();
        return TurnState.PRODUCE;
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return !(turnStates.contains(TurnState.BUY_DEV_CARD) || turnStates.contains(TurnState.PRODUCE) || turnStates.contains(TurnState.GET_FROM_MARKET));
    }


    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public Update createUpdate(Player player, Game game) {
        ArrayList<PlayerVP> playersVP = new ArrayList<>();
        for (Player p : game.getPlayers()) {
            playersVP.add(new PlayerVP(p.getNickName(), p.getVictoryPoints()));
        }

        return new ProduceUpdate(player.getNickName(), game.getTurnStates(), player.getDeposits(), player.getBoard().getStrongBox().getResources(), player.getBoard().getFaithPath().getAdvancement(), playersVP);
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
