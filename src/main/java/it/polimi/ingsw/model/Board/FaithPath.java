package it.polimi.ingsw.model.Board;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.controller.SinglePlayer.SinglePlayerGame;
import it.polimi.ingsw.controller.Game;

/**
 * The Faith Path of one single {@link Player}: every player has his own Faith Path.
 */
public class FaithPath {
    private int advancement;
    private Board board;
    private final int pope1;
    private final int pope2;
    private final int pope3;

    /**
     * Instantiates a new {@link FaithPath} setting the advancement attribute to 0,
     * the positions of the pope spaces and setting the reference to the  {@link Player}'s {@link Board}
     *
     * @param board the board
     */
    public FaithPath(Board board) {
        this.board = board;
        advancement = 0;
        pope1 = 8;
        pope2 = 16;
        pope3 = 24;
    }

    /**
     * Instantiates a new {@link FaithPath} setting the advancement attribute to 0,
     * the positions of the pope spaces.
     * <p>
     * This constructor is used during a {@link SinglePlayerGame} to instantiate Lorenzo's {@link FaithPath} who doesn't
     * own anu board.
     */
    public FaithPath() {
        advancement = 0;
        pope1 = 8;
        pope2 = 16;
        pope3 = 24;
    }

    /**
     * Receive an <em>int</em> and move the {@link Player} on his {@link FaithPath} of <em>int</em> steps.
     *
     * @param steps the number of steps the {@link Player} have to do on his Faith Path.
     */
    public void moveForward(int steps) {
        if (steps != 0) {
            advancement = advancement + steps;
        }
    }

    /**
     * Check if player's advancement reaches some <em>checkpoints</em> and gives him or not some Victory Points.
     * <p>
     * This method is called by the {@link Game}, after every single move of the player on his {@link FaithPath}:
     */
    public void checkVictoryPoints()
    {
        if(advancement >= 3 && advancement < 6)
            board.getPlayer().addVictoryPoints(1);
        else if(advancement >= 6 && advancement < 9)
            board.getPlayer().addVictoryPoints(1);
        else if(advancement >= 9 && advancement < 12)
            board.getPlayer().addVictoryPoints(2);
        else if(advancement >= 12 && advancement < 15)
            board.getPlayer().addVictoryPoints(2);
        else if(advancement >= 15 && advancement < 18)
            board.getPlayer().addVictoryPoints(3);
        else if(advancement >= 18 && advancement < 21)
            board.getPlayer().addVictoryPoints(3);
        else if(advancement >= 21 && advancement < 24)
            board.getPlayer().addVictoryPoints(4);
        else if(advancement == 24)
            board.getPlayer().addVictoryPoints(4);
    }

    /**
     * Check if the current {@link Player}'s advancement has reached or passed a Pope Space
     *
     * @param toCheck the Pope Space to control if {@link Player} has reached.
     * @return true if the player has reached or passed the Pope Space, false otherwise.
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
     * When a {@link Player} has reached or passed a Pope Space, this method is called by the {@link Game} on each player in it.
     * Check if the relative player's advancements is in the corresponding Vatican Section.
     *
     * @param popeCalled the Pope Space just reached by someone;
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
