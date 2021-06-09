package it.polimi.ingsw.view.data;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.Request.MarketResource;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Updates.Update;
import it.polimi.ingsw.view.MainMenu;
import it.polimi.ingsw.view.Printer;
import it.polimi.ingsw.view.clientCards.ClientDevCard;
import it.polimi.ingsw.view.clientCards.ClientLeaderCard;

import java.util.ArrayList;

/**
 * The type Player data.
 */
public abstract class PlayerData {
    /**
     * The Original data.
     */
//qua arrivano le cose dalla connection
    PlayerData originalData;

    /**
     * Turn state filter array list.
     *
     * @return the array list
     */
    public ArrayList<TurnState> turnStateFilter() {
        return originalData.turnStateFilter();
    }

    /**
     * Slot cards filter array list.
     *
     * @param mapped the mapped
     * @return the array list
     */
    public ArrayList<String> slotCardsFilter(ArrayList<MappedResource> mapped) {
        return originalData.slotCardsFilter(mapped);
    }

    /**
     * Create mapped res array list.
     *
     * @param resources the resources
     * @return the array list
     */
    public ArrayList<MappedResource> createMappedRes(ArrayList<Resource> resources) {
        return originalData.createMappedRes(resources);
    }

    /**
     * All resources array list.
     *
     * @return the array list
     */
    public ArrayList<MappedResource> allResources() {
        return originalData.allResources();
    }

    /**
     * Gets card from id.
     *
     * @param cardID the card id
     * @return the card from id
     */
    public ClientDevCard getCardFromID(String cardID) {
        return originalData.getCardFromID(cardID);
    }

    /**
     * Gets leader from id.
     *
     * @param cardID the card id
     * @return the leader from id
     */
    public ClientLeaderCard getLeaderFromID(String cardID) {
        return originalData.getLeaderFromID(cardID);
    }

    /**
     * Get market resource [ ] [ ].
     *
     * @return the resource [ ] [ ]
     */
    public Resource[][] getMarket() {
        return originalData.getMarket();
    }

    /**
     * Handle warehouse array list.
     *
     * @param res the res
     * @return the array list
     */
    public ArrayList<MarketResource> handleWarehouse(ArrayList<Resource> res) {
        return originalData.handleWarehouse(res);
    }

    /**
     * Table cards filter array list.
     *
     * @param mapped the mapped
     * @return the array list
     */
    public ArrayList<String> tableCardsFilter(ArrayList<MappedResource> mapped) {
        return originalData.tableCardsFilter(mapped);
    }

    /**
     * Handle slots integer.
     *
     * @param devID the dev id
     * @return the integer
     */
    public Integer handleSlots(String devID) {
        return originalData.handleSlots(devID);
    }

    /**
     * Gets deposits.
     *
     * @return the deposits
     */
    public ArrayList<Resource[]> getDeposits() {
        return originalData.getDeposits();
    }

    /**
     * Switch levels int.
     *
     * @param origin the origin
     * @return the int
     */
    public int switchLevels(int origin) {
        return originalData.switchLevels(origin);
    }

    /**
     * Leader cards filter array list.
     *
     * @return the array list
     */
    public ArrayList<String> leaderCardsFilter() {
        return originalData.leaderCardsFilter();
    }

    /**
     * Gets leaders.
     *
     * @return the leaders
     */
    public ArrayList<String> getLeaders() {
        return originalData.getLeaders();
    }

    /**
     * Sets turn states.
     *
     * @param turnStates the turn states
     */
//getters & setters
    public void setTurnStates(ArrayList<TurnState> turnStates) {
        originalData.setTurnStates(turnStates);
    }

    /**
     * Sets ware house.
     *
     * @param wareHouse the ware house
     */
    public void setWareHouse(ArrayList<Resource[]> wareHouse) {
        originalData.setWareHouse(wareHouse);
    }

    /**
     * Sets strong box.
     *
     * @param strongBox the strong box
     */
    public void setStrongBox(ArrayList<Resource> strongBox) {
        originalData.setStrongBox(strongBox);
    }

    /**
     * Sets faith points.
     *
     * @param faithPoints the faith points
     */
    public void setFaithPoints(int faithPoints) {
        originalData.setFaithPoints(faithPoints);
    }

    /**
     * Sets victory points.
     *
     * @param victoryPoints the victory points
     */
    public void setVictoryPoints(int victoryPoints) {
        originalData.setVictoryPoints(victoryPoints);
    }

    /**
     * Sets front cards id.
     *
     * @param frontCardsID the front cards id
     */
    public void setFrontCardsID(ArrayList<String> frontCardsID) {
        originalData.setFrontCardsID(frontCardsID);
    }

    /**
     * Sets leaders id.
     *
     * @param leadersID the leaders id
     */
    public void setLeadersID(ArrayList<String> leadersID) {
        originalData.setLeadersID(leadersID);
    }

    /**
     * Sets leaders played id.
     *
     * @param leadersPlayedID the leaders played id
     */
    public void setLeadersPlayedID(ArrayList<String> leadersPlayedID) {
        originalData.setLeadersPlayedID(leadersPlayedID);
    }

    /**
     * Gets player id.
     *
     * @return the player id
     */
    public String getPlayerID() {
        return originalData.getPlayerID();
    }

    /**
     * Gets front table cards id.
     *
     * @return the front table cards id
     */
    public ArrayList<String> getFrontTableCardsID() {
        return originalData.getFrontTableCardsID();
    }

    /**
     * Sets market.
     *
     * @param market the market
     */
    public void setMarket(Resource[][] market) {
        originalData.setMarket(market);
    }

    /**
     * Sets front table cards id.
     *
     * @param frontTableCardsID the front table cards id
     */
    public void setFrontTableCardsID(ArrayList<String> frontTableCardsID) {
        originalData.setFrontTableCardsID(frontTableCardsID);
    }

    /**
     * Check other stats.
     */
    public void checkOtherStats() {
        originalData.checkOtherStats();
    }

    /**
     * Get other players array list.
     *
     * @return the array list
     */
    public ArrayList<OtherPlayerData> getOtherPlayers(){
        return originalData.getOtherPlayers();
    }

    /**
     * Get strong box array list.
     *
     * @return the array list
     */
    public ArrayList<Resource> getStrongBox(){
        return originalData.getStrongBox();
    }

    /**
     * Gets table cards id.
     *
     * @return the table cards id
     */
    public ArrayList<String> getTableCardsID() {
        return originalData.getTableCardsID();
    }

    /**
     * Gets faith points.
     *
     * @return the faith points
     */
    public int getFaithPoints() {
        return originalData.getFaithPoints();
    }

    /**
     * Gets victory points.
     *
     * @return the victory points
     */
    public int getVictoryPoints() {
        return originalData.getVictoryPoints();
    }

    /**
     * Gets front cards id.
     *
     * @return the front cards id
     */
    public ArrayList<String> getFrontCardsID() {
        return originalData.getFrontCardsID();
    }

    /**
     * Gets leaders id.
     *
     * @return the leaders id
     */
    public ArrayList<String> getLeadersID() {
        return originalData.getLeadersID();
    }

    /**
     * Gets leaders played id.
     *
     * @return the leaders played id
     */
    public ArrayList<String> getLeadersPlayedID() {
        return originalData.getLeadersPlayedID();
    }

    /**
     * Gets all dev id.
     *
     * @return the all dev id
     */
    public ArrayList<String> getAllDevID() {
        return originalData.getAllDevID();
    }

    /**
     * Gets printer.
     *
     * @return the printer
     */
    public Printer getPrinter() {
        return originalData.getPrinter();
    }

    /**
     * Gets game id.
     *
     * @return the game id
     */
    public int getGameID() {
        return originalData.getGameID();
    }

    /**
     * Sets game id.
     *
     * @param gameID the game id
     */
    public void setGameID(int gameID) {
        originalData.setGameID(gameID);
    }

    /**
     * Gets menu.
     *
     * @return the menu
     */
    public MainMenu getMenu() {
        return originalData.getMenu();
    }

    /**
     * Refresh.
     *
     * @param update the update
     */
    public void refresh(Update update) {
        originalData.refresh(update);
    }

    /**
     * Send request.
     *
     * @param request the request
     */
    public void sendRequest(Request request) {originalData.sendRequest(request);}

    /**
     * New turn.
     */
    public void newTurn() {
        originalData.newTurn();
    }

    public ArrayList<Resource> changeEmpty(ArrayList<Resource> res) {
        return originalData.changeEmpty(res);
    }
}
