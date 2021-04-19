package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Random;


/**
 * The type Market.
 */
public class Market {

    private Resource[][] actualSituation; //Market disposition
    private Resource freeOne; //Free resource

    /**
     * Instantiates a new Market.
     *
     * @param gameRes the game res
     */
/*
    Recives the 13 resources that are gonna be in the market for this game
    @result Puts 12 of them in a matrix in a Random order and one of them in the freeSpot
     */
    public Market(ArrayList<Resource> gameRes)
    {
        actualSituation = new Resource[Costants.MARKETROWS][Costants.MARKETCOLS];
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
                actualSituation[r][c]=gameRes.get(i);
                gameRes.remove(i);
            }

        //Solo per il debug, aggiungere ai test
        if(gameRes.size()==1)
            System.out.println("Everything worked fine");

        freeOne = gameRes.get(0);
    }

    /**
     * Gets columns.
     *
     * @param col the col
     * @return the columns
     */
/*
    Select all the resources from a col and shift the col and insert the free resource
    @result an arrayList with the resources of the required column
     */
    public ArrayList<Resource> getColumns(int col)
    {
        ArrayList<Resource> result = new ArrayList<Resource>();
        Resource tmp;
        for(int r = 0; r< Costants.MARKETROWS; r++)
        {
            tmp=new Resource(actualSituation[r][col]); //I want to return a new resource that looks like the one i have but it's not the same
            result.add(tmp);
        }

        //Once the result array is ready move the market resources
        tmp=actualSituation[0][col];
        for(int r = 0; r< Costants.MARKETROWS-1; r++)
        {
           actualSituation[r][col] = actualSituation[r+1][col];
        }
        actualSituation[Costants.MARKETROWS-1][col]=freeOne;
        freeOne=tmp;
        return result;
    }

    /**
     * Gets row.
     *
     * @param row the row
     * @return the row
     */
/*
    Select all the resources from a row and shift the col and insert the free resource
    @result an arrayList with the resources of the required row
     */
    public ArrayList<Resource> getRow(int row)
    {
        ArrayList<Resource> result = new ArrayList<Resource>();
        Resource tmp;
        for(int c = 0; c< Costants.MARKETCOLS; c++)
        {
            tmp=new Resource(actualSituation[row][c]);
            result.add(tmp);
        }

        //Once the result array is ready move the market resources
        tmp=actualSituation[row][0];
        for(int c = 0; c< Costants.MARKETCOLS-1; c++)
        {
            actualSituation[row][c] = actualSituation[row][c+1];
        }
        actualSituation[row][Costants.MARKETCOLS-1]=freeOne;
        freeOne=tmp;
        return result;
    }

    /**
     * Gets free one.
     *
     * @return the free one
     */
/*
    Returns the free resource
     */
    public Resource getFreeOne()
    {
        return freeOne;
    }

    /**
     * Get market resource [ ] [ ].
     *
     * @return the resource [ ] [ ]
     */
//Return a matrix that rappresent the actual market situation
    public Resource[][] getMarket()
    {
        return actualSituation.clone();
    }

}
