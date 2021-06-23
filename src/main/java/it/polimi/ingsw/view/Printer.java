package it.polimi.ingsw.view;

import it.polimi.ingsw.Request.MappedResource;
import it.polimi.ingsw.Request.MarketDimension;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Updates.EndgameUpdate;
import it.polimi.ingsw.model.Updates.PlayerVP;
import it.polimi.ingsw.model.Updates.Update;
import it.polimi.ingsw.view.data.OtherPlayerData;
import it.polimi.ingsw.view.data.PlayerData;
import it.polimi.ingsw.view.selections.MarketArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

/**
 * Everything that has to be shown on the player's CLI is printed out by this class.
 * It also read every player's input.
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
     * Prints the {@link MappedResource}s received as parameter and make player choose one of them.
     *
     * @param mappedRes the {@link MappedResource}s.
     * @return          the chosen {@link MappedResource}.
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
     * Prints all the possible action that can be done by the player at the moment and make him choose one of them.
     *
     * @param turnStates the actions the player can do.
     * @return the action chose by the player.
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
     * Prints all the production power the player can use when wants to produce and make him choose one of them.
     *
     * @param cardID the IDs of the productions the player can use.
     * @param data   the data
     * @return the production power's string the player chose.
     */
    public String printDevCardID(ArrayList<String> cardID, PlayerData data) {
        Scanner inputs = new Scanner(System.in);
        String selection = "";

        while (true) {
            for (int i = 0; i < cardID.size(); i++) {
                if(cardID.get(i).equals("BASIC"))
                    System.out.println("[" + (i + 1) + "] " + "Basic production");
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

    /**
     * Prints all the leader cards the player can choose and make him choose one of them.
     *
     * @param cardID the IDs of the leader card the player chose.
     * @param data   the player that has to do the choice.
     * @return the leader card's string the player chose.
     */
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
     * Used to make player choose one of warehouse levels when he takes resources from market or to make player
     * choose one of the board's slot when he buy a development card.
     *
     * @param ints  the number of slot or warehouse level.
     * @param slots true if player has to choose a slot, false if has to choose a warehouse level.
     * @param canDiscard true if is possible to discard the resource, false if not.
     * @return the choice.
     */
    public int printIntegers(ArrayList<Integer> ints, boolean slots, boolean canDiscard) {
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
                if (canDiscard) System.out.println("[" + (actions + 1) + "] " + "Discard" );
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
     * Ask the player which resource it needs using a number as input from the stdin.
     *
     * @param resource the resources that the player might choose.
     * @return the resource chosen by the user.
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
     * Print market's matrix and ask the player to choose one between row or column and then the index.
     *
     * @param matrix the market situation at the moment.
     * @return a {@link MarketArray}.
     */
    public MarketArray printMatrix(Resource[][] matrix) {
        Scanner inputs = new Scanner(System.in);
        String selection = "";
        ArrayList<Resource> res = new ArrayList<Resource>();
        System.out.println("     1       2       3       4");
        for (int r = 0; r < matrix.length; r++) {
            System.out.print( (r + 1) + "  ");
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c].inLine() + " ");
            }
            System.out.println(" <");
        }
        System.out.println("     ^       ^       ^       ^");
        System.out.println("Free one: " + getFreeOne(matrix) + "\n");
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
     * Used when it needs to simply print a question which required an Yes/No answer.
     *
     * @return the true if the answer is Yes, false if it is no.
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
     * Used when player wants to see his status or other players' to ask which one wants to see.
     *
     * @return true if wants to see his, false if wants to see other players'.
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
     * Print other player's status.
     *
     * @param data the player who wants to see other players' status.
     */
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

    /**
     * Print player's status.
     *
     * @param data the player.
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
     * Used at the start phase of the game when player has to choose 2 out of 4 leader card: print them and makes
     * the player choose.
     *
     * @param leadersToChoose the 4 leader cards to choose.
     * @param data            the player.
     * @return the 2 leader card chosen.
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
     * Used at the start phase of the game when player can choose some resources to add to his warehouse. Print them and makes
     * the player choose.
     *
     * @return the resource
     */
    public Resource chooseResource() {
        Scanner input = new Scanner(System.in);

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
                default : System.out.println("Invalid input!");
            }
        }
    }

    /**
     * At the end of the game prints the winner and each player's victory points.
     *
     * @param endgameUpdate the {@link EndgameUpdate}.
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
     * Used to print a simple message to the player, doesn't need any input.
     *
     * @param message the message to print.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Used to simple print the market's matrix situation, doesn't need any input.
     *
     * @param matrix the market's matrix.
     */
    public void viewMarket(Resource[][] matrix) {
        ArrayList<Resource> res = new ArrayList<Resource>();
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c].inLine() + " ");
            }
            System.out.println("");
        }
        System.out.println("Free one: " + getFreeOne(matrix) + "\n");
    }

    /**
     * Used to simple print the player's warehouse situation, doesn't need any input.
     *
     * @param warehouse the player's warehouse.
     */
    public void printWareHouse(ArrayList<Resource[]> warehouse) {
        for(int i = 0; i< warehouse.size(); i++)
        {
            System.out.print("Level " +(i+1)+ ": ");
            for(int k = 0; k<warehouse.get(i).length; k++)
            {
                System.out.print(warehouse.get(i)[k].inLine() + " ");
            }
            System.out.println();
        }

    }

    /**
     * From market's situation derives the free one resource, in this way is not necessary to
     * send the information into the {@link Update}.
     *
     * @param market the market situation.
     * @return the free one.
     */
    public Resource getFreeOne(Resource[][] market) {
        ArrayList<Resource> marketRes = new ArrayList<>();
        for (Resource[] dimension : market) {
            for (Resource res : dimension) {
                marketRes.add(res);
            }
        }
        if (Collections.frequency(marketRes, Resource.EMPTY) == 3) return Resource.EMPTY;
        else if (Collections.frequency(marketRes, Resource.FAITH) == 0) return Resource.FAITH;
        else if (Collections.frequency(marketRes, Resource.GOLD) == 1) return Resource.GOLD;
        else if (Collections.frequency(marketRes, Resource.SHIELD) == 1) return Resource.SHIELD;
        else if (Collections.frequency(marketRes, Resource.STONE) == 1) return Resource.STONE;
        else return Resource.SERVANT;
    }
}
