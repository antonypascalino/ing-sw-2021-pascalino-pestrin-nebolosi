package it.polimi.ingsw.model.card;

import it.polimi.ingsw.model.Player.ExtraProdPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

/**
 * The type Extra prod.
 */
public class ExtraProd /*extends Producer*/ implements LeaderCard  {

    private int victoryPoints;
    private boolean isEnable;
    private Player player;
    private String requires; //WHat it needs to be played
    private Resource prodResource;  //What it needs for producing
    private final String className;
    private String cardID;

    /**
     * Instantiates a new Extra prod.
     *
     * @param victoryPoints the victory points
     * @param requires      the color of the required card
     * @param prodResource  the prod resource
     * @param cardID the unique id for the card
     */
    public ExtraProd(int victoryPoints, String requires, Resource prodResource, String cardID)
    {
        this.cardID = cardID;
        className = this.getClass().getName();
        this.prodResource = prodResource;
        this.requires = requires;
        this.victoryPoints = victoryPoints;
        isEnable = false;
    }

    public void assignTo(Player player) {
        this.player = player;
        player.addLeaderCard(this);
    }


    public boolean isEnable()
    {
        return isEnable;
    }



    public boolean canBePlayed()
    {
        if(isEnable) return false; //It can't be played twice
        for(DevCard card : player.getBoard().getSlot().getAllCards()) {
            if (card.getColor().equals(requires.toUpperCase()) && card.getLevel() == 2) return true;
        }
        return false;
    }

    public String getClassName()
    {
        return className;
    }

    public void playCard()
    {
        if(canBePlayed())
        {
            isEnable = true;
            player.addVictoryPoints(victoryPoints);
            Player tmp =new ExtraProdPlayer(player, prodResource, cardID);
            player.getGame().changePlayer(player, tmp);
            for (LeaderCard card : player.getLeaderCards())
            {
                //Do not change the reference on this card
                if(!card.getID().equals(this.getID()))
                card.setPlayer(tmp);
            }
            this.player = tmp;
        }
    }

    @Override
    public Boolean equals(LeaderCard compare)
    {
        return this.cardID.equals(compare.getID());

    }

    @Override
    public String getID()
    {
        return cardID;
    }

    /**
     * Just used as setter, for adding the card to the player use assignTo
     * @param tmp the player to be added to the player
     */
    @Override
    public void setPlayer(Player tmp) {
        this.player = tmp;
    }

    public ArrayList<Resource> getProducedRes(){
        ArrayList<Resource> temp = new ArrayList<Resource>();
        temp.add(Resource.FAITH);
        temp.add(Resource.CHOICE);
        return temp;
    }

    public String getProdID()
    {
        return cardID;
    }

    @Override
    public Resource getPowerResource() {
        return prodResource;
    }


}
