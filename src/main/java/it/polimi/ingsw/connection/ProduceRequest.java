package it.polimi.ingsw.connection;

import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

/**
 * The type Produce request.
 */
//Per ogni richiesta crea un elemento decodificando il gson
//Questo elemento avrò un id della carta e le possibili risorse a scelta
public class ProduceRequest {
    /**
     * The Used card.
     */
    String usedCard; //The card used
    /**
     * The Choices.
     */
    ArrayList<Resource> choices; //The resources choosen by the player
    /**
     * The Returned choice.
     */
    int returnedChoice;

    /**
     * Gets card.
     *
     * @return the card
     */
    public String getCard()
    {
        returnedChoice = 0;
        return usedCard;
    }

    /**
     * Gets choice.
     *
     * @return the choice
     */
    public Resource getChoice()
    {
        returnedChoice++;
        return choices.get(returnedChoice - 1 );
    }
}
