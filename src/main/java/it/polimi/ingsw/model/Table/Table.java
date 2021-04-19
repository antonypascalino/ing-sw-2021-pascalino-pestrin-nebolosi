package it.polimi.ingsw.model.Table;

import it.polimi.ingsw.model.Cards.DevCard;

import java.util.ArrayList;

/**
 * The type Table.
 */
public class Table {
    private DevCard[][][] avaibleDev;
    /**
     * The Stack.
     */
    int stack[][]; //Contains for each position the number of cards that are in that stack
    /**
     * The Market.
     */
    public Market market;

    /**
     * Instantiates a new Table.
     *
     * @param gameCards the game cards
     */
/*
    The array list sorts all the dev cards recived dividing them by level and color
     */
    public Table(ArrayList<DevCard> gameCards)
    {
        //MESCOLARE LE CARTE PRIMA DI PASSRLE QUA
        int row, col;
        stack=new int[4][3]; //Counters used for memorizing the indexs, initializated to 0 by default
        int cordinates[]; //Cordinates of the card that is being inserted
        avaibleDev = new DevCard[4][3][4];
        for(DevCard dev:gameCards)
        {
            cordinates = getCordinate(dev.getColor(), dev.getLevel());
            row = cordinates[0];
            col = cordinates[1];
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
     * Buy dev dev card.
     *
     * @param color the color
     * @param level the level
     * @return the dev card
     */
/*
    Used when the player needs to buy a devCard knowing the color and the level
    @result returns the card and the removes it from the table updating the stack value
     */
    public DevCard buyDev(String color, int level)
    {
        DevCard result;
        int cordinates[] = getCordinate(color, level);
        int row = cordinates[0];
        int col = cordinates[1];
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
    private static int[] getCordinate(String color, int level)
    {
        int[] result=new int[2];
        //Select the correct row
        switch(color)
        {
            case "green":
                result[0]=0;
            case "blue":
                result[0]=1;
            case "yellow":
                result[0]=2;
            case "purple":
                result[0]=3;
        }

        //Select the correct colum
        result[1]=level-1;

        return result;
    }

}
