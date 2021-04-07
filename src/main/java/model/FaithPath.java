package model;

public class FaithPath {
    private int advancement;
    private int newPoints;

    public FaithPath()
    {
        advancement = 0;
        newPoints = 0;
    }

    // updates the advancement in the faith path
    public void moveForward(int steps)
    {
           advancement = advancement + steps;

    }


    public int checkVictoryPoints(int oldPosition)
    {
        newPoints = newPoints + oldPosition;
        return newPoints;
    }

    //A Pope Space is located every 8 steps
    public boolean checkPopeSpace()
    {
        if (advancement % 8 == 0)
            return true;
        else
            return false;
    }

    
    public boolean checkVaticanSection()
    {
        if (checkPopeSpace() == true)
        {
            if
        }




    }


}
