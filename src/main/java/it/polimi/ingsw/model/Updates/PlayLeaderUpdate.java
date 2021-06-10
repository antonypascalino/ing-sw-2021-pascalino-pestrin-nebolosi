package it.polimi.ingsw.model.Updates;

import it.polimi.ingsw.controller.TurnState;
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
    private ArrayList<TurnState> turnStates;

    public PlayLeaderUpdate(String playerID, String leaderPlayedID, ArrayList<String> leadersPlayed, ArrayList<String> leadersNOTPlayed, Resource powerResource, int victoryPoints, ArrayList<TurnState> turnStates) {
        className = this.getClass().getName();
        this.playerID = playerID;
        this.leaderPlayedID = leaderPlayedID;
        this.leadersNOTPlayed = leadersNOTPlayed;
        this.leadersPlayed = leadersPlayed;
        this.powerResource = powerResource;
        this.victoryPoints = victoryPoints;
        this.turnStates = turnStates;
    }

    public void wrapPlayer(Observer observer) {
        if(observer.getData().getPlayerID().equals(playerID))
        {
            String leaderType = leaderPlayedID.substring(0,3); //considers only the firsts 3 char of the string
            PlayerData newPlayer = null;
            switch (leaderType) {

                case ("PRO") : {
                    ArrayList<String> leaderProdID = new ArrayList<>();
                    ArrayList<Resource> prodRequired = new ArrayList<>();
                    leaderProdID.add(leaderPlayedID);
                    prodRequired.add(powerResource);
                    newPlayer = new ExtraProdData(leaderProdID, prodRequired, observer.getData());
                    break;
                }
                case ("CNG") : {
                    ArrayList<Resource> changes = new ArrayList<>();
                    changes.add(powerResource);
                    newPlayer = new ChangeResData(changes, observer.getData());
                    break;
                }
                case ("DIS") : {
                    ArrayList<Resource> discount = new ArrayList<>();
                    discount.add(powerResource);
                    newPlayer = new DiscountData(discount, observer.getData());
                    break;
                }
                case ("DEP") : {
                    ArrayList<Resource> placeable = new ArrayList<>();
                    placeable.add(powerResource);
                    newPlayer = new ExtraDepData(observer.getData(), placeable);
                    break;
                }

            }
            observer.setPlayer(newPlayer);
            observer.getData().getMenu().setData(newPlayer);
        }

    }

    @Override
    public void handleUpdate(PlayerData data) {
        if (data.getPlayerID().equals(playerID)) {
            data.setLeadersPlayedID(leadersPlayed);
            data.setLeadersID(leadersNOTPlayed);
            data.setVictoryPoints(victoryPoints);
            data.setTurnStates(turnStates);
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
