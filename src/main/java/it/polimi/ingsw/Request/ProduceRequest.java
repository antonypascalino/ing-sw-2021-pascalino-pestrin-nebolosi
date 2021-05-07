package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Production;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Resource;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The type Produce request.
 */
//Per ogni richiesta crea un elemento decodificando il gson
//Questo elemento avrò un id della carta e le possibili risorse a scelta
public class ProduceRequest implements Request {

    private ArrayList<Production> productions;

    @Override
    public void handle(Player player) {

    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return !(turnStates.contains(TurnState.BUY_DEV_CARD) || turnStates.contains(TurnState.PRODUCE) || turnStates.contains(TurnState.GET_FROM_MARKET));
    }

    @Override
    public boolean canBePlayed(Player player) {

        //Controlla che il giocatore abbia le carte con cui vuole produrre
        for (Production prod : productions) {
            if(!player.getProductionID().contains(prod.getCardID())) {
                // lancia eccezione : non hai questa carta per produrre
                return false;
            }
        }

        //Controlla che non ci siano due carte uguali con cui il giocatore vuole produrre

        //Controlla che il giocatore abbia tutte le risorse per produrre


    }


    @Override
    public String getClassName()
    {
        return "ProduceRequest";
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
