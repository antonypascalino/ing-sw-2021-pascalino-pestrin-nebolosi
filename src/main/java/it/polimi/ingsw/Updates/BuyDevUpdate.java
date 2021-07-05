package it.polimi.ingsw.Updates;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.OtherPlayerData;
import it.polimi.ingsw.view.data.PlayerData;
import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

/**
 * The {@link Update} sent after that a {@link Player} buys a {@link DevCard}.
 */
public class BuyDevUpdate implements Update {

    private ArrayList<TurnState> turnStates;
    private String playerID;
    private ArrayList<Resource[]> wareHouse;
    private ArrayList<Resource> strongBox;
    private ArrayList<String> tableCardsID; //just the front table cards
    private int victoryPoints;
    private ArrayList<String> cardsID;  //3 front cards + basic + extraProd
    private final String className;


    /**
     * Instantiates a new {@link BuyDevUpdate} setting everything can change with this actions: the {@link TurnState} list,
     * {@link Player}'s warehouse, {@link Player}'s strongbox, {@link Player}'s victory points and the development card on the table.
     *
     * @param playerID      the player id
     * @param turnStates    the turn states
     * @param wareHouse     the ware house
     * @param strongBox     the strong box
     * @param tableCardsID  the table cards id
     * @param victoryPoints the victory points
     * @param cardsID       the cards id
     */
    public BuyDevUpdate(String playerID, ArrayList<TurnState> turnStates, ArrayList<Resource[]> wareHouse, ArrayList<Resource> strongBox, ArrayList<String> tableCardsID, int victoryPoints, ArrayList<String> cardsID) {
        this.turnStates = turnStates;
        this.wareHouse = wareHouse;
        this.strongBox = strongBox;
        this.tableCardsID = tableCardsID;
        this.victoryPoints = victoryPoints;
        this.cardsID = cardsID;
        this.playerID = playerID;
        className = this.getClass().getName();
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        //Since the basic production is handled separately and the client handle it itself, we don't need to pass the basic prod
        cardsID.remove("BASIC");
        data.setFrontTableCardsID(tableCardsID);
        if (playerID.equals(data.getPlayerID())) {
            data.setVictoryPoints(victoryPoints);
            data.setTurnStates(turnStates);
            data.setWareHouse(wareHouse);
            data.setStrongBox(strongBox);
            data.setFrontCardsID(cardsID);
            data.getMenu().menuMaker();
        } else {
            for (OtherPlayerData p : data.getOtherPlayers()) {
                if (playerID.equals(p.getPlayerID())) {
                    p.setStrongBox(strongBox);
                    p.setSlotFrontCards(cardsID);
                    p.getWareHouse().clear();
                    p.setVictoryPoints(victoryPoints);
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
