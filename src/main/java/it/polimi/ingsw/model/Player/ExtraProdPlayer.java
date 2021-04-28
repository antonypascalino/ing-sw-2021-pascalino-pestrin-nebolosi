package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.Request.ProduceRequest;
import it.polimi.ingsw.model.Cards.DevCard;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

/**
 * The type Extra Production player (it extends {@link Player}).
 * It's the player with an extra production leader card.
 */
public class ExtraProdPlayer extends Player{
    private Resource requires;
    private ArrayList<Resource> produce;

    /**
     * Instantiates a new Extra prod player.
     *
     * @param player       the player
     * @param prodResource a resource needed to start the production
     */
    public ExtraProdPlayer(Player player, Resource prodResource) {
        ArrayList<Resource> tmp= new ArrayList<Resource>();
        tmp.add(Resource.FAITH);
        tmp.add(Resource.CHOICE);
        produce = tmp;
        requires = prodResource;
        original = player;
    }

    /**
     * Produce.
     *
     * @param requests the requests
     */
/*
    @override
     */
    public void produce(ArrayList<ProduceRequest> requests)
    {

        //per ogni richiesta di produzione (dalla connection) attiva la giusta carta e salva la produzione
        //nel forziere del giocatore
        //TIENE CONTO ANCHE DEL PRODUCE
    }

    public void getProduction()
    {
        for (DevCard dev : original.getBoard().getSlot().getFrontCards())
            //Used for giving the power of all cards to the view
            //dev.getPower();
            System.out.println("Debug");
        //E il potere di produzione
    }
}
