package it.polimi.ingsw.view.data;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.Request.MarketResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Board.WareHouse;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.card.LeaderCard;
import it.polimi.ingsw.view.clientCards.AllGameCards;
import it.polimi.ingsw.view.clientCards.ClientDefaultCreator;
import it.polimi.ingsw.view.clientCards.ClientDevCard;
import it.polimi.ingsw.view.clientCards.ClientLeaderCard;
import it.polimi.ingsw.view.Printer;

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


    public BasicData(String playerID) {
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

        allGameCards = new AllGameCards(ClientDefaultCreator.produceClientDevCard(), ClientDefaultCreator.produceClientLeaderCard());
    }

    public ArrayList<TurnState> turnStateFilter(){
        ArrayList<TurnState> tmp = new ArrayList<TurnState>();
        tmp.add(TurnState.CHECK_STATS);
        tmp.add(TurnState.PRODUCE);
        tmp.add(TurnState.BUY_DEV_CARD);
        tmp.add(TurnState.GET_FROM_MARKET);
        tmp.add(TurnState.DISCARD_LEADER_CARD);
        tmp.add(TurnState.QUIT);

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

        if(turnStates.contains(TurnState.PLAY_LEADER_CARD)){
            tmp.remove(TurnState.PLAY_LEADER_CARD);
            tmp.remove(TurnState.DISCARD_LEADER_CARD);
        }

        if(wareHouse.size() > 0){
            tmp.add(TurnState.MOVE_RESOURCE);
        }

        if(leadersID.size() == 0){
            tmp.remove(TurnState.DISCARD_LEADER_CARD);
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

//    public void removeMappedResource(ArrayList<MappedResource> mapped) {
//        for (MappedResource m : mapped) {
//            if (m.getPlace().equals("warehouse")) {
//                for (Resource[] l : wareHouse) {
//                    Resource[] current = l;
//                    for (int j = 0; j < current.length; j++)
//                        if (current[j].equals(m.getResource())) {
//                            current[j] = null;
//                        }
//                }
//            }
//            if (m.getPlace().equals("strongbox")) {
//                strongBox.remove(m.getResource());
//            }
//        }
//    }

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
        ArrayList<MarketResource> marketRes = new ArrayList<MarketResource>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        ArrayList<Resource> wareHouseRes = new ArrayList<Resource>();

        for (Resource[] lv : wareHouse) {
            wareHouseRes.addAll(Arrays.asList(lv));
        }

        for (int p = 0; p < res.size(); p++) {
            if (res.get(p).equals(Resource.EMPTY)) {
                MarketResource m = new MarketResource(res.get(p), -2);
                marketRes.add(m);
                p++;
            }

            if (res.get(p).equals(Resource.FAITH)) {
                MarketResource m = new MarketResource(res.get(p), -1);
                marketRes.add(m);
                p++;
            }

            for (int l = 0; l < wareHouse.size(); l++) {
                Resource resource = res.get(p);
                //se è pieno
                if (!Arrays.stream(wareHouse.get(l)).anyMatch(null)) {
                    continue;
                }
                //se ha degli spazi vuoti
                if (Arrays.stream(wareHouse.get(l)).anyMatch(null)) {
                    //se è vuoto
                    if (wareHouse.get(l)[0] == null) {
                        boolean empty = true;
                        for (int x = 0; x < wareHouse.size(); x++) {
                            if (x != l) {
                                if (Arrays.stream(wareHouse.get(l)).anyMatch(z -> z.equals(resource))) {
                                    empty = false;
                                    break;
                                }
                            }
                        }
                        if (empty) {
                            tmp.add(l);
                        }
                    }
                    //se ha la mia risorsa
                    else if (wareHouse.get(l)[0] == res.get(p)) {
                        tmp.add(l);
                    }
                }
            }
            int wareHouseLevel = printer.printIntegers(tmp, false);
            MarketResource mr = new MarketResource(res.get(p), wareHouseLevel);
            System.out.println("The resource " + res.get(p) + "" + "was put in level " + wareHouseLevel);
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
}
