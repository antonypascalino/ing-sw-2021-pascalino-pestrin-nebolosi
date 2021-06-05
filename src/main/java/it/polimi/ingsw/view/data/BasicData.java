package it.polimi.ingsw.view.data;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.Request.MarketResource;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.client.LineClient;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Board.WareHouse;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Updates.Update;
import it.polimi.ingsw.model.card.LeaderCard;
import it.polimi.ingsw.view.MainMenu;
import it.polimi.ingsw.view.clientCards.AllGameCards;
import it.polimi.ingsw.view.clientCards.ClientDefaultCreator;
import it.polimi.ingsw.view.clientCards.ClientDevCard;
import it.polimi.ingsw.view.clientCards.ClientLeaderCard;
import it.polimi.ingsw.view.Printer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BasicData extends PlayerData {
    private String playerID;
    private int gameID;
    private ArrayList<TurnState> turnStates;
    private ArrayList<Resource[]> wareHouse;
    private ArrayList<Resource> strongBox;
    private ArrayList<String> tableCardsID; //just the front table cards
    private int faithPoints;
    private int victoryPoints;
    private ArrayList<String> frontCardsID;  //3 front cards + basic + extraProd
    private ArrayList<String> leadersID;
    private ArrayList<String> leadersPlayedID;
    private Resource[][] market;
    private Printer printer;
    private ArrayList<OtherPlayerData> otherPlayersData;
    private ArrayList<String> allDevID;
    private AllGameCards allGameCards;
    private MainMenu menu;
    private LineClient connection;

    public BasicData(String playerID, LineClient connection) {
        this.connection = connection;

        printer = new Printer();
        this.turnStates = new ArrayList<>();
        this.wareHouse = new ArrayList<Resource[]>();
        Resource[] tmp = new Resource[1];
        tmp[0]=Resource.EMPTY;
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

        this.strongBox = new ArrayList<Resource>();
        this.faithPoints = 0;
        this.victoryPoints = 0;
        this.frontCardsID = new ArrayList<String>();
        this.leadersID = new ArrayList<String>();
        this.playerID = playerID;
        menu = new MainMenu(this);
        allGameCards = new AllGameCards(ClientDefaultCreator.produceClientDevCard(), ClientDefaultCreator.produceClientLeaderCard());
    }

    public ArrayList<TurnState> turnStateFilter(){
        ArrayList<TurnState> tmp = new ArrayList<TurnState>();
        //tmp.add(TurnState.QUIT);
        tmp.add(TurnState.CHECK_STATS);
        tmp.add(TurnState.PRODUCE);
        tmp.add(TurnState.BUY_DEV_CARD);
        tmp.add(TurnState.GET_FROM_MARKET);
        tmp.add(TurnState.DISCARD_LEADER_CARD);

        if(turnStates.contains(TurnState.BUY_DEV_CARD) || turnStates.contains(TurnState.PRODUCE) || turnStates.contains(TurnState.GET_FROM_MARKET)){
            tmp.remove(TurnState.PRODUCE);
            tmp.remove(TurnState.BUY_DEV_CARD);
            tmp.remove(TurnState.GET_FROM_MARKET);
            tmp.add(TurnState.END_TURN);
        }

        if(turnStates.contains(TurnState.PLAY_LEADER_CARD)){
            tmp.remove(TurnState.PLAY_LEADER_CARD);
            tmp.remove(TurnState.DISCARD_LEADER_CARD);
        }

        if(turnStates.contains(TurnState.DISCARD_LEADER_CARD)){
            tmp.remove(TurnState.PLAY_LEADER_CARD);
            tmp.remove(TurnState.DISCARD_LEADER_CARD);
        }

        boolean empty = true;
        for(int i = 0; i < wareHouse.size(); i++){

            for(int k = 0; k < wareHouse.get(i).length; k++){
                if(!wareHouse.get(i)[k].equals(Resource.EMPTY)){
                    empty = false;
                    break;
                }
            }
        }
        if(!empty){
            tmp.add(TurnState.MOVE_RESOURCE);
        }
        else{
            if(strongBox.size() == 0){
                tmp.remove(TurnState.PRODUCE);
            }
            if(strongBox.size() == 0 && frontCardsID.size() == 0){
                tmp.remove(TurnState.PLAY_LEADER_CARD);
                tmp.remove(TurnState.BUY_DEV_CARD);

            }
        }

        if(leadersID.size() == 0){
            tmp.remove(TurnState.DISCARD_LEADER_CARD);
            tmp.remove(TurnState.PLAY_LEADER_CARD);
        }

        return tmp;
    }

    public ArrayList<String> slotCardsFilter(ArrayList<MappedResource> mapped){
        ArrayList<String> cloned = new ArrayList<String>();
        cloned.addAll(frontCardsID);
        ArrayList<Resource> allRes = new ArrayList<Resource>();
        for(MappedResource m : mapped){
            allRes.add(m.getResource());
        }

        cloned.removeIf(card -> !allRes.containsAll(getCardFromID(card).getRequired()));
        return cloned;
    }

    public ArrayList<MappedResource> allResources(){
        ArrayList<MappedResource> tmp = new ArrayList<MappedResource>();
        Resource[] level = new Resource[3];
        for(Resource[] l : wareHouse) {
            for (Resource w : level){
                MappedResource mappedW = new MappedResource(w, "warehouse");
                tmp.add(mappedW);
            }
        }
        for(Resource s : strongBox){
            MappedResource mappedS = new MappedResource(s, "strongbox");
            tmp.add(mappedS);
        }
        return tmp;
    }

    public ArrayList<MappedResource> createMappedRes(ArrayList<Resource> res){
        ArrayList<MappedResource> tmp = new ArrayList<MappedResource>();
        ArrayList<MappedResource> toSelect = new ArrayList<MappedResource>();
        ArrayList<MappedResource> mappedRes = new ArrayList<MappedResource>();

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

    public ClientDevCard getCardFromID(String cardID){
        for(ClientDevCard c : allGameCards.getAllDevCards()){
            if(c.getCardID().equals(cardID)){
                return c;
            }
        }
        return null;
    }

    public ClientLeaderCard getLeaderFromID(String cardID){
        for(ClientLeaderCard l : allGameCards.getAllLeaderCards()){
            if(l.getLeaderID().equals(cardID)){
                return l;
            }
        }
        return null;
    }

    public Resource[][] getMarket(){
        return market;
    }

    public ArrayList<MarketResource> handleWarehouse(ArrayList<Resource> res) {
        ArrayList<Resource[]> wareHouseClone = new ArrayList<Resource[]>();
        ArrayList<MarketResource> marketRes = new ArrayList<MarketResource>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        ArrayList<Resource> wareHouseRes = new ArrayList<Resource>();

        wareHouseClone.addAll(this.getDeposits());
        for (Resource[] lv : wareHouseClone) {
            wareHouseRes.addAll(Arrays.asList(lv));
        }
        for (Resource re : res) {
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
                //se è pieno
                if (!Arrays.stream(wareHouseClone.get(l)).anyMatch(r -> r.equals(Resource.EMPTY))) {
                   l++;
                }
                //se ha degli spazi vuoti
                if (Arrays.stream(wareHouseClone.get(l)).anyMatch(r -> r.equals(Resource.EMPTY))) {
                    //se è vuoto
                    if (wareHouseClone.get(l)[0].equals(Resource.EMPTY)) {
                        if(!wareHouseRes.contains(re)){
                            wareHouseClone.get(l)[0] = re;
                            wareHouseRes.add(re);
                            wareHouseRes.remove(Resource.EMPTY);
                            tmp.add(l);
                        }

                    }
                    //se ha la mia risorsa
                    else if (wareHouseClone.get(l)[0] == re) {
                        tmp.add(l);
                        for(int d = 0; d < wareHouseClone.get(l).length; d++){
                            if(wareHouseClone.get(l)[d] == Resource.EMPTY){
                                wareHouseClone.get(l)[d] = re;
                                wareHouseRes.add(re);
                                wareHouseRes.remove(Resource.EMPTY);
                                break;
                            }
                        }
                    }
                }
            }
            printer.printMessage("\nWhere do you wanna put " + re + "?");
            int wareHouseLevel = printer.printIntegers(tmp, false);
            MarketResource mr = new MarketResource(re, wareHouseLevel);
            printer.printMessage("The resource " + re + " " + "was put in level " + wareHouseLevel);
            marketRes.add(mr);
        }
        return marketRes;
    }


    public ArrayList<String> tableCardsFilter(ArrayList<MappedResource> mapped){

        ArrayList<String> cloned = new ArrayList<String>();
        cloned.addAll(tableCardsID);
        ArrayList<Resource> allRes = new ArrayList<Resource>();
        for(MappedResource m : mapped){
            allRes.add(m.getResource());
        }
        cloned.removeIf(card -> !allRes.containsAll(getCardFromID(card).getPrice()));
        return cloned;
    }

    public Integer handleSlots(String devID){
        ArrayList<Integer> slots = new ArrayList<Integer>();
        for(int i = 0; i < frontCardsID.size(); i++){
            if(getCardFromID(frontCardsID.get(i)).getLevel() < getCardFromID(devID).getLevel()){
                slots.add(i);
            }
            if(frontCardsID.size() < 3){
                for(int j = i; j < 3 - frontCardsID.size(); j++){
                    slots.add(j);
                }
            }
        }
        return printer.printIntegers(slots, true);
    }

    public ArrayList<Resource[]> getDeposits(){
        return wareHouse;
    }

    public int switchLevels(int origin){

        ArrayList<Integer> levels = new ArrayList<Integer>();
        int counterOr = 0;
        int counterDes = 0;

        //conta origine
        for(int co = 0; co < wareHouse.get(origin).length; co++) {
            if (wareHouse.get(origin)[co] == null) {
                break;
            }
            counterOr = co;
        }

        for(int i = 0; i < wareHouse.size(); i++) {
            //conta destinazione
            for(int cd = 0; cd < wareHouse.get(i).length; cd++){
                if(wareHouse.get(i)[cd] == null){
                    break;
                }
                counterDes = cd;
            }
            if (counterOr <= wareHouse.get(i).length && counterDes <= wareHouse.get(origin).length) {
                levels.add(i);
            }
        }
        return printer.printIntegers(levels, false);
    }

    public ArrayList<String> leaderCardsFilter() {
        ArrayList<String> tmp = new ArrayList<String>();
        for (String c : leadersID)
        {
            if(getLeaderFromID(c).canBePlayed(this)) {
                tmp.add(c);
            }
        }
        return tmp;
    }

    public ArrayList<String> getLeaders() {
        return leadersID;
    }

    @Override
    public void sendRequest(Request request)
    {
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

    public void checkOtherStats() {
        for(OtherPlayerData p : otherPlayersData){
            printer.printOtherStats(p);
        }
    }

    public ArrayList<OtherPlayerData> getOtherPlayers(){
        return otherPlayersData;
    }

    public ArrayList<Resource> getStrongBox() {
        return strongBox;
    }

    public ArrayList<String> getTableCardsID() {
        return tableCardsID;
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

    public void refresh(Update update) {
        update.handleUpdate(this);
    }

    public ArrayList<String> getFrontTableCardsID() {
        return frontCardsID;
    }
}
