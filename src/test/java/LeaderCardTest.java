import it.polimi.ingsw.Request.*;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.card.LeaderCard;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LeaderCardTest {
    /**
     * Tries to wrap a BasicPlayer in a new type of player after using a leadercard
     */
    @Test
    public void TestPlayExtraProd()
    {
        //First create a new game and add a player
        ArrayList<Game> games = new ArrayList<>();
        NewGameRequest request = new NewGameRequest("SickNebo", 3);
        //This code has been copied from the client handler class

                //If there's no game or the last one has reached the maximum player, it doesn't check it if games.size==0
                //Create a new game
        {
            int gameId;

                gameId=0;
            ArrayList<Player> tmp = new ArrayList<>();

            Player newPlayer = new BasicPlayer(request.getNickname());
            tmp.add(newPlayer);
            Game newGame = new Game(tmp,DefaultCreator.produceDevCard(),gameId, request.getPlayers());
            newPlayer.setGame(newGame);
            newPlayer.setTable(newGame.getTable());
            games.add(newGame);
            System.out.println("Player "+ request.getNickname()+ " added to the new game "+newGame.getGameId());
        }




        //Now send a new request for buying
        Player tmp = games.get(0).getPlayers().get(0);
        //Get the second row of the market
        ArrayList<Resource> resources = games.get(0).getTable().market.seeRow(2);
        System.out.println(games.get(0).getTable().market.toString());
        ArrayList<MarketResource> mappedRes = new ArrayList<>();
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
        mappedRes = new ArrayList<>();
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
        Game game = games.get(0);

        //it gets the first card which is a n extra prod card that requires
        LeaderCard card = DefaultCreator.produceLeaderCard().get(0);
        card.assignTo(tmp);
        //Assigns to the player what it needs for using the leader card
        tmp.getBoard().getSlot().placeCard(DefaultCreator.produceDevCard().get(42),0);
        System.out.println(card.getClassName() + " " +card.getID());
        card.playCard();
        assertEquals(game.getPlayers().get(0).getClass().getName(),"it.polimi.ingsw.model.Player.ExtraProdPlayer");
        System.out.println(game.getPlayers().get(0).getClass().getName());
    }

    @Test
    public void TestChangeRes()
    {
        //First create a new game and add a player
        ArrayList<Game> games = new ArrayList<>();
        NewGameRequest request = new NewGameRequest("SickNebo", 3);
        //This code has been copied from the client handler class
        //If there's no game on the server create the first one
        //If there's no game or the last one has reached the maximum player, it doesn't check it if games.size==0
        //Create a new game
        {
            int gameId;
            gameId=0;
            ArrayList<Player> tmp = new ArrayList<>();

            Player newPlayer = new BasicPlayer(request.getNickname());
            tmp.add(newPlayer);
            Game newGame = new Game(tmp,DefaultCreator.produceDevCard(),gameId, request.getPlayers());
            newPlayer.setGame(newGame);
            newPlayer.setTable(newGame.getTable());
            games.add(newGame);
            System.out.println("Player "+ request.getNickname()+ " added to the new game "+newGame.getGameId());
        }


        //Now send a new request for buying
        Player tmp = games.get(0).getPlayers().get(0);
        //Get the second row of the market
        ArrayList<Resource> resources = games.get(0).getTable().market.seeRow(2);
        System.out.println(games.get(0).getTable().market.toString());
        ArrayList<MarketResource> mappedRes = new ArrayList<>();
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
        mappedRes = new ArrayList<>();
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
        Game game = games.get(0);
        //it gets the first card which is a n extra prod card that requires
        LeaderCard card = DefaultCreator.produceLeaderCard().get(4);
        card.assignTo(tmp);
        tmp.getBoard().getSlot().placeCard(DefaultCreator.produceDevCard().get(0),0);
        tmp.getBoard().getSlot().placeCard(DefaultCreator.produceDevCard().get(13),1);
        tmp.getBoard().getSlot().placeCard(DefaultCreator.produceDevCard().get(14),2);
        System.out.println(card.getClassName() + " " +card.getID());
        card.playCard();
        assertEquals(game.getPlayers().get(0).getClass().getName(),"it.polimi.ingsw.model.Player.ChangeResPlayer");
        System.out.println(game.getPlayers().get(0).getClass().getName());
    }

    @Test
    public void TestDiscount()
    {
        //First create a new game and add a player
        ArrayList<Game> games = new ArrayList<>();
        NewGameRequest request = new NewGameRequest("SickNebo", 3);
        //This code has been copied from the client handler class
        //If there's no game on the server create the first one
        //If there's no game or the last one has reached the maximum player, it doesn't check it if games.size==0
        //Create a new game
        {
            int gameId;
            gameId=0;
            ArrayList<Player> tmp = new ArrayList<>();

            Player newPlayer = new BasicPlayer(request.getNickname());
            tmp.add(newPlayer);
            Game newGame = new Game(tmp,DefaultCreator.produceDevCard(),gameId, request.getPlayers());
            newPlayer.setGame(newGame);
            newPlayer.setTable(newGame.getTable());
            games.add(newGame);
            System.out.println("Player "+ request.getNickname()+ " added to the new game "+newGame.getGameId());
        }


        //Now send a new request for buying
        Player tmp = games.get(0).getPlayers().get(0);
        //Get the second row of the market
        ArrayList<Resource> resources = games.get(0).getTable().market.seeRow(2);
        System.out.println(games.get(0).getTable().market.toString());
        ArrayList<MarketResource> mappedRes = new ArrayList<>();
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
        mappedRes = new ArrayList<>();
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
        Game game = games.get(0);
        //it gets the first card which is a n extra prod card that requires
        LeaderCard card = DefaultCreator.produceLeaderCard().get(9);
        card.assignTo(tmp);
        tmp.getBoard().getSlot().placeCard(DefaultCreator.produceDevCard().get(13),1);
        tmp.getBoard().getSlot().placeCard(DefaultCreator.produceDevCard().get(27),2);
        System.out.println(card.getClassName() + " " +card.getID());
        card.playCard();
        assertEquals(game.getPlayers().get(0).getClass().getName(),"it.polimi.ingsw.model.Player.DiscountedPlayer");
        System.out.println(game.getPlayers().get(0).getClass().getName());
    }

    @Test
    public void TestExtraDep()
    {
        //First create a new game and add a player
        ArrayList<Game> games = new ArrayList<>();
        NewGameRequest request = new NewGameRequest("SickNebo", 3);
        //This code has been copied from the client handler class
        //If there's no game on the server create the first one
        //If there's no game or the last one has reached the maximum player, it doesn't check it if games.size==0
        //Create a new game
        {
            int gameId;
            gameId=0;
            ArrayList<Player> tmp = new ArrayList<>();

            Player newPlayer = new BasicPlayer(request.getNickname());
            tmp.add(newPlayer);
            Game newGame = new Game(tmp,DefaultCreator.produceDevCard(),gameId, request.getPlayers());
            newPlayer.setGame(newGame);
            newPlayer.setTable(newGame.getTable());
            games.add(newGame);
            System.out.println("Player "+ request.getNickname()+ " added to the new game "+newGame.getGameId());
        }


        //Now send a new request for buying
        Player tmp = games.get(0).getPlayers().get(0);
        //Get the second row of the market
        ArrayList<Resource> resources = games.get(0).getTable().market.seeRow(2);
        System.out.println(games.get(0).getTable().market.toString());
        ArrayList<MarketResource> mappedRes = new ArrayList<>();
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
        mappedRes = new ArrayList<>();
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
        Game game = games.get(0);
        //it gets the first card which is a n extra prod card that requires
        LeaderCard card = DefaultCreator.produceLeaderCard().get(13);
        card.assignTo(tmp);
        tmp.getBoard().getStrongBox().addResource(Resource.SHIELD);
        tmp.getBoard().getStrongBox().addResource(Resource.SHIELD);
        tmp.getBoard().getStrongBox().addResource(Resource.SHIELD);
        tmp.getBoard().getStrongBox().addResource(Resource.SHIELD);
        tmp.getBoard().getStrongBox().addResource(Resource.SHIELD);
        tmp.getBoard().getStrongBox().addResource(Resource.SHIELD);

        System.out.println(card.getClassName() + " " +card.getID());
        card.playCard();
        assertEquals(game.getPlayers().get(0).getClass().getName(),"it.polimi.ingsw.model.Player.ExtraDepositPlayer");
        System.out.println(game.getPlayers().get(0).getClass().getName());
    }
}