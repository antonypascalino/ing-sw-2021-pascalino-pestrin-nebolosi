package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Table.Market;
import it.polimi.ingsw.view.data.OtherPlayerData;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The {@link Update} sent after that a {@link Player} takes {@link Resource}s from {@link Market}.
 */
public class MarketUpdate implements Update {
    private ArrayList<TurnState> turnStates;
    private ArrayList<Resource[]> wareHouse;
    private ArrayList<PlayerVP> playersVP;
    private ArrayList<PlayerFP> playersFP;
    private Resource[][] market;
    private String playerID;
    private final String className;


    /**
     * Instantiates a new {@link MarketUpdate} setting everything can change with this actions: the {@link TurnState} list
     * {@link Player}'s warehouse, every players' victory points, every players' faith points and the disposal of the {@link Resource}
     * in the {@link Market}.
     *
     * @param playerID   the {@link Player}'s ID who went to the {@link Market}.
     * @param turnStates the turn states list.
     * @param wareHouse  the {@link Player}'s warehouse.
     * @param playersVP  the {@link PlayerVP}s.
     * @param playersFP  the {@link PlayerFP}s.
     * @param market     the {@link Market} disposal.
     */
    public MarketUpdate(String playerID, ArrayList<TurnState> turnStates, ArrayList<Resource[]> wareHouse, ArrayList<PlayerVP> playersVP, ArrayList<PlayerFP> playersFP, Resource[][] market) {
        this.turnStates = turnStates;
        this.wareHouse = wareHouse;
        this.playersVP = playersVP;
        this.playersFP = playersFP;
        this.market = market;
        this.playerID = playerID;
        className = this.getClass().getName();
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        data.setMarket(market);
        for (PlayerVP pvp : playersVP) {
            if (pvp.getPlayerID().equals(data.getPlayerID())) {
                data.setVictoryPoints(pvp.getVictoryPoints());
            } else {
                for (OtherPlayerData p : data.getOtherPlayers()) {
                    if (pvp.getPlayerID().equals(p.getPlayerID())) {
                        p.setVictoryPoints(pvp.getVictoryPoints());
                    }
                }
            }
        }
        for (PlayerFP pfp : playersFP) {
            if (pfp.getPlayerID().equals(data.getPlayerID())) {
                data.setFaithPoints(pfp.getFaithPoints());
            } else {
                for (OtherPlayerData p : data.getOtherPlayers()) {
                    if (pfp.getPlayerID().equals(p.getPlayerID())) {
                        p.setFaithPoints(pfp.getFaithPoints());
                    }
                }
            }
        }
        if (playerID.equals(data.getPlayerID())) {
            data.setTurnStates(turnStates);
            data.setWareHouse(wareHouse);
            data.getMenu().menuMaker();
        } else {
            for (OtherPlayerData p : data.getOtherPlayers()) {
                if (playerID.equals(p.getPlayerID())) {
                    for (Resource[] l : wareHouse) {
                        for (Resource r : l) {
                            p.getWareHouse().add(r);
                        }
                    }
                }
            }
        }
    }
}
