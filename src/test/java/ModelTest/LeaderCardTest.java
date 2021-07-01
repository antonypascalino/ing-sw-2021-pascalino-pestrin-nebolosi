package ModelTest;

import it.polimi.ingsw.controller.Request.*;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.GameHolder;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.card.LeaderCard;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LeaderCardTest {
    /**
     * Tries to wrap a BasicPlayer in a new type of player after using a leadercard
     */
    @Test
    public void TestPlayExtraProd()
    {
        final Socket socket = mock(Socket.class);
        GameHolder games= new GameHolder();
        ArrayList<Player> players = new ArrayList<>();
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
            when(socket.getInputStream()).thenReturn(System.in);
            players.add(new BasicPlayer("Tester1", new ClientHandler(socket, games)));
            players.add(new BasicPlayer("Tester2", new ClientHandler(socket, games)));
        }
        catch (IOException e) {System.out.println("IOException!");}
        Game game = new Game(players, DefaultCreator.produceDevCard(), 1, 2);
        games.add(game);
        players.get(0).setTable(game.getTable());
        players.get(1).setTable(game.getTable());
        players.get(0).setGame(game);
        players.get(1).setGame(game);

        //Now send a new request for buying
        Player tmp = games.get(0).getPlayers().get(0);
        //Get the second row of the market
        ArrayList<Resource> resources = games.get(0).getTable().market.seeRow(2);
        System.out.println(games.get(0).getTable().market.toString());
        ArrayList<MarketResource> mappedRes = new ArrayList<>();
        for (int i=0; i<4;i++)
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
        for (int i=0; i<4;i++)
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
        game = games.get(0);

        //it gets the first card which is a n extra prod card that requires
        LeaderCard card = DefaultCreator.produceLeaderCard().get(0);
        card.assignTo(tmp);
        LeaderCard card1 = DefaultCreator.produceLeaderCard().get(3);
        card1.assignTo(tmp);
        //Assigns to the player what it needs for using the leader card
        tmp.getBoard().getSlot().placeCard(DefaultCreator.getDevFromID("devB201"),0);
        System.out.println(card.getClassName() + " " +card.getID());
        card.playCard();
        assert (game.getPlayers().get(0).getClass().getName().equals("it.polimi.ingsw.model.Player.ExtraProdPlayer"));
        System.out.println(game.getPlayers().get(0).getClass().getName());
    }

    @Test
    public void TestChangeRes()
    {
        final Socket socket = mock(Socket.class);
        GameHolder games= new GameHolder();
        ArrayList<Player> players = new ArrayList<>();
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
            when(socket.getInputStream()).thenReturn(System.in);
            players.add(new BasicPlayer("Tester1", new ClientHandler(socket, games)));
            players.add(new BasicPlayer("Tester2", new ClientHandler(socket, games)));
        }
        catch (IOException e) {System.out.println("IOException!");}
        Game game = new Game(players, DefaultCreator.produceDevCard(), 1, 2);
        games.add(game);
        players.get(0).setTable(game.getTable());
        players.get(1).setTable(game.getTable());
        players.get(0).setGame(game);
        players.get(1).setGame(game);

        //Now send a new request for buying
        Player tmp = games.get(0).getPlayers().get(0);
        //Get the second row of the market
        ArrayList<Resource> resources = games.get(0).getTable().market.seeRow(2);
        System.out.println(games.get(0).getTable().market.toString());
        ArrayList<MarketResource> mappedRes = new ArrayList<>();
        for (int i=0; i<4;i++)
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
        for (int i=0; i<4;i++)
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
        game = games.get(0);
        //it gets the first card which is a n extra prod card that requires
        LeaderCard card = DefaultCreator.produceLeaderCard().get(4);
        card.assignTo(tmp);
        LeaderCard card1 = DefaultCreator.produceLeaderCard().get(3);
        card1.assignTo(tmp);
        tmp.getBoard().getSlot().placeCard(DefaultCreator.getDevFromID("devP101"),0);
        tmp.getBoard().getSlot().placeCard(DefaultCreator.getDevFromID("devG101"),1);
        tmp.getBoard().getSlot().placeCard(DefaultCreator.getDevFromID("devG102"),2);
        System.out.println(card.getClassName() + " " +card.getID());
        card.playCard();
        assert(game.getPlayers().get(0).getClass().getName().equals("it.polimi.ingsw.model.Player.ChangeResPlayer"));
        System.out.println(game.getPlayers().get(0).getClass().getName());
    }

    @Test
    public void TestDiscount()
    {
        final Socket socket = mock(Socket.class);
        GameHolder games= new GameHolder();
        ArrayList<Player> players = new ArrayList<>();
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
            when(socket.getInputStream()).thenReturn(System.in);
            players.add(new BasicPlayer("Tester1", new ClientHandler(socket, games)));
            players.add(new BasicPlayer("Tester2", new ClientHandler(socket, games)));
        }
        catch (IOException e) {System.out.println("IOException!");}
        Game game = new Game(players, DefaultCreator.produceDevCard(), 1, 2);
        games.add(game);
        players.get(0).setTable(game.getTable());
        players.get(1).setTable(game.getTable());
        players.get(0).setGame(game);
        players.get(1).setGame(game);

        //Now send a new request for buying
        Player tmp = games.get(0).getPlayers().get(0);
        //Get the second row of the market
        ArrayList<Resource> resources = games.get(0).getTable().market.seeRow(2);
        System.out.println(games.get(0).getTable().market.toString());
        ArrayList<MarketResource> mappedRes = new ArrayList<>();
        for (int i=0; i<4;i++)
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
        for (int i=0; i<4;i++)
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
        game = games.get(0);
        //it gets the first card which is a n extra prod card that requires
        LeaderCard card = DefaultCreator.produceLeaderCard().get(9);
        card.assignTo(tmp);
        LeaderCard card1 = DefaultCreator.produceLeaderCard().get(3);
        card1.assignTo(tmp);
        tmp.getBoard().getSlot().placeCard(DefaultCreator.getDevFromID("devY101"),1);
        tmp.getBoard().getSlot().placeCard(DefaultCreator.getDevFromID("devG101"),2);
        System.out.println(card.getClassName() + " " +card.getID());
        card.playCard();
        assert (game.getPlayers().get(0).getClass().getName().equals("it.polimi.ingsw.model.Player.DiscountedPlayer"));
        System.out.println(game.getPlayers().get(0).getClass().getName());
    }

    @Test
    public void TestExtraDep()
    {
        final Socket socket = mock(Socket.class);
        GameHolder games= new GameHolder();
        ArrayList<Player> players = new ArrayList<>();
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
            when(socket.getInputStream()).thenReturn(System.in);
            players.add(new BasicPlayer("Tester1", new ClientHandler(socket, games)));
            players.add(new BasicPlayer("Tester2", new ClientHandler(socket, games)));
        }
        catch (IOException e) {System.out.println("IOException!");}
        Game game = new Game(players, DefaultCreator.produceDevCard(), 1, 2);
        games.add(game);
        players.get(0).setTable(game.getTable());
        players.get(1).setTable(game.getTable());
        players.get(0).setGame(game);
        players.get(1).setGame(game);

        //Now send a new request for buying
        Player tmp = games.get(0).getPlayers().get(0);
        //Get the second row of the market
        ArrayList<Resource> resources = games.get(0).getTable().market.seeRow(2);
        System.out.println(games.get(0).getTable().market.toString());
        ArrayList<MarketResource> mappedRes = new ArrayList<>();
        for (int i=0; i<4;i++)
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
        for (int i=0; i<4;i++)
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
        game = games.get(0);
        //it gets the first card which is a n extra prod card that requires
        LeaderCard card = DefaultCreator.produceLeaderCard().get(13);
        card.assignTo(tmp);
        LeaderCard card1 = DefaultCreator.produceLeaderCard().get(3);
        card1.assignTo(tmp);
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
