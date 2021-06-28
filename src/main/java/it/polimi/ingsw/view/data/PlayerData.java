package it.polimi.ingsw.view.data;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.Request.MarketResource;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.MainMenu;
import it.polimi.ingsw.view.Printer;
import it.polimi.ingsw.view.clientCards.ClientDevCard;
import it.polimi.ingsw.view.clientCards.ClientLeaderCard;

import java.util.ArrayList;


/**
 * The type representing a player of the game, client side.
 * <p>
 * It is an abstract class used by almost every method that involves a player, client side.
 * It is always related to an instantiable type of player through its <em>originalData</em> attribute.
 * Every method, when called, calls the relative <em>originalData</em>'s method which can be differently
 * implemented based on the <em>originalData</em>'s type.
 */
public abstract class PlayerData {

    PlayerData originalData;

    /**
     * Based on the action already done or not by the player in his turn,
     * return a list of all the action the player can still do.
     *
     * @return an ArrayList of {@link TurnState}
     */
    public ArrayList<TurnState> turnStateFilter() {
        return originalData.turnStateFilter();
    }

    /**
     * Called when the player wants to produce: it, based on player's resource and production powers,
     * return all the production he can use to produce.
     * <p>
     * It is called after every single production, because, for example, the
     * player could use more then one production at the start of his turn but after using one of them
     * he couldn't use any more production.
     *
     * @param mapped an ArrayList of {@link MappedResource} containing all player's resources and their position.
     * @return the ArrayList of all the production's ID the player can use at the moment.
     */
    public ArrayList<String> slotCardsFilter(ArrayList<MappedResource> mapped) {
        return originalData.slotCardsFilter(mapped);
    }

    /**
     * When the player has to use some resources, these are received as parameter and, for each of them, the method
     * search where the player has it, looking in all his deposits; finally he returns an ArrayList of {@link MappedResource}
     * containing all the resources needed and their location so the player can choose which ones to use.
     *
     * @param resources the resources needed to buy a development card or to produce.
     * @return the ArrayList of {@link MappedResource}s.
     */
    public ArrayList<MappedResource> createMappedRes(ArrayList<Resource> resources) {
        return originalData.createMappedRes(resources);
    }

    /**
     * Gets all resources owned by the player specifying their location (using {@link MappedResource}s),
     * considering even the possible extra deposits.
     *
     * @return an ArrayList of {@link MappedResource}
     */
    public ArrayList<MappedResource> allResources() {
        return originalData.allResources();
    }

    /**
     * Gets a {@link ClientDevCard} having the cardID received as parameter. It searches in player's allGameCards attribute.
     *
     * @param cardID the {@link ClientDevCard}'s ID.
     * @return the {@link ClientDevCard}.
     */
    public ClientDevCard getCardFromID(String cardID) {
        return originalData.getCardFromID(cardID);
    }

    /**
     * Gets a {@link ClientLeaderCard} having the cardID received as parameter. It searches in player's allGameCards attribute.
     *
     * @param cardID the {@link ClientLeaderCard}'s ID.
     * @return the {@link ClientLeaderCard}.
     */
    public ClientLeaderCard getLeaderFromID(String cardID) {
        return originalData.getLeaderFromID(cardID);
    }

    public Resource[][] getMarket() {
        return originalData.getMarket();
    }

    /**
     * Called when the player takes resources from market.
     * <p>
     * First of all the obtained resources are filtered by the Faith points and by the
     * white marbles, if the player is a {@link ChangeResData} these ones are changed.
     * Then asks to the player in which levels he wants to put every resource or even he wants to discard some of them,
     * if there is no space the resource are automatically discarded.
     * Finally returns an ArrayList containing the resources and their destination in the WareHouse, or in an extra
     * deposit if the player is an {@link ExtraDepData}.
     *
     * @param res the resources gained from the market.
     * @return an ArrayList of {@link MarketResource}s.
     */
    public ArrayList<MarketResource> handleWarehouse(ArrayList<Resource> res) {
        return originalData.handleWarehouse(res);
    }

    /**
     * Called when the player wants to buy a development card: it, based on player's resource
     * return all the development cards' ID he can buy.
     * If the player is a {@link DiscountData} even the discount is considered.
     * <p>
     * @param mapped all player's resources and their location
     * @return all the development cards' ID the player can buy.
     */
    public ArrayList<String> tableCardsFilter(ArrayList<MappedResource> mapped) {
        return originalData.tableCardsFilter(mapped);
    }

    /**
     * When player buys a {@link ClientDevCard} this method shows him all the slots in which he can put the just bought
     * card; makes the player choose one of them and return it as Integer.
     *
     *
     * @param devID the bought {@link ClientDevCard}'s ID.
     * @return the slot in which the player wants to put the card.
     */
    public Integer handleSlots(String devID) {
        return originalData.handleSlots(devID);
    }

    /**
     * Gets all the deposits in which the player can puts resources takes from the market; if the player is
     * an {@link ExtraDepData} considers even the extra deposits.
     *
     * @return all the players' deposits.
     */
    public ArrayList<Resource[]> getDeposits() {
        return originalData.getDeposits();
    }

    /**
     * Used when the player wants to sort resources in his warehouse. It received the origin level from where the player
     * wants to start to move resources, shows to the player all the possible deposits in which can move those resources and make him
     * choose one of them. Finally return the number of the chosen level. If the player is an {@link ExtraDepData} even the
     * extra deposits are consider.
     *
     * @param origin the level from where the player wants to move resources.
     * @return the chosen level by the player to where puts the resources.
     */
    public int switchLevels(int origin) {
        return originalData.switchLevels(origin);
    }

    /**
     * Gets, among the leader cards owned by the player, those ones that can be played at the moment.
     *
     * @return the array list with all playable leader cards' ID.
     */
    public ArrayList<String> leaderCardsFilter() {
        return originalData.leaderCardsFilter();
    }

    /**
     * Once it's player's turn, it clear the {@link TurnState}'s list, so the player can start a new turn.
     */
    public void newTurn() {
        originalData.newTurn();
    }

    /**
     * Once the player made all the choices, this method receive the {@link Request} relative to the action and
     * sends it to the server.
     *
     * @param request the {@link Request}.
     */
    public void sendRequest(Request request) {originalData.sendRequest(request);}

    /**
     * If the player is an {@link ChangeResData}, changes all the empty resource in the ArrayList received as parameter,
     * if not returns the same resources received as parameters.
     *
     * @param res the resource to change, or not.
     * @return the possibly changed resource.
     */
    public ArrayList<Resource> changeEmpty(ArrayList<Resource> res) {
        return originalData.changeEmpty(res);
    }


    //All getters adn setters
    public ArrayList<String> getLeaders() {
        return originalData.getLeaders();
    }

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

    public void setFrontCardsID(ArrayList<String> frontCardsID) {
        originalData.setFrontCardsID(frontCardsID);
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

    public ArrayList<OtherPlayerData> getOtherPlayers(){
        return originalData.getOtherPlayers();
    }

    public ArrayList<Resource> getStrongBox(){
        return originalData.getStrongBox();
    }

    public int getFaithPoints() {
        return originalData.getFaithPoints();
    }

    public int getVictoryPoints() {
        return originalData.getVictoryPoints();
    }

    public ArrayList<String> getFrontCardsID() {
        return originalData.getFrontCardsID();
    }

    public ArrayList<String> getLeadersID() {
        return originalData.getLeadersID();
    }

    public ArrayList<String> getLeadersPlayedID() {
        return originalData.getLeadersPlayedID();
    }

    public ArrayList<String> getAllDevID() {
        return originalData.getAllDevID();
    }

    public Printer getPrinter() {
        return originalData.getPrinter();
    }

    public int getGameID() {
        return originalData.getGameID();
    }

    public void setGameID(int gameID) {
        originalData.setGameID(gameID);
    }

    public MainMenu getMenu() {
        return originalData.getMenu();
    }

    public ArrayList<TurnState> getTurnStates() {
        return originalData.getTurnStates();
    }

}
