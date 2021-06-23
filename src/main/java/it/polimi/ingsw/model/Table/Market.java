package it.polimi.ingsw.model.Table;

import it.polimi.ingsw.model.Constants;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.card.DevCard;

import java.util.ArrayList;
import java.util.Random;


/**
 * The market of the {@link Game} containing all the {@link Resource} {@link Player}s can obtain without using {@link DevCard}
 */
public class Market {

    private Resource[][] currentSituation; //Market disposition
    private Resource freeOne; //Free resource

    /**
     * Instantiates a new Market.
     * This method receives 13 {@link Resource}s and then puts 12 of them in a matrix
     * in random order whilst keeping one outside as a "free one".
     * Those resources are going to be in the market matrix for this game.
     * @param gameRes 13 resources as an array list.
     */
    public Market(ArrayList<Resource> gameRes)
    {
        currentSituation = new Resource[Constants.MARKETROWS][Constants.MARKETCOLS];
        Random rnd = new Random();
        int i;

        //Solo per il debug, aggiungere ai test
        if(gameRes.size()!=13)
        {
           System.out.println("Not enough resources");
        }

        for (int r = 0; r< Constants.MARKETROWS; r++)
            for(int c = 0; c< Constants.MARKETCOLS; c++)
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
     * Selects all the {@link Resource}s from a column, shifts it and inserts the free one.
     *
     * @param col the column of the market selected by the {@link Player}.
     * @return the array list of {@link Resource}s on the selected column.
     */
    public ArrayList<Resource> getColumn(int col)
    {
        ArrayList<Resource> result = new ArrayList<Resource>();
        Resource tmp;
        for(int r = 0; r< Constants.MARKETROWS; r++)
        {
            tmp=currentSituation[r][col];
            result.add(tmp);
        }
        //Once the result array is ready move the market resources
        tmp=currentSituation[0][col];
        for(int r = 0; r< Constants.MARKETROWS-1; r++)
        {
           currentSituation[r][col] = currentSituation[r+1][col];
        }
        currentSituation[Constants.MARKETROWS-1][col]=freeOne;
        freeOne=tmp;
        return result;
    }

    /**
     * Only gets the {@link Resource}s from a column without modifying the {@link Market}.
     *
     * @param col the column of the {@link Market} it wants to see.
     * @return the array list of {@link Resource}s on the selected column.
     */
    public ArrayList<Resource> seeColumn(int col) {
        ArrayList<Resource> result = new ArrayList<Resource>();
        Resource tmp;
        for(int r = 0; r< Constants.MARKETROWS; r++)
        {
            tmp=currentSituation[r][col];
            result.add(tmp);
        }
        return result;
    }

    /**
     * Selects all the {@link Resource}s from a row, shifts it and inserts the free one.
     *
     * @param row the row of the market selected by the {@link Player}.
     * @return the array list of {@link Resource}s on the selected column.
     */
    public ArrayList<Resource> getRow(int row)
    {
        ArrayList<Resource> result = new ArrayList<Resource>();
        Resource tmp;
        for(int c = 0; c< Constants.MARKETCOLS; c++)
        {
            tmp = currentSituation[row][c];
            result.add(tmp);
        }

        //Once the result array is ready move the market resources
        tmp=currentSituation[row][0];
        for(int c = 0; c< Constants.MARKETCOLS-1; c++)
        {
            currentSituation[row][c] = currentSituation[row][c+1];
        }
        currentSituation[row][Constants.MARKETCOLS-1]=freeOne;
        freeOne=tmp;
        return result;
    }

    /**
     * Only shows the {@link Resource}s from a row without modifying the {@link Market}.
     *
     * @param row a row of the {@link Market} it wants to see.
     * @return the array list of {@link Resource}s on the selected row.
     */
    public ArrayList<Resource> seeRow(int row) {
        ArrayList<Resource> result = new ArrayList<Resource>();
        Resource tmp;
        for(int c = 0; c< Constants.MARKETCOLS; c++)
        {
            tmp = currentSituation[row][c];
            result.add(tmp);
        }
        return result;
    }

    /**
     * Gets free one {@link Resource} in the {@link Market}.
     *
     * @return the free one {@link Resource}.
     */
    public Resource getFreeOne()
    {
        return freeOne;
    }

    /**
     * Gets the {@link Resource} matrix of the {@link Resource}s.
     *
     * @return a matrix representing the current market situation.
     */
    public Resource[][] getMarket()
    {
        return currentSituation.clone();
    }

    @Override
    public String toString() {
        return ""+this.seeRow(0)+"\n\r"+this.seeRow(1)+"\n\r"+this.seeRow(2)+"\n\r"+this.seeRow(3)+"\n\r"+"THe free resource is: "+freeOne;
    }
}
