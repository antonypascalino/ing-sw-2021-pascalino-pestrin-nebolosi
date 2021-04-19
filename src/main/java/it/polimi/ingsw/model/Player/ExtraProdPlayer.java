package it.polimi.ingsw.model.Player;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import it.polimi.ingsw.model.Cards.DevCard;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

public class ExtraProdPlayer extends Player{
    private Resource requires;
    private ArrayList<Resource> produce;

    public ExtraProdPlayer(Player player, Resource prodResource) {
        ArrayList<Resource> tmp= new ArrayList<Resource>();
        tmp.add(Resource.FAITH);
        tmp.add(Resource.CHOICE);
        produce = tmp;
        requires = prodResource;
        original = player;
    }

    /*
    @override
     */
    public void produce()
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
