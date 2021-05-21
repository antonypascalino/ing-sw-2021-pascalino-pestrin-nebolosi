package it.polimi.ingsw.model.Table;

import it.polimi.ingsw.model.card.DevCard;

import java.util.ArrayList;

/**
 * The type Table.
 */
public class Table {
    private DevCard[][][] avaibleDev;
    /**
     * The Stack which contains a number of cards in each position.
     */
    int stack[][];
    /**
     * Market reference.
     */
    public Market market;

    /**
     * Instantiates a new Table.
     * This method sorts the dev cards in the array list by level and colour.
     *
     * @param gameCards array list of dev cards
     */
/*

     */
    public Table(ArrayList<DevCard> gameCards)
    {
        //MESCOLARE LE CARTE PRIMA DI PASSARLE QUA
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
     * Get top dev card [ ] [ ].
     *
     * @return the dev card [ ] [ ]
     */
//Gives all the top card for each stack, that one in the 0 index
    public DevCard[][] getTop()
    {
        DevCard[][] result = new DevCard[4][3];
        for(int r=0; r<4; r++)
        {
            for(int c=0; c<3; c++)
            {
                //If there are no cards in a stack return a null
                if(stack[r][c]==0)
                    result[r][c]=null;
                else
                    result[r][c]=avaibleDev[r][c][0];
            }
        }

        return result;
    }


    /**
     * Called when the player needs to buy a devCard knowing color and level parameters
     *
     * @param color the color
     * @param level the level
     * @return the dev card and then it removes it from the table updating the stack
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


    /*
    Used for determating the cordinates on the map of a card based on his color and level
    @result an array where the first position is the row and the second is the columns
     */
    public static int[] getCoordinate(String color, int level)
    {
        int[] result=new int[2];
        //Select the correct row
        if(color.equals("GREEN"))
            result[0]=0;
        if(color.equals("BLUE"))
            result[0]=1;
        if(color.equals("YELLOW"))
            result[0]=2;
        if(color.equals("PURPLE"))
            result[0]=3;


        //Select the correct colum
        result[1]=level-1;

        return result;
    }

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

    public DevCard getDevFromID(String cardID) {
        DevCard[][] topCard = this.getTop();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                if (topCard[row][col].getCardID().equals(cardID)) {
                    return topCard[row][col];
                }
            }
        }
        return null;
        //Lancia eccezione: non c'è questa carta nel mercato;

    }



}
