package it.polimi.ingsw.model.card;

import it.polimi.ingsw.model.Player.ExtraProdPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

/**
 * The {@link LeaderCard} that gives to the {@link Player} an extra production power.
 */
public class ExtraProd implements LeaderCard {

    private int victoryPoints;
    private boolean isEnable;
    private Player player;
    private String requires; //WHat it needs to be played
    private Resource prodResource;  //What it needs for producing
    private final String className;
    private String cardID;

    /**
     * Instantiates a new Extra Production {@link LeaderCard} and set enable to false.
     *
     * @param victoryPoints the victory points the {@link LeaderCard} gives the {@link Player} when played.
     * @param requires      the color of a {@link DevCard} of level 2 needed to play the {@link LeaderCard}.
     * @param prodResource  the {@link DevCard} needed to use this production power.
     * @param cardID        the {@link LeaderCard}'s ID.
     */
    public ExtraProd(int victoryPoints, String requires, Resource prodResource, String cardID) {
        this.cardID = cardID;
        className = this.getClass().getName();
        this.prodResource = prodResource;
        this.requires = requires;
        this.victoryPoints = victoryPoints;
        isEnable = false;
    }

    @Override
    public void assignTo(Player player) {
        this.player = player;
        player.addLeaderCard(this);
    }

    @Override
    public boolean isEnable() {
        return isEnable;
    }

    @Override
    public boolean canBePlayed() {
        if (isEnable) return false; //It can't be played twice
        for (DevCard card : player.getBoard().getSlot().getAllCards()) {
            if (card.getColor().equals(requires.toUpperCase()) && card.getLevel() == 2) return true;
        }
        return false;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void playCard() {
        if (canBePlayed()) {
            isEnable = true;
            player.addVictoryPoints(victoryPoints);
            Player tmp = new ExtraProdPlayer(player, prodResource, cardID);
            player.getGame().changePlayer(player, tmp);
            for (LeaderCard card : player.getLeaderCards()) {
                //Do not change the reference on this card
                if (!card.getID().equals(this.getID()))
                    card.setPlayer(tmp);
            }
            this.player = tmp;
        }
    }

    @Override
    public boolean equals(LeaderCard compare) {
        return this.cardID.equals(compare.getID());

    }

    @Override
    public String getID() {
        return cardID;
    }

    @Override
    public void setPlayer(Player tmp) {
        this.player = tmp;
    }

    /**
     * Get the {@link Resource} produced by this {@link ExtraProd}.
     *
     * @return the array list
     */
    public ArrayList<Resource> getProducedRes() {
        ArrayList<Resource> temp = new ArrayList<>();
        temp.add(Resource.FAITH);
        temp.add(Resource.CHOICE);
        return temp;
    }

    @Override
    public Resource getPowerResource() {
        return prodResource;
    }

}
