package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.card.ExtraProd;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

/**
 * The type Extra Production player (it extends {@link Player}).
 * It's the player with an extra production leader card.
 */
public class ExtraProdPlayer extends Player {
    private Resource requires;
    private ArrayList<Resource> produce;
    private ArrayList<ExtraProd> extraProds;

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
    public void produce(String cardID) {

        if (cardID.contains("dev")) {
            getBoard().getTempBox().addResource(getBoard().getDevFromID(cardID).producedResources());

        }
        if (cardID.contains("PROD")) {
            ArrayList<Resource> extraProd = new ArrayList<Resource>();
            extraProd.add(Resource.CHOICE);
            extraProd.add(Resource.FAITH);
            getBoard().getTempBox().addResource(extraProd);
        }

        if (cardID.contains("basic")) {
            ArrayList<Resource> basicProd = new ArrayList<Resource>();
            basicProd.add(Resource.CHOICE);
            getBoard().getTempBox().addResource(basicProd);
        }
    }

    public void getProduction()
    {
        for (DevCard dev : original.getBoard().getSlot().getFrontCards())
            //Used for giving the power of all cards to the view
            //dev.getPower();
            System.out.println("Debug");
        //E il potere di produzione
    }

    private ArrayList<String> extraProdID() {
        ArrayList<String> extraProdID = new ArrayList<String>();
        for (ExtraProd extra : extraProds) {
            extraProdID.add(extra.getID());
        }
        return extraProdID;
    }

    @Override
    public ArrayList<String> getProductionID() {
        ArrayList<String> productions = new ArrayList<String>();
        productions.addAll(original.getBoard().getProdID());
        productions.addAll(extraProdID());
        return productions;
    }

}
