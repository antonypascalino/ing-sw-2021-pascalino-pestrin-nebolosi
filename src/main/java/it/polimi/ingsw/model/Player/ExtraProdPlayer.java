package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.card.ExtraProd;
import it.polimi.ingsw.model.Table.Resource;

import java.util.ArrayList;

/**
 * The type Extra Production player (it extends {@link Player}).
 * It's the player with an extra production leader card.
 */
public class ExtraProdPlayer extends Player {
    private ArrayList<Resource> required;
    private ArrayList<String> extraProdIDs; //The IDs of all LeaderCard which built these ExtraProdPlayers


    public ExtraProdPlayer(Player original, Resource required, String extraProdID) {
        if (original instanceof ExtraProdPlayer) {
            this.required = new ArrayList<Resource>();
            this.required.addAll(((ExtraProdPlayer) original).getRequired());
            this.extraProdIDs = new ArrayList<>();
            this.extraProdIDs.addAll(((ExtraProdPlayer) original).getExtraProdIDs());
        }
        this.required = new ArrayList<Resource>();
        this.required.add(required);
        this.original = original;
        this.extraProdIDs = new ArrayList<>();
        this.extraProdIDs.add(extraProdID);
    }

    public void produce(String cardID) {

        if (cardID.contains("dev")) {
            getBoard().getTempBox().addResource(getBoard().getDevFromID(cardID).producedResources());

        }
        if (cardID.contains("PROD")) {
            ArrayList<Resource> extraProd = new ArrayList<Resource>();
            //LA choice viene gestita nella handleRequest
            extraProd.add(Resource.FAITH);
            getBoard().getTempBox().addResource(extraProd);
        }
    }

    private ArrayList<String> getExtraProdIDs() {
        return extraProdIDs;
    }

    @Override
    public ArrayList<String> getProductionID() {
        ArrayList<String> productions = new ArrayList<String>();
        productions.addAll(original.getBoard().getProdID());
        productions.addAll(extraProdIDs);
        return productions;
    }

    public ArrayList<Resource> getRequired() {
        return required;
    }
}
