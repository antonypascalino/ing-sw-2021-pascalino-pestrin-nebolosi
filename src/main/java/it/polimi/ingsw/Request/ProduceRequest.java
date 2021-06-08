package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Updates.PlayerVP;
import it.polimi.ingsw.model.Updates.ProduceUpdate;
import it.polimi.ingsw.model.Updates.Update;
import it.polimi.ingsw.model.card.ExtraProd;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The type Produce request.
 */
//Per ogni richiesta crea un elemento decodificando il gson
//Questo elemento avrò un id della carta e le possibili risorse a scelta
public class ProduceRequest implements Request {

    private String playerID;
    private int gameID;
    private ArrayList<Production> productions;
    private int playerSteps;
    private int playerChoices;
    private final String className;

    public ProduceRequest(int gameID, String playerID, ArrayList<Production> prod)
    {
        className = this.getClass().getName();
        this.gameID = gameID;
        this.playerID = playerID;
        this.productions = prod;
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

        //controlla che il giocatore abbia le risorse aggiungendole in un array temporaneo per controllare che le abbia tutte
        ArrayList<Resource> resTemp = new ArrayList<Resource>();
        for(Production prod : productions) {
            for (MappedResource map : prod.getMappedResources()) {
                if (!map.getPlace().equals("choice")) {
                    resTemp.add(map.getResource());
                }
            }
        }
        for (Resource res : resTemp) {
            if (Collections.frequency(resTemp, res) > Collections.frequency(player.getAllResources(), res)) {
                return false;
            }
        }

        //controlla che le risorse e le requires della carta siano giuste
        for(Production pr : productions){
            for(MappedResource mp : pr.getMappedResources()) {
                if (pr.getCardID().contains("dev")) {
                    if (player.getBoard().getDevFromID(pr.getCardID()).getRequirements().size() != pr.getMappedResources().size() || !player.getBoard().getDevFromID(pr.getCardID()).getRequirements().contains(mp.getResource())) {
                        //lancia eccezione "resources selected do not match card requirements"
                        return false;
                    }
                }
                if (pr.getCardID().contains("PROD")) {
                    //If the card
                    if (((ExtraProd)(player.getLeaderFromID(pr.getCardID()))).getProducedRes().contains(mp.getResource())) {
                        //lancia eccezione
                        return false;
                    }
                }
            }
        }

        //Controlla che non ci siano due carte uguali con cui il giocatore vuole produrre
        if( productions.stream().map(Production::getCardID).distinct().count() != productions.size() )
            return false;
        return true;
    }

    @Override
    public TurnState handle(Player player, Game game) {
        for (Production prodReq : productions) {
            for (MappedResource mapRes : prodReq.getMappedResources()) {
                player.removeResource(mapRes.getResource(), mapRes.getPlace());
            }
        }
        for (Production prod : productions) {
            if (prod.getCardID().equals("BASIC") || prod.getCardID().contains("PROD")) {
                //Add to the temp box all the resource that
                //Are received as choices
                ArrayList<Resource> list = new ArrayList<>();
                for (MappedResource x : prod.getMappedResources()) {
                    if (x.getPlace().equals("choice")) {
                        list.add(x.getResource());
                    }
                }
                player.getBoard().getTempBox().addResource(list);
            } else
                player.produce(prod.getCardID());
        }

        playerSteps = player.getBoard().getTempBox().filterFaithPoints();
        game.fpAdvancement(0, playerSteps);
        player.getBoard().getTempBox().moveToStrongBox();
        return TurnState.PRODUCE;
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return !(turnStates.contains(TurnState.BUY_DEV_CARD) || turnStates.contains(TurnState.PRODUCE) || turnStates.contains(TurnState.GET_FROM_MARKET));
    }



    @Override
    public String getClassName()
    {
        return className;
    }

    @Override
    public Update createUpdate(Player player, Game game) {
        ArrayList<PlayerVP> playersVP = new ArrayList<PlayerVP>();
        for (Player p : game.getPlayers()) {
            playersVP.add(new PlayerVP(p.getNickName(), p.getVictoryPoints()));
        }

        return new ProduceUpdate(player.getNickName(), game.getTurnStates(), player.getDeposits(), player.getBoard().getStrongBox().getResources(), player.getBoard().getFaithPath().getAdvancement(), playersVP);
    }

    @Override
    public String getPlayerID() {
        return playerID;
    }

    @Override
    public int getGameID() {
        return gameID;
    }
}
