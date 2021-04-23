package it.polimi.ingsw.connection;

import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

/**
 * The type Produce request.
 */
//Per ogni richiesta crea un elemento decodificando il gson
//Questo elemento avrò un id della carta e le possibili risorse a scelta
public class ProduceRequest implements Request {
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


    public void activate()
    {
        //Chiama il game dicendo chi fa cosa
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
}
