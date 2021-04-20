package it.polimi.ingsw.connection;

import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

//Per ogni richiesta crea un elemento decodificando il gson
//Questo elemento avrò un id della carta e le possibili risorse a scelta
public class ProduceRequest {
    String usedCard; //The card used
    ArrayList<Resource> choices; //The resources choosen by the player
    int returnedChoice;

    public String getCard()
    {
        returnedChoice = 0;
        return usedCard;
    }

    public Resource getChoice()
    {
        returnedChoice++;
        return choices.get(returnedChoice - 1 );
    }
}
