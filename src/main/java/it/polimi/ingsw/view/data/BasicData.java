package it.polimi.ingsw.view.data;

import it.polimi.ingsw.controller.Request.MappedResource;
import it.polimi.ingsw.controller.Request.MarketResource;
import it.polimi.ingsw.controller.Request.Request;
import it.polimi.ingsw.view.LineClient;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.MainMenu;
import it.polimi.ingsw.view.clientCards.AllGameCards;
import it.polimi.ingsw.view.clientCards.ClientDevCard;
import it.polimi.ingsw.view.clientCards.ClientLeaderCard;
import it.polimi.ingsw.view.Printer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * The default type of a player client side, it extends {@link PlayerData} and it's the player without any
 * {@link ClientLeaderCard} played.
 */
public class BasicData extends PlayerData {
    private String playerID;
    private int gameID;
    private ArrayList<TurnState> turnStates;
    private ArrayList<Resource[]> wareHouse;
    private ArrayList<Resource> strongBox;
    private ArrayList<String> tableCardsID; //just the front table cards
    private int faithPoints;
    private int victoryPoints;
    private ArrayList<String> frontCardsID;  //3 front cards + basic
    private ArrayList<String> leadersID;
    private ArrayList<String> leadersPlayedID;
    private Resource[][] market;
    private Printer printer;
    private ArrayList<OtherPlayerData> otherPlayersData;
    private ArrayList<String> allDevID;
    private AllGameCards allGameCards;
    private MainMenu menu;
    private LineClient connection;

    /**
     * Instantiates a new {@link BasicData} setting his nickname and his reference to the server.
     * It also instantiates all the attributes
     *
     * @param playerID   the player's nickname.
     * @param connection the reference to the server.
     */
    public BasicData(String playerID, LineClient connection) {
        this.connection = connection;
        this.otherPlayersData = new ArrayList<>();
        printer = new Printer();
        this.turnStates = new ArrayList<>();
        this.wareHouse = new ArrayList<>();
        Resource[] tmp = new Resource[1];
        tmp[0] = Resource.EMPTY;
        wareHouse.add(tmp);
        tmp = new Resource[2];
        tmp[0] = Resource.EMPTY;
        tmp[1] = Resource.EMPTY;
        wareHouse.add(tmp);
        tmp = new Resource[3];
        tmp[0] = Resource.EMPTY;
        tmp[1] = Resource.EMPTY;
        tmp[2] = Resource.EMPTY;
        wareHouse.add(tmp);
        this.allDevID = new ArrayList<>();
        this.strongBox = new ArrayList<>();
        this.faithPoints = 0;
        this.victoryPoints = 0;
        this.frontCardsID = new ArrayList<>();
        this.leadersID = new ArrayList<>();
        this.leadersPlayedID = new ArrayList<>();
        this.playerID = playerID;
        menu = new MainMenu(this);
        allGameCards = new AllGameCards();
    }

    public ArrayList<TurnState> turnStateFilter() {
        ArrayList<TurnState> tmp = new ArrayList<>();
        //tmp.add(TurnState.QUIT);
        tmp.add(TurnState.CHECK_STATS);
        tmp.add(TurnState.PRODUCE);
        tmp.add(TurnState.BUY_DEV_CARD);
        tmp.add(TurnState.GET_FROM_MARKET);
        tmp.add(TurnState.DISCARD_LEADER_CARD);

        for (String s : leadersID) {
            if (getLeaderFromID(s).canBePlayed(this)) {
                tmp.add(TurnState.PLAY_LEADER_CARD);
                break;
            }
        }

        if (turnStates.contains(TurnState.BUY_DEV_CARD) || turnStates.contains(TurnState.PRODUCE) || turnStates.contains(TurnState.GET_FROM_MARKET)) {
            tmp.remove(TurnState.PRODUCE);
            tmp.remove(TurnState.BUY_DEV_CARD);
            tmp.remove(TurnState.GET_FROM_MARKET);
            tmp.add(TurnState.END_TURN);
        }

        if (turnStates.contains(TurnState.PLAY_LEADER_CARD)) {
            tmp.remove(TurnState.PLAY_LEADER_CARD);
            tmp.remove(TurnState.DISCARD_LEADER_CARD);
        }

        if (turnStates.contains(TurnState.DISCARD_LEADER_CARD)) {
            tmp.remove(TurnState.PLAY_LEADER_CARD);
            tmp.remove(TurnState.DISCARD_LEADER_CARD);
        }
        //you always have the basic production
        if (allResources().size() == 0) {
            tmp.remove(TurnState.PRODUCE);
        }

        boolean empty = true;
        for (int i = 0; i < wareHouse.size(); i++) {

            for (int k = 0; k < wareHouse.get(i).length; k++) {
                if (!wareHouse.get(i)[k].equals(Resource.EMPTY)) {
                    empty = false;
                    break;
                }
            }
        }
        if (!empty) {
            tmp.add(TurnState.MOVE_RESOURCE);
        } else {
            if (strongBox.size() == 0) {
                tmp.remove(TurnState.PRODUCE);
                tmp.remove(TurnState.BUY_DEV_CARD);
            }
        }
        if (leadersID.size() == 0) {
            tmp.remove(TurnState.DISCARD_LEADER_CARD);
            tmp.remove(TurnState.PLAY_LEADER_CARD);
        }
        return tmp;
    }

    public ArrayList<String> slotCardsFilter(ArrayList<MappedResource> mapped) {
        ArrayList<String> cloned = new ArrayList<>();
        cloned.addAll(frontCardsID);

        ArrayList<Resource> allRes = new ArrayList<>();
        for (MappedResource m : mapped) {
            allRes.add(m.getResource());
        }
        cloned.removeIf(card -> !allRes.containsAll(getCardFromID(card).getRequired()));
        cloned.add("BASIC");
        return cloned;
    }

    public ArrayList<MappedResource> allResources() {
        ArrayList<MappedResource> tmp = new ArrayList<>();
        for (Resource[] l : wareHouse) {
            for (Resource w : l) {
                MappedResource mappedW = new MappedResource(w, "warehouse");
                tmp.add(mappedW);
            }
        }
        for (Resource s : strongBox) {
            MappedResource mappedS = new MappedResource(s, "strongbox");
            tmp.add(mappedS);
        }
        return tmp;
    }

    public ArrayList<MappedResource> createMappedRes(ArrayList<Resource> res) {
        ArrayList<MappedResource> tmp = new ArrayList<>();
        ArrayList<MappedResource> toSelect = new ArrayList<>();
        ArrayList<MappedResource> mappedRes = new ArrayList<>();

        //gets all the res
        tmp.addAll(allResources());

        for (Resource re : res) {
            for (MappedResource map : tmp) {
                if (map.getResource().equals(re)) {
                    toSelect.add(map);
                }
            }
            MappedResource selected = printer.printMappedRes(toSelect);
            mappedRes.add(selected);
            tmp.remove(selected);
            toSelect.clear();
        }

        return mappedRes;
    }

    public ClientDevCard getCardFromID(String cardID) {
        for (ClientDevCard c : allGameCards.getAllDevCards()) {
            if (c.getCardID().equals(cardID)) {
                return c;
            }
        }
        return null;
    }

    public ClientLeaderCard getLeaderFromID(String cardID) {
        for (ClientLeaderCard l : allGameCards.getAllLeaderCards()) {
            if (l.getLeaderID().equals(cardID)) {
                return l;
            }
        }
        return null;
    }

    public Resource[][] getMarket() {
        return market;
    }

    public ArrayList<MarketResource> handleWarehouse(ArrayList<Resource> res) {
        ArrayList<Resource[]> wareHouseClone = new ArrayList<>();
        ArrayList<MarketResource> marketRes = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        ArrayList<Resource> wareHouseRes = new ArrayList<>();

        wareHouseClone.addAll(this.getDeposits());
        for (Resource[] lv : wareHouseClone) {
            wareHouseRes.addAll(Arrays.asList(lv));
        }
        for (Resource re : res) {
            tmp.clear();
            if (re.equals(Resource.EMPTY)) {
                MarketResource m = new MarketResource(re, -2);
                marketRes.add(m);
                continue;
            }

            if (re.equals(Resource.FAITH)) {
                MarketResource m = new MarketResource(re, -1);
                marketRes.add(m);
                continue;
            }

            for (int l = 0; l < wareHouseClone.size(); l++) {
                //if full
                if (!Arrays.stream(wareHouseClone.get(l)).anyMatch(r -> r.equals(Resource.EMPTY))) {
                    continue;
                }
                //if it has empty spaces
                if (Arrays.stream(wareHouseClone.get(l)).anyMatch(r -> r.equals(Resource.EMPTY))) {
                    //if totally empty
                    if (wareHouseClone.get(l)[0].equals(Resource.EMPTY)) {
                        if (!wareHouseRes.contains(re)) {
                            tmp.add(l);
                        }
                    }
                    //if it contains my resource
                    else if (wareHouseClone.get(l)[0] == re) {
                        tmp.add(l);
                    }
                }
            }
            if (tmp.size() == 0) {
                printer.printMessage("You have no space for " + re + ". It was discarded!");
                MarketResource mr = new MarketResource(re, -1);
                marketRes.add(mr);
                continue;
            }
            printer.printMessage("\nWhere do you wanna put " + re + "?");
            int wareHouseLevel = printer.printIntegers(tmp, false, true);
            MarketResource mr = new MarketResource(re, wareHouseLevel);
            if (wareHouseLevel == -1) {
                printer.printMessage("The resource " + re + " was discarded!");
                marketRes.add(mr);
                continue;
            } else printer.printMessage("The resource " + re + " " + "was put in level " + (wareHouseLevel + 1));
            marketRes.add(mr);
            for (int d = 0; d < wareHouseClone.get(wareHouseLevel).length; d++) {
                if (wareHouseClone.get(wareHouseLevel)[d] == Resource.EMPTY) {
                    wareHouseClone.get(wareHouseLevel)[d] = re;
                    wareHouseRes.add(re);
                    wareHouseRes.remove(Resource.EMPTY);
                    break;
                }
            }
        }
        return marketRes;
    }

    public ArrayList<Resource> changeEmpty(ArrayList<Resource> res) {
        return res;
    }

    public ArrayList<String> tableCardsFilter(ArrayList<MappedResource> mapped) {
        ArrayList<String> available = new ArrayList<>();
        ArrayList<Integer> cardLevel = new ArrayList<>(); // the levels of the cards which the player can buy
        cardLevel.add(1); //added because if there is no card I can't add its level+1
        ArrayList<Resource> allRes = new ArrayList<>();
        for (MappedResource m : mapped) {
            allRes.add(m.getResource());
        }
        for (String devCardID : getFrontCardsID()) {
            cardLevel.add(getCardFromID(devCardID).getLevel() + 1);
        }
        if (cardLevel.size() == 4) {
            cardLevel.remove(0);
        }
        //For every resource i have to check if the occurrences match
        for (String card : getFrontTableCardsID()) {
            boolean canBeBought = true;
            if (!cardLevel.contains(getCardFromID(card).getLevel())) {
                continue;
            }
            for (Resource res : getCardFromID(card).getPrice()) {
                //Check if they have the same number of res for every type, if it doesn't have the resource remove them
                if (Collections.frequency(allRes, res) < Collections.frequency(getCardFromID(card).getPrice(), res)) {
                    canBeBought = false;
                    break;
                }
            }
            if (canBeBought) available.add(card);
        }
        return available;
    }

    public Integer handleSlots(String devID) {
        ArrayList<Integer> slots = new ArrayList<>();
        for (int i = 0; i < frontCardsID.size(); i++) {
            if (getCardFromID(frontCardsID.get(i)).getLevel() < getCardFromID(devID).getLevel()) {
                slots.add(i);
            }
        }
        //It needs to stay out because if the size of frontcardsId is zero it never buys them
        //If the are empty spaces and the players is trying to buy a level 1 card it puts it in the first position
        if (frontCardsID.size() < 3) {
            if (getCardFromID(devID).getLevel() == 1)
                return frontCardsID.size();
        }
        printer.printMessage("In which slot do you wanna put the card?: ");
        return printer.printIntegers(slots, true, false);
    }

    public ArrayList<Resource[]> getDeposits() {
        return wareHouse;
    }

    public int switchLevels(int origin) {

        ArrayList<Integer> levels = new ArrayList<>();
        int counterOr = 0;

        //counts origin
        for (int co = 0; co < wareHouse.get(origin).length; co++) {
            if (wareHouse.get(origin)[co] == Resource.EMPTY) {
                break;
            }
            counterOr++;
        }

        for (int i = 0; i < wareHouse.size(); i++) {
            if (i != origin) {
                //counts destination
                int counterDes = 0;
                for (int cd = 0; cd < wareHouse.get(i).length; cd++) {
                    if (wareHouse.get(i)[cd] == Resource.EMPTY) {
                        break;
                    }
                    counterDes++;
                }
                if (counterOr <= wareHouse.get(i).length && counterDes <= wareHouse.get(origin).length && !(counterDes == 0 && counterOr == 0)) {
                    levels.add(i);
                }
            }
        }
        if (levels.size() == 0) {
            printer.printMessage("You can't switch this level!");
            return -1;
        }
        return printer.printIntegers(levels, false, false);
    }

    public ArrayList<String> leaderCardsFilter() {
        ArrayList<String> tmp = new ArrayList<>();
        for (String c : leadersID) {
            if (getLeaderFromID(c).canBePlayed(this)) {
                tmp.add(c);
            }
        }
        return tmp;
    }

    public ArrayList<String> getLeaders() {
        return leadersID;
    }

    @Override
    public void sendRequest(Request request) {
        try {
            connection.sendRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTurnStates(ArrayList<TurnState> turnStates) {
        this.turnStates = turnStates;
    }

    public void setWareHouse(ArrayList<Resource[]> wareHouse) {
        this.wareHouse = wareHouse;
    }

    public void setStrongBox(ArrayList<Resource> strongBox) {
        this.strongBox = strongBox;
    }

    public void setFaithPoints(int faithPoints) {
        this.faithPoints = faithPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public void setFrontCardsID(ArrayList<String> frontCardsID) {
        this.frontCardsID = frontCardsID;
    }

    public void setLeadersID(ArrayList<String> leadersID) {
        this.leadersID = leadersID;
    }

    public void setLeadersPlayedID(ArrayList<String> leadersPlayedID) {
        this.leadersPlayedID = leadersPlayedID;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setMarket(Resource[][] market) {
        this.market = market;
    }

    public void setFrontTableCardsID(ArrayList<String> frontTableCardsID) {
        this.tableCardsID = frontTableCardsID;
    }

    public ArrayList<OtherPlayerData> getOtherPlayers() {
        return otherPlayersData;
    }

    public ArrayList<Resource> getStrongBox() {
        return strongBox;
    }

    public int getFaithPoints() {
        return faithPoints;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public ArrayList<String> getFrontCardsID() {
        return frontCardsID;
    }

    public ArrayList<String> getLeadersID() {
        return leadersID;
    }

    public ArrayList<String> getLeadersPlayedID() {
        return leadersPlayedID;
    }

    public ArrayList<String> getAllDevID() {
        return allDevID;
    }

    public Printer getPrinter() {
        return printer;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public MainMenu getMenu() {
        return menu;
    }

    public ArrayList<String> getFrontTableCardsID() {
        return tableCardsID;
    }

    public void newTurn() {
        turnStates.clear();
    }

    @Override
    public void setNickname(String newNickname)
    {
        this.playerID = newNickname;
    }

    @Override
    public ArrayList<TurnState> getTurnStates() {
        return turnStates;
    }
}
