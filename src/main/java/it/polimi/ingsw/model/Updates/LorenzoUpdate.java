package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.view.data.OtherPlayerData;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class LorenzoUpdate implements Update {
    private String className;
    private int lorenzoFaithPoints;
    private int playersVP;
    private ArrayList<String> frontTableCards;
    private String playerID;
    private String actionDone;
    private ArrayList<TurnState> turnStates;

    public LorenzoUpdate(int lorenzoFaithPoints, int playersVP, ArrayList<String> frontTableCards, String actionDone, ArrayList<TurnState> turnStates) {
        className = this.getClass().getName();
        this.lorenzoFaithPoints = lorenzoFaithPoints;
        this.playersVP = playersVP;
        this.frontTableCards = frontTableCards;
        playerID = "Lorenzo";
        this.actionDone = actionDone;
        this.turnStates = turnStates;
    }

    @Override
    public void handleUpdate(PlayerData data) {
        data.setVictoryPoints(playersVP);
        data.setFrontTableCardsID(frontTableCards);
        data.getPrinter().printMessage(actionDone);
        data.getPrinter().printMessage("Lorenzo's advancement on his Faith Path is now " + lorenzoFaithPoints);
        //data.getMenu().menuMaker(); Se si lascia questo vengono prodotti due menuMaker, ma se aggiungiamo un messaggio con quello che ha fatto il player apparirà prima quello che ha fatto Lorenzo e poi quello che ha fatto il player
    }

    @Override
    public String getClassName() {
        return null;
    }
}
