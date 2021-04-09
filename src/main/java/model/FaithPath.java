package model;

public class FaithPath

{
    private int advancement;
    private int newPoints;

    //crea tessere favore papale boolean

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

    //receives one of the three pope spaces etc
    public boolean checkVaticanSection(int pope)
    {
        if (checkPopeSpace() == true)
        {
            if(advancement > 4 && advancement < 9)
                return true;
            else if (advancement > 11 && advancement < 17)
                return true;
            else if (advancement > 18)
                return true;
            else
                return false;
        }
        return false;

    }


}
