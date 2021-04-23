package it.polimi.ingsw.model.Table;

import it.polimi.ingsw.model.Costants;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;
import java.util.Random;


/**
 * The type Market.
 */
public class Market {

    private Resource[][] currentSituation; //Market disposition
    private Resource freeOne; //Free resource

    /**
     * Instantiates a new Market.
     * This method receives 13 resources and then puts 12 of them in a matrix
     * in random order whilst keeping one outside as a "free one".
     *
     * @param gameRes 13 resources as an array list.
     *                Those resources are going to be in the market matrix for this game.
     */

    public Market(ArrayList<Resource> gameRes)
    {
        currentSituation = new Resource[Costants.MARKETROWS][Costants.MARKETCOLS];
        Random rnd = new Random();
        int i;

        //Solo per il debug, aggiungere ai test
        if(gameRes.size()!=13)
        {
           System.out.println("Not enough resources");
        }

        for (int r = 0; r< Costants.MARKETROWS; r++)
            for(int c = 0; c< Costants.MARKETCOLS; c++)
            {

                //Gets a new random passing the size of the array
                i = rnd.nextInt(gameRes.size());

                //Saves the random element in the array and delete it from the original arraylist
                currentSituation[r][c] = gameRes.get(i);
                gameRes.remove(i);
            }

        //Solo per il debug, aggiungere ai test
        if(gameRes.size()==1)
            System.out.println("Everything worked fine");

        freeOne = gameRes.get(0);
    }

    /**
     * Selects all the resources from a column, shifts it and inserts the free resource
     *
     * @param col a column of the market selected by the player
     * @return the array list of resources on the selected column
     */

    public ArrayList<Resource> getColumns(int col)
    {
        ArrayList<Resource> result = new ArrayList<Resource>();
        Resource tmp;
        for(int r = 0; r< Costants.MARKETROWS; r++)
        {
            tmp=currentSituation[r][col];
            result.add(tmp);
        }

        //Once the result array is ready move the market resources
        tmp=currentSituation[0][col];
        for(int r = 0; r< Costants.MARKETROWS-1; r++)
        {
           currentSituation[r][col] = currentSituation[r+1][col];
        }
        currentSituation[Costants.MARKETROWS-1][col]=freeOne;
        freeOne=tmp;
        return result;
    }

    /**
     * Selects all the resources from a row, shifts it and inserts the free resource
     *
     * @param row a row of the market selected by the player
     * @return the array list of resources on the selected row
     */

    public ArrayList<Resource> getRow(int row)
    {
        ArrayList<Resource> result = new ArrayList<Resource>();
        Resource tmp;
        for(int c = 0; c< Costants.MARKETCOLS; c++)
        {
            tmp = currentSituation[row][c];
            result.add(tmp);
        }

        //Once the result array is ready move the market resources
        tmp=currentSituation[row][0];
        for(int c = 0; c< Costants.MARKETCOLS-1; c++)
        {
            currentSituation[row][c] = currentSituation[row][c+1];
        }
        currentSituation[row][Costants.MARKETCOLS-1]=freeOne;
        freeOne=tmp;
        return result;
    }

    /**
     * Gets free resource
     *
     * @return the free resource
     */

    public Resource getFreeOne()
    {
        return freeOne;
    }

    /**
     * Gets the Market matrix
     *
     * @return a matrix representing the current market situation
     */

    public Resource[][] getMarket()
    {
        return currentSituation.clone();
    }

}
