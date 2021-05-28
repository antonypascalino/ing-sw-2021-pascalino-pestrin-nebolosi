import it.polimi.ingsw.Request.MarketDimension;
import it.polimi.ingsw.Request.MarketRequest;
import it.polimi.ingsw.Request.NewGameRequest;
import it.polimi.ingsw.Request.Request;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.MarketResource;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

/*
Tries to create a game and send different requests to it
 */
public class RequestTest {
    @Test
    //Request to get a column first and then a row from the market
    public void GetFromMarketRequest()
    {
        //First create a new game and add a player
        ArrayList<Game> games = new ArrayList<>();
        Request request = new NewGameRequest("SickNebo", 3);
        //This code has been copied from the client handler class
        if(request instanceof NewGameRequest)
        {
            //If there's no game on the server create the first one
            Game lastGame = null;
            if (games.size() != 0)
                lastGame = games.get(games.size()-1);
            //If there's no game or the last one has reached the maximum player, it doesn't check it if games.size==0
            //Create a new game
            if (games.size() == 0 || !(lastGame.getPlayers().size() < lastGame.getMax()))
            {
                int gameId;
                if(games.size() != 0)
                    gameId = games.get(games.size()-1).getGameId() +1;
                else
                    gameId=0;
                ArrayList<Player> tmp = new ArrayList<Player>();
                tmp.add(new BasicPlayer(((NewGameRequest) request).getNickname()));
                Game newGame = new Game(tmp, DefaultCreator.produceDevCard(),gameId,((NewGameRequest) request).getPlayers());
                games.add(newGame);
                System.out.println("Player "+((NewGameRequest) request).getNickname()+ " added to the new game "+newGame.getGameId());
            }
            //If it hasn't alrady reached the maximum numner of players
            //add the new player
            else
            {
                Player newPlayer = new BasicPlayer(((NewGameRequest) request).getNickname());
                lastGame.addPlayer(newPlayer);
                System.out.println("Player "+((NewGameRequest) request).getNickname()+ " added to game "+lastGame.getGameId());

            }

        }

        //Now send a new request for buying
        Player tmp = games.get(0).getPlayers().get(0);
        //Get the second row of the market
        ArrayList<Resource> resources = games.get(0).getTable().market.seeRow(2);
        ArrayList<MarketResource> mappedRes = new ArrayList<MarketResource>();
        for (Resource res : resources)
        {
            System.out.println(res);
            int lev = 2;
            MarketResource resource = new MarketResource(res, lev);
            mappedRes.add(resource);
        }



        Request test = new MarketRequest(MarketDimension.ROW, 2, games.get(0).getGameId(), tmp.getNickName(), mappedRes);
        games.get(0).notify(test);
        System.out.println(tmp.getAllResources());
    }

}
