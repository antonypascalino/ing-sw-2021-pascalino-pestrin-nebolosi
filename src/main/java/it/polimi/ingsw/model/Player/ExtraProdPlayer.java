package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.card.ExtraProd;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.card.LeaderCard;

import java.util.ArrayList;

/**
 * The type Extra Production player (it extends {@link Player}).
 * It's the player with an {@link ExtraProd} {@link LeaderCard}.
 */
public class ExtraProdPlayer extends Player {
    private ArrayList<Resource> required;
    private ArrayList<String> extraProdIDs; //The IDs of all LeaderCard which built these ExtraProdPlayers


    /**
     * Instantiates a new Extra prod player.
     *
     * @param original    the original
     * @param required    the required
     * @param extraProdID the extra prod id
     */
    public ExtraProdPlayer(Player original, Resource required, String extraProdID) {
        this.extraProdIDs = new ArrayList<>();
        if (original instanceof ExtraProdPlayer) {
            this.required = new ArrayList<>();
            this.required.addAll(((ExtraProdPlayer) original).getRequired());
            this.extraProdIDs.addAll(((ExtraProdPlayer) original).getExtraProdIDs());
        }
        this.required = new ArrayList<>();
        this.required.add(required);
        this.original = original;
        this.extraProdIDs.add(extraProdID);
    }

    public void produce(String cardID) {

        if (cardID.contains("dev")) {
            getBoard().getTempBox().addResource(getBoard().getDevFromID(cardID).producedResources());

        }
        if (cardID.contains("PROD")) {
            ArrayList<Resource> extraProd = new ArrayList<>();
            //choices are handled in the Requests
            extraProd.add(Resource.FAITH);
            getBoard().getTempBox().addResource(extraProd);
        }
    }

    private ArrayList<String> getExtraProdIDs() {
        return extraProdIDs;
    }

    @Override
    public ArrayList<String> getProductionID() {
        ArrayList<String> productions = new ArrayList<>();
        productions.addAll(original.getBoard().getProdID());
        productions.addAll(extraProdIDs);
        return productions;
    }

    /**
     * Gets required.
     *
     * @return the required
     */
    public ArrayList<Resource> getRequired() {
        return required;
    }
}
