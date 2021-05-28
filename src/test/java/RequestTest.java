import it.polimi.ingsw.Request.*;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.MappedResource;
import it.polimi.ingsw.controller.MarketResource;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Market;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.card.DevCard;
import org.junit.Test;

import javax.annotation.processing.SupportedSourceVersion;
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
        System.out.println(games.get(0).getTable().market.toString());
        ArrayList<MarketResource> mappedRes = new ArrayList<MarketResource>();
        for (int i=0; i<3;i++)
        {
            Resource res= resources.get(i);
            System.out.println(res);
            MarketResource resource = new MarketResource(res, i);
            mappedRes.add(resource);
        }



        Request test = new MarketRequest(MarketDimension.ROW, 2, games.get(0).getGameId(), tmp.getNickName(), mappedRes);
        games.get(0).notify(test);
        System.out.println(tmp.getAllResources());
        System.out.println(games.get(0).getTable().market.toString());

    }

    @Test
    public void DoubleMarket()
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
        System.out.println(games.get(0).getTable().market.toString());
        ArrayList<MarketResource> mappedRes = new ArrayList<MarketResource>();
        for (int i=0; i<3;i++)
        {
            Resource res= resources.get(i);
            System.out.println(res);
            MarketResource resource = new MarketResource(res, i);
            mappedRes.add(resource);
        }



        Request test = new MarketRequest(MarketDimension.ROW, 2, games.get(0).getGameId(), tmp.getNickName(), mappedRes);
        games.get(0).notify(test);
        System.out.println(tmp.getAllResources());
        System.out.println(games.get(0).getTable().market.toString());



        //Now send a new request for buying
        tmp = games.get(0).getPlayers().get(0);
        //Get the second row of the market
        resources = games.get(0).getTable().market.seeRow(2);
        System.out.println(games.get(0).getTable().market.toString());
        mappedRes = new ArrayList<MarketResource>();
        for (int i=0; i<3;i++)
        {
            Resource res= resources.get(i);
            System.out.println(res);
            MarketResource resource = new MarketResource(res, i);
            mappedRes.add(resource);
        }



        test = new MarketRequest(MarketDimension.ROW, 2, games.get(0).getGameId(), tmp.getNickName(), mappedRes);
        games.get(0).notify(test);
        System.out.println(tmp.getAllResources());
        System.out.println(games.get(0).getTable().market.toString());

    }


    @Test
    //Test a dev card purchase
    public void BuyDevTest()
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
        tmp.addResource(0, Resource.GOLD);
        tmp.addResource(1, Resource.SERVANT);
        tmp.addResource(1, Resource.SERVANT);
        tmp.addResource(2, Resource.SHIELD);
        tmp.addResource(2, Resource.SHIELD);
        tmp.addResource(2, Resource.SHIELD);
        System.out.println(tmp.getAllResources());
        System.out.println(games.get(0).getTable().toString());
        DevCard interessata = games.get(0).getTable().getTop()[0][0];
        ArrayList<Resource> res = new ArrayList<>();
        res = interessata.getPrice();
        System.out.println(res);
        tmp.getBoard().getStrongBox().addResource(res);
        System.out.println(tmp.getAllResources());
        ArrayList<MappedResource> test = new ArrayList<>();
        for(Resource resource : interessata.getPrice())
        {
            MappedResource risorsa = new MappedResource(resource,"strongbox");
            test.add(risorsa);
        }


        Request buyDevRequest = new BuyDevRequest(games.get(0).getGameId(),tmp.getNickName(), interessata.getCardID(), test, 1);
        games.get(0).notify(buyDevRequest);
        for(int i = 0; i< 3; i++)
        {
            if (tmp.getBoard().getSlot().getFrontCards()[i] != null)
                System.out.println(tmp.getBoard().getSlot().getFrontCards()[i].getCardID());
            else
                System.out.println("null");
        }

        System.out.println(games.get(0).getTable().toString());
        System.out.println(tmp.getAllResources());
    }

}
