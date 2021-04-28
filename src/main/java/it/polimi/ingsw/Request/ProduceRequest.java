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


    public TurnState handle()
    {
        //Chiama il game dicendo chi fa cosa
        return TurnState.Initial;
    }

    @Override
    public boolean validRequest() {
        return false;
    }

    @Override
    public int canBePlayed(Player player) {
        return 0;
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
}
