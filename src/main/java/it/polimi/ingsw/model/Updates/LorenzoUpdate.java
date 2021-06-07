package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.view.data.OtherPlayerData;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;

public class LorenzoUpdate implements Update {

    private String classname;
    private int lorenzoFaithPoints;
    private int playersVP;
    private ArrayList<String> frontTableCards;
    private String playerID;

    public LorenzoUpdate(int lorenzoFaithPoints, int playersVP, ArrayList<String> frontTableCards) {
        classname = this.getClass().getName();
        this.lorenzoFaithPoints = lorenzoFaithPoints;
        this.playersVP = playersVP;
        this.frontTableCards = frontTableCards;
        playerID = "Lorenzo";
    }

    @Override
    public void handleUpdate(PlayerData data) {
        data.setVictoryPoints(playersVP);
        data.setFrontCardsID(frontTableCards);
        data.getMenu().menuMaker();
        data.getOtherPlayers().get(0).setFaithPoints(lorenzoFaithPoints);
    }

    @Override
    public String getClassName() {
        return null;
    }
}
