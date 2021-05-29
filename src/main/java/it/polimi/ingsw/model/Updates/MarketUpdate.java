package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

public class MarketUpdate implements Update {
    private ArrayList<TurnState> turnStates;
    private ArrayList<Resource[]> wareHouse;
    private ArrayList<PlayerVP> playersVP;
    private ArrayList<PlayerFP> playersFP;
    private Resource[][] market;

    public MarketUpdate(ArrayList<TurnState> turnStates, ArrayList<Resource[]> wareHouse, ArrayList<PlayerVP> playersVP, ArrayList<PlayerFP> playersFP, Resource[][] market) {
        this.turnStates = turnStates;
        this.wareHouse = wareHouse;
        this.playersVP = playersVP;
        this.playersFP = playersFP;
        this.market = market;
    }

    @Override
    public void handleUpdate(Game game) {

    }
}
