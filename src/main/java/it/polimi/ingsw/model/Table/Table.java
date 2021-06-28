package it.polimi.ingsw.model.Table;

import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

/**
 * The table with all the {@link DevCard}s and the {@link Market} of a {@link Game}.
 */
public class Table {
    private DevCard[][][] avaibleDev;
    private int stack[][];
    public Market market;

    /**
     * Instantiates a new {@link Table} sorting the {@link DevCard}s and the {@link Market}'s {@link Resource}s.
     * @param gameCards all the {@link DevCard}s int the game.
     */
    public Table(ArrayList<DevCard> gameCards)
    {
        market = new Market(DefaultCreator.getGameRes());
        int row, col;
        stack=new int[4][3]; //
        int coordinates[]; //Coordinates of the card that is being inserted
        avaibleDev = new DevCard[4][3][4];
        for(DevCard dev:gameCards)
        {
            coordinates = getCoordinate(dev.getColor(), dev.getLevel());
            row = coordinates[0];
            col = coordinates[1];
            avaibleDev[row][col][stack[row][col]]=dev;
            stack[row][col]++;
        }
    }

    /**
     * Gets all the front {@link DevCard}s in the {@link Table}.
     *
     * @return a matrix with the {@link DevCard}.
     */
    public DevCard[][] getTop() {
        DevCard[][] result = new DevCard[4][3];
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                //If there are no cards in a stack return a null
                if (stack[r][c] == 0)
                    result[r][c] = null;
                else
                    result[r][c] = avaibleDev[r][c][0];
            }
        }
        return result;
    }


    /**
     * Called when a {@link Player} needs to buy a {@link DevCard} knowing color and level parameters:
     * removes it from the table updating the stack.
     *
     * @param color the color of the {@link DevCard}.
     * @param level the level of the {@link DevCard}.
     * @return the bought {@link DevCard}.
     */
    public DevCard buyDev(String color, int level)
    {
        DevCard result;
        int[] coordinates = getCoordinate(color, level);
        int row = coordinates[0];
        int col = coordinates[1];
        //If the stack for that type is empty return a null pointer
        if(stack[row][col]==0)
            return null;
        //Get the top card as result
        result=avaibleDev[row][col][0];

        //Shift the other cards in the stack
        for(int i=0; i< stack[row][col]-1; i++)
        {
            avaibleDev[row][col][i]=avaibleDev[row][col][i+1];
        }
        stack[row][col]--;

        return result;
    }

    /**
     * Used to determining the coordinates on the {@link Table} of a {@link DevCard} based on his color and level.
     *
     * @param color the {@link DevCard}'s color.
     * @param level the {@link DevCard}'s level.
     * @return an array where the first position is the row and the second is the columns.
     */
    public static int[] getCoordinate(String color, int level) {
        int[] result = new int[2];
        //Select the correct row
        if (color.equals("GREEN"))
            result[0] = 0;
        if (color.equals("BLUE"))
            result[0] = 1;
        if (color.equals("YELLOW"))
            result[0] = 2;
        if (color.equals("PURPLE"))
            result[0] = 3;

        //Select the correct colum
        result[1] = level - 1;

        return result;
    }

    /**
     * Called when it needs to see which is the front {@link DevCard} of a specific color and level.
     *
     * @param color the {@link DevCard}'s color.
     * @param level the {@link DevCard}'s level.
     * @return the {@link DevCard}.
     */
    public DevCard seeDev(String color, int level) {
        DevCard result;
        int[] coordinates = getCoordinate(color, level);
        int row = coordinates[0];
        int col = coordinates[1];
        //If the stack for that type is empty return a null pointer
        if(stack[row][col]==0)
            return null;
        //Get the top card as result
        result=avaibleDev[row][col][0];
        return result;
    }

    /**
     * From the front {@link DevCard}s on the Table gets the one having the specified ID.
     *
     * @param cardID the ID of the {@link DevCard} it wants to get.
     * @return the {@link DevCard}, null if the card is not on the front ones.
     */
    public DevCard getDevFromID(String cardID) {
        DevCard[][] topCard = this.getTop();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                if (topCard[row][col] == null) return null;
                if (topCard[row][col].getCardID().equals(cardID)) {
                    return topCard[row][col];
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String result = "";
        for(int r=0; r<4; r++)
        {
            for(int c=0; c<3; c++)
            {
                    result+=" " +avaibleDev[r][c][0].getCardID();
            }
            result+="\n\r";
        }

        return result;
    }

    /**
     * Gets an ArrayList containing the IDs of all the front {@link DevCard} on the table.
     *
     * @return the ArrayList with the IDs.
     */
    public ArrayList<String> getFrontIDs() {
        ArrayList<String> result = new ArrayList<String>();
        for(int r=0; r<4; r++)
        {
            for(int c=0; c<3; c++)
            {
                if(stack[r][c]!=0)
                    result.add(avaibleDev[r][c][0].getCardID());
            }
        }
        return result;
    }

    /**
     * Get a matrix having the number of {@link DevCard} in each position.
     *
     * @return the matrix.
     */
    public int[][] getStack() { return stack; }
}
