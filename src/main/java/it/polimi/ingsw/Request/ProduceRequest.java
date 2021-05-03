package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

/**
 * The type Produce request.
 */
//Per ogni richiesta crea un elemento decodificando il gson
//Questo elemento avrò un id della carta e le possibili risorse a scelta
public class ProduceRequest implements Request {

    private final String className;
    /**
     * The Used card for producing
     */
    String usedCard; //The card used
    /**
     * The Choices in order, whenever there's a Resource.choice get it from here
     */
    ArrayList<Resource> choices; //The resources choosen by the player
    /**
     * The Returned choice index to keep track of where the caller is
     */
    int returnedChoice;

    public ProduceRequest()
    {
        className = this.getClass().getName();
    }
    /**
     * Gets card and inizializes returned choice so it starts with the first element
     *
     * @return the card
     */
    public String getCard()
    {
        returnedChoice = 0;
        return usedCard;
    }


    public void handle()
    {
        //Chiama il game dicendo chi fa cosa
    }

    @Override
    public void handle(Player player) {

    }

    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return !(turnStates.contains(TurnState.BUY_DEV_CARD) || turnStates.contains(TurnState.PRODUCE) || turnStates.contains(TurnState.GET_FROM_MARKET));
    }

    @Override
    public boolean canBePlayed(Player player) {
        return false;
    }

    /**
     * Gets choice and increment the counter so next time it gives back the following choice     *
     * @return the choice
     */
    public Resource getChoice()
    {
        returnedChoice++;
        return choices.get(returnedChoice - 1 );
    }

    @Override
    public String getClassName()
    {
        return className;
    }




    //DA IMPLEMENTARE
    @Override
    public TurnState nextTurnState() {
        return null;
    }

    @Override
    public int getMyFPSteps() {
        return 0;
    }

    @Override
    public int getDiscardedSteps() {
        return 0;
    }
}
