package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.Observer;
import it.polimi.ingsw.view.data.*;

import java.util.ArrayList;

public class PlayLeaderUpdate implements Update {

    private final String className;
    private ArrayList<String> leadersPlayed; //the leaders the player activated
    private ArrayList<String> leadersNOTPlayed; //the leaders the player still not activated
    private String leaderPlayedID; //the leader played this turn
    private String playerID;
    private int victoryPoints;
    private Resource powerResource;

    public PlayLeaderUpdate(String playerID, String leaderPlayedID, ArrayList<String> leadersPlayed, ArrayList<String> leadersNOTPlayed, Resource powerResource, int victoryPoints) {
        className = this.getClass().getName();
        this.playerID = playerID;
        this.leaderPlayedID = leaderPlayedID;
        this.leadersNOTPlayed = leadersNOTPlayed;
        this.leadersPlayed = leadersPlayed;
        this.powerResource = powerResource;
        this.victoryPoints = victoryPoints;
    }

    public void wrapPlayer(Observer observer) {
        String leaderType = leaderPlayedID.substring(0,3); //considers only the firsts 3 char of the string
        switch (leaderType) {
            case ("PRO") : {
                ArrayList<String> leaderProdID = new ArrayList<>();
                ArrayList<Resource> prodRequired = new ArrayList<>();
                leaderProdID.add(leaderPlayedID);
                prodRequired.add(powerResource);
                observer.setPlayer(new ExtraProdData(leaderProdID, prodRequired, observer.getData()));
                break;
            }
            case ("CNG") : {
                ArrayList<Resource> changes = new ArrayList<>();
                changes.add(powerResource);
                observer.setPlayer(new ChangeResData(changes, observer.getData()));
                break;
            }
            case ("DIS") : {
                ArrayList<Resource> discount = new ArrayList<>();
                discount.add(powerResource);
                observer.setPlayer(new DiscountData(discount, observer.getData()));
                break;
            }
            case ("DEP") : {
                ArrayList<Resource> placeable = new ArrayList<>();
                placeable.add(powerResource);
                observer.setPlayer(new ExtraDepData(observer.getData(), placeable));
                break;
            }

        }
    }

    @Override
    public void handleUpdate(PlayerData data) {
        if (data.getPlayerID().equals(playerID)) {
            data.setLeadersPlayedID(leadersPlayed);
            data.setLeadersID(leadersNOTPlayed);
            data.setVictoryPoints(victoryPoints);
            data.getPrinter().printMessage("You have played " + data.getLeaderFromID(leaderPlayedID));
            data.getMenu().menuMaker();
        }
        else {
            for (OtherPlayerData otherPlayer : data.getOtherPlayers()) {
                if (otherPlayer.getPlayerID().equals(playerID)) {
                    otherPlayer.setPlayedLeadersID(leadersPlayed);
                    otherPlayer.setVictoryPoints(victoryPoints);
                }
            }
            data.getPrinter().printMessage("The player " + playerID + " has played " + data.getLeaderFromID(leaderPlayedID));
        }
    }

    @Override
    public String getClassName() {
        return className;
    }
}
