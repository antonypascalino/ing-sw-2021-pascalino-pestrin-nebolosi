package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.Production;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Cards.Producer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

/**
 * The type Produce request.
 */
//Per ogni richiesta crea un elemento decodificando il gson
//Questo elemento avrò un id della carta e le possibili risorse a scelta
public class ProduceRequest implements Request {

    private ArrayList<Production> productions;
    private int playerSteps;


    @Override
    public void handle(Player player) {
        for(Production prodReq : productions){
            for(MappedResource mapRes : prodReq.getMappedResources()){
                player.removeResource(mapRes.getResource(), mapRes.getPlace());
            }

        }
        for(Production prod : productions){
            player.produce(prod.getCardID());
        }

        //non so se si può fare, nel caso si sposta nel player
        playerSteps = player.getBoard().getTempBox().filterFaithPoints().size();
        //poi sarebbe da svuotare l'array;
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

        //controlla che il giocatore abbia le risorse
        ArrayList<Resource> resTemp = new ArrayList<Resource>();
        for(Production prod : productions){
            for(MappedResource map : prod.getMappedResources()){
                resTemp.add(map.getResource());
            }
        }
        if(!player.getAllResources().containsAll(resTemp)){
            //lancia eccezione "you don't have those resources!"
            return false;
        }

        //controlla che le risorse e le requires siano giuste
        for(Production pr : productions){
            for(MappedResource mp : pr.getMappedResources()) {
                if (pr.getCardID().contains("dev")) {
                    if (player.getBoard().getDevFromID(pr.getCardID()).getRequirements().size() != pr.getMappedResources().size() && !player.getBoard().getDevFromID(pr.getCardID()).getRequirements().contains(mp.getResource())) {
                        //lancia eccezione "resources selected do not match card requirements"
                        return false;
                    }
                }
                if (pr.getCardID().contains("PROD")) {
                    if (!player.getLeaderFromID(pr.getCardID()).canBePlayed()) {
                        //lancia eccezione
                        return false;
                    }
                }
            }
        }

        //Controlla che non ci siano due carte uguali con cui il giocatore vuole produrre
        ArrayList<Production> tmpProd = (ArrayList<Production>) productions.clone();
        for (Production p : tmpProd){
            String tmpID = p.getCardID();
            tmpProd.remove(p);
            if(tmpProd.contains(tmpID)){
                //lancia eccezione "there's a duplicate of a leader card"
                return false;
            }
        }
        return true;
    }

    @Override
    public String getClassName()
    {
        return "ProduceRequest";
    }

    //DA IMPLEMENTARE
    @Override
    public TurnState nextTurnState() {
        return TurnState.PRODUCE;
    }

    @Override
    public int getMyFPSteps() {
        return playerSteps;
    }

    @Override
    public int getDiscardedSteps() {
        return 0;
    }

}
