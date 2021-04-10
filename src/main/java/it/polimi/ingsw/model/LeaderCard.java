package it.polimi.ingsw.model;

public interface LeaderCard {

    public void playCard();
    public boolean canBePlayed();
    public void assignTo(Player p);
}
