package model;

public class FaithPath

{
    private int advancement;
    private int newPoints;

    //a few booleans needed to check pope tiles

    public FaithPath()
    {
        advancement = 0;
        newPoints = 0;
    }

    // updates the advancement in the faith path
    public void moveForward(int steps)
    {
           advancement = advancement + steps;
           checkPopeSpace();

    }

    public int checkVictoryPoints(int oldPosition)
    {
        newPoints = newPoints + oldPosition;
        return newPoints;
    }

    //A Pope Space is located every 8 steps
    public boolean checkPopeSpace()
    {
        //we need to mark each pope space differently in order to call correctly check vatican section
        if (advancement % 8 == 0)
            return true;
        else
            return false;
    }

    //receives one of the three pope spaces etc
    public boolean checkVaticanSection(int pope)
    {

        //vatican section indicators needed
            if(advancement > 4 && advancement < 9)
                return true;
            else if (advancement > 11 && advancement < 17)
                return true;
            else if (advancement > 18)
                return true;
            else
                return false;
        }


    }



