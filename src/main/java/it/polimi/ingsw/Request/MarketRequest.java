package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Table.Market;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Updates.MarketUpdate;
import it.polimi.ingsw.model.Updates.PlayerFP;
import it.polimi.ingsw.model.Updates.PlayerVP;
import it.polimi.ingsw.model.Updates.Update;

import java.util.ArrayList;

/**
 * The {@link Request} sent by a player when he wants to take {@link Resource} from the {@link Market}.
 */
public class MarketRequest implements Request {
    private String playerID;
    private int gameID;
    private MarketDimension marketDimension;
    private int number;
    private final String className;
    private ArrayList<MarketResource> marketResources;
    private int myFPSteps;
    private int discardedSteps;

    /**
     * Instantiates a new {@link MarketRequest} setting the information for handle the specific actions:
     * the {@link Game}'s ID, the player's nickname, all the {@link MarketResource} and the information about
     * the row/column take from market.
     *
     * @param marketDimension the market dimension
     * @param number          the index of the market dimension.
     * @param gameID          the {@link Game}'s ID.
     * @param playerID        the {@link Player}'s ID.
     * @param marketResources the market resources
     */
    public MarketRequest(MarketDimension marketDimension, int number, int gameID, String playerID, ArrayList<MarketResource> marketResources) {
        this.marketResources = marketResources;
        this.playerID = playerID;
        this.marketDimension = marketDimension;
        this.number = number;
        this.gameID = gameID;

        this.className = this.getClass().getName();
        myFPSteps = 0;
        discardedSteps = 0;
    }

    @Override
    public boolean canBePlayed(Player player) {
        ArrayList<Resource> fromMarket = new ArrayList<>();

        if (marketDimension.equals(MarketDimension.ROW)) {
            fromMarket = player.getTable().market.seeRow(number);
        } else if (marketDimension.equals(MarketDimension.COL)) {
            fromMarket = player.getTable().market.seeColumn(number);
        }
        //check if the Required resources match the relative market resources and if the empty marbles have been correctly indicated
        if (!player.checkMarketRes(this.requestedRes(), fromMarket)) {
            return false;
        }
        //check if the indicated levels are compatible with the player's level in his WareHouse
        //Passa dal player perchè potrebbero esserci livelli extra
        for (MarketResource marketRes : marketResources) {
            if (marketRes.getLevel() >= 0) {
                if (!player.checkLevel(marketRes.getLevel())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int getGameID() {
        return gameID;
    }

    @Override
    public TurnState handle(Player player, Game game) {
        //It has already checked that is the same array as the market row or column
        for (MarketResource marketRes : marketResources) {
            if (!marketRes.getResource().equals(Resource.EMPTY)) {
                if (marketRes.getResource().equals(Resource.FAITH)) {
                    myFPSteps++;
                    //Level = -1 means the player wants to discard it
                } else if ((marketRes.getLevel() == -1) || (!player.checkSpace(marketRes.getResource(), marketRes.getLevel()))) {
                    discardedSteps++;
                } else player.addResource(marketRes.getLevel(), marketRes.getResource());
            }
        }
        //Abbiamo già confrontato che le risorse richieste dal player matchano le corrispondenti risorse del mercato (tenenedo anche conto delle Changes)
        // per cui si possono usare quelle, già matchate e changeate per aggiungerle al Player e modificare il mercato di conseguenza.
        // altrimenti si dovrebbe fare un altro giro di chiamate per prendere le risorse dal mercato cambiare le empty e aggiungerle al player.
        if (marketDimension.equals(MarketDimension.ROW)) {
            player.getTable().market.getRow(number);
        } else if (marketDimension.equals(MarketDimension.COL)) {
            player.getTable().market.getColumn(number);
        }

        game.fpAdvancement(discardedSteps, myFPSteps);
        return TurnState.GET_FROM_MARKET;
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return !(turnStates.contains(TurnState.BUY_DEV_CARD) || turnStates.contains(TurnState.PRODUCE) || turnStates.contains(TurnState.GET_FROM_MARKET));
    }


    @Override
    public String getClassName() {
        return className;
    }

    private ArrayList<Resource> requiredRes() {
        ArrayList<Resource> requiredRes = new ArrayList<>();
        for (MarketResource marketRes : marketResources) {
            requiredRes.add(marketRes.getResource());
        }
        return requiredRes;
    }

    /**
     * Gets my fp steps.
     *
     * @return the my fp steps
     */
    public int getMyFPSteps() {
        return myFPSteps;
    }

    /**
     * Gets discarded steps.
     *
     * @return the discarded steps
     */
    public int getDiscardedSteps() {
        return discardedSteps;
    }


    /**
     * Requested res array list.
     *
     * @return the array list
     */
    public ArrayList<Resource> requestedRes() {
        ArrayList<Resource> requestedRes = new ArrayList<>();
        for (MarketResource marketRes : marketResources) {
            requestedRes.add(marketRes.getResource());
        }
        return requestedRes;
    }

    @Override
    public Update createUpdate(Player player, Game game) {
        ArrayList<PlayerVP> playersVP = new ArrayList<>();
        ArrayList<PlayerFP> playersFP = new ArrayList<>();
        for (Player p : game.getPlayers()) {
            playersVP.add(new PlayerVP(p.getNickName(), p.getVictoryPoints()));
            playersFP.add(new PlayerFP(p.getNickName(), p.getBoard().getFaithPath().getAdvancement()));
        }

        return new MarketUpdate(player.getNickName(), game.getTurnStates(), player.getDeposits(), playersVP, playersFP, game.getTable().market.getMarket());
    }

    @Override
    public String getPlayerID() {
        return playerID;
    }
}
