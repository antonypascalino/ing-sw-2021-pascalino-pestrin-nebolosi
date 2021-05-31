package it.polimi.ingsw.view.data;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.MarketResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.ClientDevCard;
import it.polimi.ingsw.view.ClientLeaderCard;

import java.util.ArrayList;

public abstract class PlayerData {
    //qua arrivano le cose dalla connection
    PlayerData originalData;

    public ArrayList<TurnState> turnStateFilter() {
        return originalData.turnStateFilter();
    }

    public ArrayList<String> slotCardsFilter(ArrayList<MappedResource> mapped) {
        return originalData.slotCardsFilter(mapped);
    }

    public ArrayList<MappedResource> createMappedRes(ArrayList<Resource> resources) {
        return originalData.createMappedRes(resources);
    }

    public ArrayList<MappedResource> allResources() {
        return originalData.allResources();
    }

    public ClientDevCard getCardFromID(String cardID) {
        return originalData.getCardFromID(cardID);
    }

    public ClientLeaderCard getLeaderFromID(String cardID) {
        return originalData.getLeaderFromID(cardID);
    }

    public Resource[][] getMarket() {
        return originalData.getMarket();
    }

    public ArrayList<MarketResource> handleWarehouse(ArrayList<Resource> res) {
        return originalData.handleWarehouse(res);
    }

    public ArrayList<String> tableCardsFilter(ArrayList<MappedResource> mapped) {
        return originalData.tableCardsFilter(mapped);
    }

    public Integer handleSlots(String devID) {
        return originalData.handleSlots(devID);
    }

    public ArrayList<Resource[]> getDeposits() {
        return originalData.getDeposits();
    }

    public int switchLevels(int origin) {
        return originalData.switchLevels(origin);
    }

    public ArrayList<String> leaderCardsFilter() {
        return originalData.leaderCardsFilter();
    }

    public ArrayList<String> getLeaders() {
        return originalData.getLeaders();
    }


    //getters & setters
    public void setTurnStates(ArrayList<TurnState> turnStates) {
        originalData.setTurnStates(turnStates);
    }

    public void setWareHouse(ArrayList<Resource[]> wareHouse) {
        originalData.setWareHouse(wareHouse);
    }

    public void setStrongBox(ArrayList<Resource> strongBox) {
        originalData.setStrongBox(strongBox);
    }

    public void setFaithPoints(int faithPoints) {
        originalData.setFaithPoints(faithPoints);
    }

    public void setVictoryPoints(int victoryPoints) {
        originalData.setVictoryPoints(victoryPoints);
    }

    public void setCardsID(ArrayList<String> cardsID) {
        originalData.setCardsID(cardsID);
    }

    public void setLeadersID(ArrayList<String> leadersID) {
        originalData.setLeadersID(leadersID);
    }

    public void setLeadersPlayedID(ArrayList<String> leadersPlayedID) {
        originalData.setLeadersPlayedID(leadersPlayedID);
    }

    public String getPlayerID() {
        return originalData.getPlayerID();
    }

    public ArrayList<String> getFrontTableCardsID() {
        return originalData.getFrontTableCardsID();
    }

    public void setMarket(Resource[][] market) {
        originalData.setMarket(market);
    }

    public void setFrontTableCardsID(ArrayList<String> frontTableCardsID) {
        originalData.setFrontTableCardsID(frontTableCardsID);
    }

    public void checkOtherStats() {
        originalData.checkOtherStats();
    }

    public ArrayList<OtherPlayerData> getOtherPlayers(){
        return originalData.getOtherPlayers();
    }

    public ArrayList<Resource> getStrongBox(){
        return originalData.getStrongBox();
    }

    public ArrayList<String> getTableCardsID() {
        return originalData.getTableCardsID();
    }

    public int getFaithPoints() {
        return originalData.getFaithPoints();
    }

    public int getVictoryPoints() {
        return originalData.getVictoryPoints();
    }

    public ArrayList<String> getCardsID() {
        return originalData.getCardsID();
    }

    public ArrayList<String> getLeadersID() {
        return originalData.getLeadersID();
    }

    public ArrayList<String> getLeadersPlayedID() {
        return originalData.getLeadersPlayedID();
    }

}
