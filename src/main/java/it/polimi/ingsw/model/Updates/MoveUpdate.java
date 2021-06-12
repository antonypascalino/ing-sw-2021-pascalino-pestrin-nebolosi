package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Board.WareHouse;
import it.polimi.ingsw.view.data.OtherPlayerData;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

/**
 * The {@link Update} sent after that a {@link Player} moves his {@link Resource}s in his {@link WareHouse}.
 */
public class MoveUpdate implements Update {
    private ArrayList<TurnState> turnStates;
    private ArrayList<Resource[]> wareHouse;
    private String playerID;

    private final String className;


    /**
     * Instantiates a new {@link MoveUpdate} setting everything can change with this actions:
     * {@link Player}'s {@link WareHouse}, {@link TurnState}'s list.
     *
     * @param playerID   the {@link Player}'s ID.
     * @param turnStates the turn states' list
     * @param wareHouse  the {@link WareHouse} of the player.
     */
    public MoveUpdate(String playerID, ArrayList<TurnState> turnStates, ArrayList<Resource[]> wareHouse) {
        this.turnStates = turnStates;
        this.wareHouse = wareHouse;
        this.playerID = playerID;
        className = this.getClass().getName();
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        if (playerID.equals(data.getPlayerID())) {
            data.setTurnStates(turnStates);
            data.setWareHouse(wareHouse);
            data.getMenu().menuMaker();
        } else {
            for (OtherPlayerData p : data.getOtherPlayers()) {
                if (playerID.equals(p.getPlayerID())) {
                    p.getWareHouse().clear();
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
