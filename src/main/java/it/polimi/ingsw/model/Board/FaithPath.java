package it.polimi.ingsw.model.Board;

import it.polimi.ingsw.model.Player.Player;

/**
 * The Faith Path of one single {@link Player}: every player has his own Faith Path
 */
public class FaithPath
{
    private int advancement;

    private Board board;

    private final int pope1;
    private final int pope2;
    private final int pope3;

    private boolean tile1;
    private boolean tile2;
    private boolean tile3;

    /**
     * Instantiates a new Faith path setting the advancement attribute to 0.
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
     * Receive an <em>int</em> and move the move the player on his {@link FaithPath} of <em>int</em> steps.
     *
     * @param steps the number of steps the {@link Player} have to do on his Faith Path.
     */
    public void moveForward(int steps)
    {
        if(steps != 0)
        {
            advancement = advancement + steps;
            checkVictoryPoints(advancement);
        }
    }

    /**
     * Check if player's advancement reaches some <em>checkpoints</em> and gives him some Victory Points.
     * <p>
     * This method check, after every single move of the player on the {@link FaithPath}, if the player reached any <em>checkpoint</em>.
     * If yes, the method call the {@link it.polimi.ingsw.model.Player.Player#addVictoryPoints(int)} method passing the respective VictoryPoints.
     *
     * @param a the current advancement of the {@link Player} on his {@link FaithPath}.
     */
    //could be improved
    //if advancement % 3 == 0, addVictoryPoints is called. The argument is the amount of VP written on
    //faith path
    public void checkVictoryPoints(int a)
    {
        if(a >= 3 && a < 6)
            board.getPlayer().addVictoryPoints(1);
        else if(a >= 6 && a < 9)
            board.getPlayer().addVictoryPoints(2);
        else if(a >= 9 && a < 12)
            board.getPlayer().addVictoryPoints(4);
        else if(a >= 12 && a < 15)
            board.getPlayer().addVictoryPoints(6);
        else if(a >= 15 && a < 18)
            board.getPlayer().addVictoryPoints(9);
        else if(a >= 18 && a < 21)
            board.getPlayer().addVictoryPoints(12);
        else if(a >= 21 && a < 24)
            board.getPlayer().addVictoryPoints(16);
        else if(a == 24)
            board.getPlayer().addVictoryPoints(20);
    }

    /**
     * Check if the current {@link Player}'s advancement has reached or passed a Pope Space
     *
     * @param toCheck the current advancement of the {@link Player}
     * @return true if the player has reached or passed a Pope Space, false otherwise
     */
    //A Pope Space is located every 8 steps
    public boolean checkPopeSpace(int toCheck) {
        if (toCheck == 1) {
            return advancement >= pope1;
        } else if (toCheck == 2) {
            return advancement >= pope2;
        } else if (toCheck == 3) {
            return advancement >= pope3;
        }
        return false;
    }


    /**
     * When a {@link Player} has reached or passed a Pope Space, this method is called by {@link #checkPopeSpace(int)}.
     * Check if the current player's advancements is in the corresponding Vatican Section.
     * <p>
     * If the tile is true and the advancement is more than the threshold, it means it's the first time
     * a player passes that particular pope space. If another player (that was behind) passes that
     * same pope space later in the game, checkVaticanSection is still called but the tile remains false
     *
     * @param popeCalled the Pope Space just reached;
     * @return true if the player advancement is in the Vatican Section of the corresponding Pope Space just reached, false otherwise
     */
    public boolean checkVaticanSection(int popeCalled)
    {
        if (popeCalled == 1) {
           if(advancement >= 5) {
               board.getPlayer().addVictoryPoints(2);
               return true;
           }
        }
        else if (popeCalled == 2) {
            if(advancement >= 12) {
                board.getPlayer().addVictoryPoints(3);
                return true;
            }
        }
        else if (popeCalled == 3) {
            if(advancement >= 19) {
                board.getPlayer().addVictoryPoints(4);
                return true;
            }
        }
        return false;
    }

    public int getAdvancement() {
        return advancement;
    }
}
