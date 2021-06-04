package it.polimi.ingsw.view;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.Request.MarketDimension;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Updates.EndgameUpdate;
import it.polimi.ingsw.model.Updates.PlayerVP;
import it.polimi.ingsw.view.data.OtherPlayerData;
import it.polimi.ingsw.view.data.PlayerData;
import it.polimi.ingsw.view.selections.MarketArray;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Printer {

    public Resource printResources(ArrayList<Resource> res){
        Scanner inputs = new Scanner(System.in);
        String selection = "";

        for (int i = 0; i < res.size(); i++) {
            System.out.println("[" + (i + 1) + "]" + "" + res.get(i));
        }

        System.out.println("Enter selection: ");
        selection = inputs.nextLine();
        int index = Integer.parseInt(selection);
        if(res.get(index - 1).equals(Resource.CHOICE)){
            ArrayList<Resource> choices = new ArrayList<Resource>();
            choices.add(Resource.GOLD);
            choices.add(Resource.SERVANT);
            choices.add(Resource.SHIELD);
            choices.add(Resource.STONE);
        }
        return res.get(index -1);
    }

    public MappedResource printMappedRes(ArrayList<MappedResource> mappedRes){
        Scanner inputs = new Scanner(System.in);
        String selection = "";

        for (int i = 0; i < mappedRes.size(); i++) {
            System.out.println("[" + (i + 1) + "]" + "" + mappedRes.get(i).getResource() + "" + mappedRes.get(i).getPlace());
        }

        System.out.println("Enter selection: ");
        selection = inputs.nextLine();
        int index = Integer.parseInt(selection);

        return mappedRes.get(index -1);

    }

    public TurnState printTurnStates(ArrayList<TurnState> turnStates){
        Scanner inputs = new Scanner(System.in);
        String selection = "";

        for (int i = 0; i < turnStates.size(); i++) {
            System.out.println("[" + (i + 1) + "]" + "" + turnStates.get(i));
        }

        System.out.println("Enter selection: ");
        while(selection.equals(""))
        {
            selection = inputs.nextLine();
        }

        int index = Integer.parseInt(selection);
        return turnStates.get(index -1);
    }

    public String printCardID(ArrayList<String> cardID){
        Scanner inputs = new Scanner(System.in);
        String selection = "";

        for (int i = 0; i < cardID.size(); i++) {
            System.out.println("[" + (i + 1) + "]" + "" + cardID.get(i));
        }

        System.out.println("Enter selection: ");
        selection = inputs.nextLine();
        int index = Integer.parseInt(selection);

        return cardID.get(index - 1);
    }

    public int printIntegers(ArrayList<Integer> ints, boolean slots){
        Scanner inputs = new Scanner(System.in);
        String selection = "";
        if(slots){
            for (int i = 0; i < ints.size(); i++) {
                System.out.println("[" + (i + 1) + "]" + "" + "slot" + "" + ints.get(i));
            }
        }
        else{
            for (int i = 0; i < ints.size(); i++) {
                System.out.println("[" + (i + 1) + "]" + "" + "warehouse level" + "" + ints.get(i));
            }

        }
        System.out.println("Enter selection: ");
        selection = inputs.nextLine();
        int index = Integer.parseInt(selection);
        return ints.get(index - 1);
    }

    public MarketArray printMatrix(Resource[][] matrix) {
        Scanner inputs = new Scanner(System.in);
        String selection = "";
        ArrayList<Resource> res = new ArrayList<Resource>();
        //da fare meglio la matrice a schermo
        //System.out.println("1  2   3  4");
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println("");
        }
        System.out.println("Select if you want a row or a column: ");
        System.out.println("[1] row");
        System.out.println("[2] column");

        selection = inputs.nextLine();


        switch (selection) {
            case ("1"):
                System.out.println("Select your row: ");
                selection = inputs.nextLine();
                int indexRow = Integer.parseInt(selection);
                for (int k = 0; k < matrix[indexRow - 1].length; k++) {
                    res.add(matrix[indexRow - 1][k]);
                }
                return new MarketArray(res, MarketDimension.ROW, indexRow);

            case ("2"):
                System.out.println("Select your column: ");
                selection = inputs.nextLine();
                int indexColumn = Integer.parseInt(selection);
                for (int z = 0; z < matrix.length; z++) {
                    res.add(matrix[z][indexColumn - 1]);
                }
                return new MarketArray(res, MarketDimension.COL, indexColumn);
        }
        return null;
    }

    public boolean askQuestion(){
        Scanner inputs = new Scanner(System.in);
        String selection = "";
        System.out.println("Continue?");
        System.out.println("[1] Yes");
        System.out.println("[2] No");

        selection = inputs.nextLine();
        switch(selection){
            case("1"):
                return true;
            case("2"):
                return false;
        }
        return false;
    }

    public void printOtherStats(OtherPlayerData data){
        System.out.println("Player ID: " + data.getPlayerID());
        System.out.println("Warehouse:\n" + data.getWareHouse());
        System.out.println("Strongbox:\n" + data.getStrongBox());
        System.out.println("Slots:\n" + data.getSlotFrontCards());
        System.out.println("Faith Points: " + data.getFaithPoints());
        System.out.println("Victory Points: " + data.getVictoryPoints());
        for(String s: data.getPlayedLeadersID()){
            System.out.println("Leader ID: " + s);
            System.out.println(""); //depositi extra o sconti o bla bla bla
        }
    }

    public void printMyStats(PlayerData data) {
        System.out.println("Player ID: " + data.getPlayerID());
        System.out.println("Market:\n" + data.getMarket());
        System.out.println("Front cards:\n" + data.getFrontTableCardsID());
        System.out.println("Warehouse:\n" + data.getDeposits());
        System.out.println("Strongbox:\n" + data.getStrongBox());
        System.out.println("Slots:\n" + data.getFrontCardsID());
        System.out.println("Faith Points: " + data.getFaithPoints());
        System.out.println("Victory Points: " + data.getVictoryPoints());
        for(String s: data.getLeadersID()){
            System.out.println("Leader ID: " + s);
            System.out.println(""); //depositi extra o sconti o bla bla bla
        }

    }

    public ArrayList<String> chooseLeaderCard(ArrayList<String> leadersToChoose, PlayerData data) {
        ArrayList<String> chosen = new ArrayList<String>();
        Scanner inputs = new Scanner(System.in);
        int index = 1;
        System.out.println("Choose 2 Leaders Cards between this 4:");
        for(String s : leadersToChoose) {
            System.out.println("[" + index + "]");
            System.out.println(data.getLeaderFromID(s).toString());
        }
        System.out.println("What is your first choice?");
        chosen.add(leadersToChoose.remove(inputs.nextInt() - 1));
        index = 1;
        System.out.println("What is your second choice?");
        for(String s : leadersToChoose) {
            System.out.println("[" + index + "]");
            System.out.println(data.getLeaderFromID(s).toString());
            index++;
        }
        chosen.add(leadersToChoose.remove(inputs.nextInt() - 1));

        return chosen;
    }

    public Resource chooseResource() {
        Scanner input = new Scanner(System.in);
        boolean validInput;

        while(true) {
            System.out.println("You can choose a Resource to add to your Warehouse, which one do you want?");
            System.out.println("[1] GOLD");
            System.out.println("[2] STONE");
            System.out.println("[3] SHIELD");
            System.out.println("[4] SERVANT");

            switch (input.nextInt()) {
                case (1): return Resource.GOLD;
                case (2): return Resource.STONE;
                case (3): return Resource.SHIELD;
                case (4): return Resource.SERVANT;
                default : System.out.println("Invalid input");
            }
        }
    }

    public void endgame(EndgameUpdate endgameUpdate) {
        System.out.println(endgameUpdate.getWinner().toUpperCase(Locale.ROOT) + "WON!");
        System.out.println("Here's the points:");
        for (PlayerVP player : endgameUpdate.getFinalPlayersVPS()) {
            System.out.println(player.getPlayerID() + ": " + player.getVictoryPoints());
        }
        //chiudere connessione e tutto baci baci ciao ciao
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
