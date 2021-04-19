package it.polimi.ingsw.model;

/**
 * The type Faith path.
 */
public class FaithPath
{
    private int advancement;

    private Player player;

    private final int pope1;
    private final int pope2;
    private final int pope3;

    private boolean tile1;
    private boolean tile2;
    private boolean tile3;

    /**
     * Instantiates a new Faith path.
     */
    public FaithPath()
    {
        advancement = 0;
        pope1 = 8;
        pope2 = 16;
        pope3 = 24;
        tile1 = true;
        tile2 = true;
        tile3 = true;
    }

    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer(Player player)
    {
        this.player = player;
    }

    /**
     * Move forward.
     *
     * @param steps the steps
     */
// updates the advancement in the faith path
    public void moveForward(int steps)
    {
        if(steps != 0)
        {
            advancement = advancement + steps;
            checkPopeSpace(advancement);
            checkVictoryPoints(advancement);
        }

    }

    /**
     * Check victory points.
     *
     * @param a the a
     */
//could be improved
    //if advancement % 3 == 0, addVictoryPoints is called. The argument is the amount of VP written on
    //faith path
    public void checkVictoryPoints(int a)
    {
        if(a == 3)
            player.addVictoryPoints(1);
        else if(a == 6)
            player.addVictoryPoints(2);
        else if(a == 9)
            player.addVictoryPoints(4);
        else if(a == 12)
            player.addVictoryPoints(6);
        else if(a == 15)
            player.addVictoryPoints(9);
        else if(a == 18)
            player.addVictoryPoints(12);
        else if(a == 21)
            player.addVictoryPoints(16);
        else if(a == 24)
            player.addVictoryPoints(20);
    }

    /**
     * Check pope space boolean.
     *
     * @param a the a
     * @return the boolean
     */
//A Pope Space is located every 8 steps
    public boolean checkPopeSpace(int a)
    {
        if (a == pope1)
        {
            checkVaticanSection(pope1);
            return true;
        }
        else if (a == pope2)
        {
            checkVaticanSection(pope2);
            return true;
        }
        else if (a == pope3)
        {
            checkVaticanSection(pope3);
            return true;
        }

        return false;

    }

    /**
     * Check vatican section boolean.
     *
     * @param pope the pope
     * @return the boolean
     */
/*
    if the tile is true and the advancement is more than the threshold, it means it's the first time
    a player passes that particular pope space. If another player (that was behind) passes that
    same pope space later in the game, checkVaticanSection is still called but the tile remains false
     */
    public boolean checkVaticanSection(int pope)
    {

        //one of the three pope spaces is given
        if(pope == pope1)
        {
            // checks the advancement first
            if(advancement < 5)
            {
                tile1 = false;
                return false;
            }
            //checks if the special tile is true or false
            if(tile1 == false)
                return false;
            else
                return true;

        }
        else if(pope == pope2)
        {
            if(advancement < 12)
            {
                tile2 = false;
                return false;
            }
            if(tile2 == false)
                return false;
            else
                return true;
        }
        else if(pope == pope3)
        {
            if(advancement < 19)
            {
                tile3 = false;
                return false;
            }
            if(tile3 == false)
                return false;
            else
                return true;
        }
        else
            return false;
    }


}
