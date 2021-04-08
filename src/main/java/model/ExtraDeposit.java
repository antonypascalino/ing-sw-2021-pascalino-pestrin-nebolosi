package model;

import java.util.ArrayList;
import java.util.Collections;

public class ExtraDeposit implements LeaderCard {

    private int victoryPoints;
    private boolean isEnable;
    private Player player;
    private Resource requires;
    private Resource depositableRes;


    public ExtraDeposit() {

        //legge da file Json quale risorsa ha come richiesta
        //legge da file Json quale risorsa si può depositare nel deposito aggiuntivo

        //aggiunge a requires 5 Resource di tipo required, in modo da poter verificare dopo il loro possesso da parte del Player
        //con una semplice containsAll()
    }

    public void assignTo (Player player)
    {
        this.player = player;
    }

    public boolean isEnable()
    {
        return isEnable;
    }

    public void playCard()
    {
        if(canBePlayed())
        {
            isEnable = true;
            player.addVictoryPoints(victoryPoints);
            player.getBoard().getWareHouse().levels.add(new Resource[2]);
        }
    }

    public boolean canBePlayed()
    {
        return (Collections.frequency(player.getBoard().getWarehouse().getResources(), requires) +
                (Collections.frequency(player.getBoard().getWarehouse().getResources(), requires)) >= 5;
    }


}
