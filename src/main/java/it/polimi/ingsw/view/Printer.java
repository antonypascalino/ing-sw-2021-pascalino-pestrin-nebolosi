package it.polimi.ingsw.view;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.Request.MarketDimension;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Updates.EndgameUpdate;
import it.polimi.ingsw.model.Updates.PlayerVP;
import it.polimi.ingsw.view.clientCards.ClientDevCard;
import it.polimi.ingsw.view.data.OtherPlayerData;
import it.polimi.ingsw.view.data.PlayerData;
import it.polimi.ingsw.view.selections.MarketArray;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * The type Printer.
 */
public class Printer {

    /**
     * Print resources resource.
     *
     * @param res the res
     * @return the resource
     */
    public Resource printResources(ArrayList<Resource> res) {
        Scanner inputs = new Scanner(System.in);
        String selection;

        int actions = 0;
        while (true) {
            for (int i = 0; i < res.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + res.get(i));
                actions = i + 1;
            }
            System.out.println("Enter selection: ");
            selection = inputs.nextLine();
            try {
                int index = Integer.parseInt(selection);
                if (index > actions || index <= 0) {
                    System.out.println("Invalid input!");
                } else {
                    if (res.get(index - 1).equals(Resource.CHOICE)) {
                        ArrayList<Resource> choices = new ArrayList<Resource>();
                        choices.add(Resource.GOLD);
                        choices.add(Resource.SERVANT);
                        choices.add(Resource.SHIELD);
                        choices.add(Resource.STONE);
                    }
                    return res.get(index - 1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }
        }
    }

    /**
     * Print mapped res mapped resource.
     *
     * @param mappedRes the mapped res
     * @return the mapped resource
     */
    public MappedResource printMappedRes(ArrayList<MappedResource> mappedRes) {
        Scanner inputs = new Scanner(System.in);
        String selection = "";

        while (true) {
            int actions = 0;
            for (int i = 0; i < mappedRes.size(); i++) {
                if(mappedRes.get(i).getResource().equals(Resource.EMPTY)){
                    continue;
                }
                System.out.println("[" + (i + 1) + "] " + mappedRes.get(i).getResource() + " " + mappedRes.get(i).getPlace());
                actions = i + 1;
            }
            System.out.println("Enter selection: ");
            selection = inputs.nextLine();
            try {
                int index = Integer.parseInt(selection);
                if (index > actions || index <= 0) {
                    System.out.println("Invalid input!");
                } else return mappedRes.get(index - 1);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }
        }
    }


    /**
     * Print turn states turn state.
     *
     * @param turnStates the turn states
     * @return the turn state
     */
    public TurnState printTurnStates(ArrayList<TurnState> turnStates) {
        Scanner inputs = new Scanner(System.in);
        String selection = "";
        int actions = 0;

        while (true) {
            System.out.println("What do you wanna do?");
            for (int i = 0; i < turnStates.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + turnStates.get(i));
                actions = i + 1;
            }
            System.out.println("Enter selection: ");
            selection = inputs.nextLine();
            try {
                int index = Integer.parseInt(selection);
                if ( index > actions || index <= 0) {
                    System.out.println("Invalid input!");
                } else return turnStates.get(index - 1);;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }
        }
    }

    /**
     * Print card id string.
     *
     * @param cardID the card id
     * @param data   the data
     * @return the string
     */
    public String printDevCardID(ArrayList<String> cardID, PlayerData data) {
        Scanner inputs = new Scanner(System.in);
        String selection = "";

        while (true) {
            for (int i = 0; i < cardID.size(); i++) {
                if(cardID.get(i).equals("BASIC"))
                    System.out.println("[" + (i + 1) + "] "+"Basic production");
                else if (!cardID.get(i).contains("PROD"))
                System.out.println("[" + (i + 1) + "] " + data.getCardFromID(cardID.get(i)).toString());
                else System.out.println("[" + (i + 1) + "] " + data.getLeaderFromID(cardID.get(i)).toString());
            }
            System.out.println("Enter selection: ");
            selection = inputs.nextLine();
            try {
                int index = Integer.parseInt(selection);
                if (index <= 0 || index > cardID.size()) {
                    System.out.println("Invalid input");
                } else return cardID.get(index - 1);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
    }

    public String printLeaderCardID(ArrayList<String> cardID, PlayerData data) {
        Scanner inputs = new Scanner(System.in);
        String selection = "";

        while (true) {
            for (int i = 0; i < cardID.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + data.getLeaderFromID(cardID.get(i)).toString());
            }
            System.out.println("Enter selection: ");
            selection = inputs.nextLine();
            try {
                int index = Integer.parseInt(selection);
                if (index <= 0 || index > cardID.size()) {
                    System.out.println("Invalid input");
                } else return cardID.get(index - 1);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
    }

    /**
     * Print integers int.
     *
     * @param ints  the ints
     * @param slots the slots
     * @return the int
     */
    public int printIntegers(ArrayList<Integer> ints, boolean slots) {
        Scanner inputs = new Scanner(System.in);
        String selection = "";
        int actions = 0;

        while (true) {
            if (slots) {
                for (int i = 0; i < ints.size(); i++) {
                    System.out.println("[" + (i + 1) + "]" + " " + "slot" + " " + (ints.get(i) + 1));
                    actions = i + 1;
                }
            } else {
                for (int i = 0; i < ints.size(); i++) {
                    System.out.println("[" + (i + 1) + "]" + " " + "warehouse level" + " " + (ints.get(i) + 1));
                    actions = i + 1;
                }
                System.out.println("[" + (actions + 1) + "] " + "Discard" );
            }
            System.out.println("Enter selection: ");
            selection = inputs.nextLine();
            try {
                int index = Integer.parseInt(selection);
                if (index > actions + 1 || index <= 0) {
                    System.out.println("Invalid input!");
                } else {
                    if (index == actions + 1) return -1;
                    return ints.get(index - 1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }
        }
    }

    /**
     * Ask the player which resource it needs using a number as input from the stdin
     * @param resource the resources that the player might choose
     * @return the resource chosen by the user
     */
    public Resource printResource(ArrayList<Resource> resource) {
        Scanner inputs = new Scanner(System.in);
        String selection = "";
        int actions = 0;

        while (true) {
            for (int i = 0; i < resource.size(); i++) {
                    System.out.println("[" + (i + 1) + "]" + " "+ resource.get(i));
                    actions = i + 1;
                }
            System.out.println("Enter selection: ");
            selection = inputs.nextLine();
            try {
                int index = Integer.parseInt(selection);
                if (index > actions + 1 || index <= 0) {
                    System.out.println("Invalid input!");
                } else {
                    return resource.get(index - 1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }
        }
    }
    /**
     * Print matrix market array.
     *
     * @param matrix the matrix
     * @return the market array
     */
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
        while (true) {
            System.out.println("Select if you want a row or a column: ");
            System.out.println("[1] Row");
            System.out.println("[2] Column");

            selection = inputs.nextLine();
            if (!selection.equals("1") && !selection.equals("2")) {
                System.out.println("Invalid input");
            } else break;
        }

        switch (selection) {
            case ("1"):
                while (true) {
                    System.out.println("Select your row: ");
                    selection = inputs.nextLine();
                    try {
                        int indexRow = Integer.parseInt(selection);
                        if (indexRow <= 0 || indexRow > matrix.length) {
                            System.out.println ("Invalid input!");
                        }
                        else {
                            for (int k = 0; k < matrix[indexRow - 1].length; k++) {
                                res.add(matrix[indexRow - 1][k]);
                            }
                            System.out.println("You chose these resources: " + res);
                            return new MarketArray(res, MarketDimension.ROW, indexRow - 1);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input!");
                    }

                }
            case ("2"):
                while (true) {
                    System.out.println("Select your column: ");
                    selection = inputs.nextLine();
                    try {
                        int indexColumn = Integer.parseInt(selection);
                        if (indexColumn <= 0 || indexColumn > matrix[0].length) {
                            System.out.println("Invalid input!");
                        }
                        else {
                            for (int z = 0; z < matrix.length; z++) {
                                res.add(matrix[z][indexColumn - 1]);
                            }
                            System.out.println("You chose these resources: " + res);
                            return new MarketArray(res, MarketDimension.COL, indexColumn - 1);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input!");
                    }
                }
        }
        return null;
    }

    /**
     * Ask question boolean.
     *
     * @return the boolean
     */
    public boolean askQuestion() {
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

    /**
     * Choose stats boolean.
     *
     * @return the boolean
     */
    public boolean chooseStats() {
        Scanner inputs = new Scanner(System.in);
        String selection = "";
        System.out.println("Whose stats do you wanna see?");
        System.out.println("[1] Yours");
        System.out.println("[2] Others players' stats");

        while (true) {
            selection = inputs.nextLine();
            switch (selection) {
                case ("1"):
                    return true;
                case ("2"):
                    return false;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }

    /**
     * Print other stats.
     *
     * @param data the data
     */
    public void printOtherStats(OtherPlayerData data){
        System.out.println("Player ID: " + data.getPlayerID());
        System.out.println("Warehouse:\n" );
        System.out.println("Strongbox:\n" + data.getStrongBox());
        System.out.println("Slots:\n" + data.getSlotFrontCards());
        System.out.println("Faith Points: " + data.getFaithPoints());
        System.out.println("Victory Points: " + data.getVictoryPoints());
        for(String s: data.getPlayedLeadersID()){
            System.out.println("Leader ID: " + s);
            System.out.println(""); //depositi extra o sconti o bla bla bla
        }
    }

    /**
     * Print my stats.
     *
     * @param data the data
     */
    public void printMyStats(PlayerData data) {
        System.out.println("\nPLAYER ID: " + data.getPlayerID());
        System.out.println("\nMARKET:");
        viewMarket(data.getMarket());
        System.out.println("\nTABLE FRONT CARDS:\n");
        for (String cardID : data.getFrontTableCardsID()) {
            System.out.println(data.getCardFromID(cardID));
        }
        System.out.println("\nWAREHOUSE:");
        printWareHouse(data.getDeposits());
        System.out.println("\nSTRONGBOX:\n" + data.getStrongBox());
        System.out.println("\nSLOT FRONT CARD:\n" );
        for (String card : data.getFrontCardsID()) {
            System.out.println(data.getCardFromID(card));
        }
        System.out.println("\nFAITH POINTS: " + data.getFaithPoints());
        System.out.println("\nVICTORY POINTS: " + data.getVictoryPoints() + "\n");
        System.out.println("LEADER CARDS NOT PLAYED:\n");
        for(String s: data.getLeadersID()){
            System.out.println(data.getLeaderFromID(s) + "\n");
        }
        System.out.println();
        System.out.println("LEADER CARDS PLAYED:\n");
        for(String s: data.getLeadersPlayedID()){
            System.out.println(data.getLeaderFromID(s) + "\n");
        }
        System.out.println();
    }

    /**
     * Choose leader card array list.
     *
     * @param leadersToChoose the leaders to choose
     * @param data            the data
     * @return the array list
     */
    public ArrayList<String> chooseLeaderCard(ArrayList<String> leadersToChoose, PlayerData data) {
        ArrayList<String> chosen = new ArrayList<String>();
        Scanner inputs = new Scanner(System.in);
        int index = 1;
        System.out.println("\nChoose 2 Leaders Cards between this 4:");
        for(String s : leadersToChoose) {
            System.out.print("\n[" + index + "] ");
            System.out.println(data.getLeaderFromID(s).toString() + "\n");
            index++;
        }
        System.out.println("What is your first choice?");
        chosen.add(leadersToChoose.remove(inputs.nextInt() - 1));
        index = 1;
        System.out.println("What is your second choice?");
        for(String s : leadersToChoose) {
            System.out.print("\n[" + index + "] ");
            System.out.println(data.getLeaderFromID(s).toString());
            index++;
        }
        chosen.add(leadersToChoose.remove(inputs.nextInt() - 1));

        return chosen;
    }

    /**
     * Choose resource resource.
     *
     * @return the resource
     */
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

    /**
     * Endgame.
     *
     * @param endgameUpdate the endgame update
     */
    public void endgame(EndgameUpdate endgameUpdate) {
        System.out.println(endgameUpdate.getWinner().toUpperCase(Locale.ROOT) + " WON!");
        System.out.println("Here's the points:");
        for (PlayerVP player : endgameUpdate.getFinalPlayersVPS()) {
            System.out.println(player.getPlayerID() + ": " + player.getVictoryPoints());
        }
        System.exit(0);
    }

    /**
     * Print message.
     *
     * @param message the message
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * View market.
     *
     * @param matrix the matrix
     */
    public void viewMarket(Resource[][] matrix) {
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
    }

    /**
     * Print ware house.
     *
     * @param warehouse the warehouse
     */
    public void printWareHouse(ArrayList<Resource[]> warehouse) {
        for(int i = 0; i< warehouse.size(); i++)
        {
            System.out.print("Level " +(i+1)+ ": ");
            for(int k = 0; k<warehouse.get(i).length; k++)
            {
                System.out.print(warehouse.get(i)[k]+" ");
            }
            System.out.println();
        }

    }
}
