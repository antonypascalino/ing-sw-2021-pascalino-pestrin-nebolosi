package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Board.Board;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.Updates.Update;
import it.polimi.ingsw.model.Table.Table;
import it.polimi.ingsw.controller.Request.MarketResource;
import it.polimi.ingsw.controller.Request.MarketRequest;
import it.polimi.ingsw.model.card.*;
import it.polimi.ingsw.model.Board.WareHouse;
import it.polimi.ingsw.model.Board.StrongBox;

import java.util.ArrayList;

/**
 * The type representing a player of the game.
 * <p>
 * It is an abstract class used by almost every method that involves a player.
 * It is always related to an instantiable type of player through its <em>original</em> attribute.
 * Every method, when called, calls the relative <em>original</em>'s method which can be differently
 * implemented based on the <em>original</em>'s type.
 */
public abstract class Player {
    Player original;

    /**
     * Set the reference to the {@link Game}.
     *
     * @param game the game's reference.
     */
    public void setGame(Game game) {
        original.setGame(game);
    }

    /**
     * Gets player's nickname.
     *
     * @return the nickname.
     */
    public String getNickName() {
        return original.getNickName();
    }

    /**
     * Gets player's victory points.
     *
     * @return the victory points.
     */
    public int getVictoryPoints() {
        return original.getVictoryPoints();
    }

    /**
     * Gets the reference to the player's {@link Board}.
     *
     * @return the {@link Board}.
     */
    public Board getBoard() {
        return original.getBoard();
    }

    /**
     * Gets the reference to the {@link Game} the player is playing.
     *
     * @return the {@link Game}.
     */
    public Game getGame() {
        return original.getGame();
    }

    /**
     * Adds {@link LeaderCard}s to the player.
     * Called at the beginning of the match when
     * the player chooses two out of the four leader cards.
     *
     * @param card the card that needs to be added to the player.
     */
    public void addLeaderCard(LeaderCard card) {
        original.addLeaderCard(card);
    }

    /**
     * Add victory points to the player.
     *
     * @param vp the victory points added throughout the game.
     */
    public void addVictoryPoints(int vp) {
        original.addVictoryPoints(vp);
    }

    /**
     * Gets {@link LeaderCard}.
     *
     * @return the leader cards
     */
    public ArrayList<LeaderCard> getLeaderCards() {
        return original.getLeaderCards();
    }

    /**
     * Produce {@link Resource}s using the production power having the ID received as parameter.
     *
     * @param cardID the card's ID
     */
    public void produce(String cardID) {
        original.produce(cardID);
    }

    /**
     * Check if player has space in the received as parameter level in his deposits.
     *
     * @param res   the {@link Resource}.
     * @param level the level in the deposits
     * @return true if there's space, false otherwise.
     */
    public boolean checkSpace(Resource res, int level) {
        return original.checkSpace(res, level);
    }

    /**
     * Add a {@link Resource} in the specific level received as parameter.
     *
     * @param level the level of deposits.
     * @param res   the {@link Resource}.
     */
    public void addResource(int level, Resource res) {
        original.addResource(level, res);
    }

    /**
     * Remove the {@link Resource} from the place received as parameter.
     *
     * @param place the place containing resources.
     * @param res   the {@link Resource}.
     */
    public void removeResource(Resource res, String place) {
        original.removeResource(res, place);
    }

    /**
     * Gets player's reference to the {@link Table}.
     *
     * @return the table's reference.
     */
    public Table getTable() {
        return original.getTable();
    }

    /**
     * Check if all the {@link Resource}s in the {@link MarketResource}s sent by the player
     * in his {@link MarketRequest} match the relatives {@link Resource}s in the market;
     * it also considered if player has played or not a {@link ChangeResource} {@link LeaderCard}.
     *
     * @param requestedRes the requested resources from the player.
     * @param marketRes    the market resource actually in the market.
     * @return true if the resources match or if the player has the right {@link ChangeResource} {@link LeaderCard},
     * false otherwise.
     */
    public boolean checkMarketRes(ArrayList<Resource> requestedRes, ArrayList<Resource> marketRes) {
        return original.checkMarketRes(requestedRes, marketRes);
    }

    /**
     * Check if the player actually has the level received as parameter in his deposit, considering even
     * the possible extra deposit.
     *
     * @param level the level
     * @return the boolean
     */
    public boolean checkLevel(int level) {
        return original.checkLevel(level);
    }

    /**
     * Gets all player's resources contained in his {@link WareHouse}, his {@link StrongBox},
     * even, if present, the extra deposits.
     *
     * @return all the {@link Resource}s.
     */
    public ArrayList<Resource> getAllResources() {
        return original.getAllResources();
    }

    /**
     * Called when the player wants to move {@link Resource}s in his {@link WareHouse}, considering
     * even the possible extra deposits. It moves resource from an level to another in the player's deposits.
     *
     * @param originLevel the origin level
     * @param destLevel   the dest level
     */
    public void switchLevels(int originLevel, int destLevel) {
        original.switchLevels(originLevel, destLevel);
    }

    /**
     * Given a {@link DevCard} on the {@link Table}, check if the player can buy
     * it based on his {@link Resource} and the possible {@link Discount}s
     * he could have.
     *
     * @param devCard      the development card it wants to buy.
     * @param allPlayerRes all player's resources,
     * @return true if the player can buy the card, false otherwise.
     */
    public boolean canBuy(DevCard devCard, ArrayList<Resource> allPlayerRes) {
        return original.canBuy(devCard, allPlayerRes);
    }

    /**
     * Get the {@link LeaderCard} having the ID received as parameter searching between those the player owns.
     *
     * @param cardID the leader card's ID.
     * @return the reference to the {@link LeaderCard}, null if the player doesn't own it.
     */
    public LeaderCard getLeaderFromID(String cardID) {
        return original.getLeaderFromID(cardID);
    }

    /**
     * Gets the ID of all production powers owns by the player, even considering the possible {@link ExtraProd}
     * the player could have.
     *
     * @return all the productions' ID.
     */
    public ArrayList<String> getProductionID() {
        return original.getProductionID();
    }

    /**
     * Set the reference to the {@link Game}'s {@link Table}.
     *
     * @param tbl the table reference.
     */
    public void setTable(Table tbl) {
        original.setTable(tbl);
    }

    /**
     * Gets the IDs of all the {@link LeaderCard} owns by the player.
     *
     * @return all the Leader card's ID.
     */
    public ArrayList<String> getLeadersID() {
        return original.getLeadersID();
    }

    /**
     * Gets all the deposits owns by the player, even considering the possible {@link ExtraDeposit}
     * he could have.
     *
     * @return all the productions' ID.
     */
    public ArrayList<Resource[]> getDeposits() {
        return original.getDeposits();
    }

    /**
     * Receives an {@link Update} and sends it, through the server, to the player on client side.
     *
     * @param update the update to send.
     */
    public void notifyView(Update update) {
        original.notifyView(update);
    }

    public boolean checkSwitch(int originLevel, int destLevel) {
        return original.checkSwitch(originLevel, destLevel);
    }
}

