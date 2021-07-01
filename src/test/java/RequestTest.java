import it.polimi.ingsw.controller.Request.*;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.GameHolder;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.Request.MappedResource;
import it.polimi.ingsw.controller.Request.MarketResource;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.card.LeaderCard;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
Tries to create a game and send different requests to it
 */
public class RequestTest {
    @Test
    //Request to get a column first and then a row from the market
    public void GetFromMarketRequest()
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

        //Now send a new request for buying
        Player tmp = games.get(0).getPlayers().get(0);
        tmp.setTable(games.get(0).getTable());

        //Get the second column of the market
        ArrayList<Resource> resources = games.get(0).getTable().market.seeColumn(2);
        System.out.println(games.get(0).getTable().market.toString());
        ArrayList<MarketResource> mappedRes = new ArrayList<MarketResource>();
        for (int i=0; i<3;i++)
        {
            Resource res= resources.get(i);
            System.out.println(res);
            MarketResource resource = new MarketResource(res, i);
            mappedRes.add(resource);
        }



        Request test = new MarketRequest(MarketDimension.COL, 2, games.get(0).getGameId(), tmp.getNickName(), mappedRes);
        assertTrue(test.getGameID() == (games.get(0).getGameId()));
        test.getClassName();
        games.get(0).getTurnStates().clear();
        games.get(0).notify(test);
        System.out.println(tmp.getAllResources());
        System.out.println(games.get(0).getTable().market.toString());

        /*
        NEW REQUEST


         */
        //Get the second row of the market and it should fail
        resources = games.get(0).getTable().market.seeRow(2);
        System.out.println(games.get(0).getTable().market.toString());
        mappedRes = new ArrayList<MarketResource>();
        for (int i=0; i<4;i++)
        {
            Resource res= resources.get(i);
            System.out.println(res);
            MarketResource resource = new MarketResource(res, i);
            mappedRes.add(resource);
        }

        test = new MarketRequest(MarketDimension.ROW, 2, games.get(0).getGameId(), tmp.getNickName(), mappedRes);
        games.get(0).getTurnStates().clear();
        games.get(0).notify(test);
        System.out.println(tmp.getAllResources());
        System.out.println(games.get(0).getTable().market.toString());

    }


    @Test
    public void DoubleMarket()
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

        //Now send a new request for buying
        Player tmp = games.get(0).getPlayers().get(0);
        tmp.setTable(games.get(0).getTable());

        //Get the second row of the market
        ArrayList<Resource> resources = games.get(0).getTable().market.seeRow(2);
        System.out.println(games.get(0).getTable().market.toString());
        ArrayList<MarketResource> mappedRes = new ArrayList<MarketResource>();
        for (int i=0; i<4;i++)
        {
            Resource res= resources.get(i);
            System.out.println(res);
            MarketResource resource = new MarketResource(res, i);
            mappedRes.add(resource);
        }



        Request test = new MarketRequest(MarketDimension.ROW, 2, games.get(0).getGameId(), tmp.getNickName(), mappedRes);
        games.get(0).getTurnStates().clear();
        games.get(0).notify(test);
        System.out.println(tmp.getAllResources());
        System.out.println(games.get(0).getTable().market.toString());



        //Now send a new request for buying
        tmp = games.get(0).getPlayers().get(0);
        //Get the second row of the market
        resources = games.get(0).getTable().market.seeRow(2);
        System.out.println(games.get(0).getTable().market.toString());
        mappedRes = new ArrayList<MarketResource>();
        for (int i=0; i<4;i++)
        {
            Resource res= resources.get(i);
            System.out.println(res);
            MarketResource resource = new MarketResource(res, i);
            mappedRes.add(resource);
        }



        test = new MarketRequest(MarketDimension.ROW, 2, games.get(0).getGameId(), tmp.getNickName(), mappedRes);
        games.get(0).getTurnStates().clear();
        games.get(0).notify(test);
        System.out.println(tmp.getAllResources());
        System.out.println(games.get(0).getTable().market.toString());

    }

    @Test
    //Test a dev card purchase
    public void BuyDevTest()
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

        //Now send a new request for buying
        Player tmp = games.get(0).getPlayers().get(0);
        tmp.setTable(games.get(0).getTable());

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
        System.out.println(buyDevRequest.getClassName());
        assert buyDevRequest.getGameID() == 1;
        games.get(0).getTurnStates().clear();
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
        assertEquals(interessata.getCardID(),tmp.getBoard().getSlot().getFrontCards()[1].getCardID());
    }

    @Test
    public void doubleBuyDev()
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

        //Now send a new request for buying and it doesn't buy it because there's no space in the same slot
        Player tmp = games.get(0).getPlayers().get(0);
        tmp.setTable(games.get(0).getTable());

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
        ArrayList<Resource> res;
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
        games.get(0).getTurnStates().clear();
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


        //Request to buy again another card
        interessata = games.get(0).getTable().getTop()[0][1];
        res = interessata.getPrice();
        System.out.println(res);
        tmp.getBoard().getStrongBox().addResource(res);
        System.out.println(tmp.getAllResources());
        test = new ArrayList<>();
        for(Resource resource : interessata.getPrice())
        {
            MappedResource risorsa = new MappedResource(resource,"strongbox");
            test.add(risorsa);
        }

        buyDevRequest = new BuyDevRequest(games.get(0).getGameId(),tmp.getNickName(), interessata.getCardID(), test, 1);
        games.get(0).getTurnStates().clear();
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

    @Test
    //Try to buy two cards with the same level and stack them and the server simply doesn't do that
    public void buySameLevel()
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

        //Now send a new request for buying and it doesn't buy it because there's no space in the same slot
        Player tmp = games.get(0).getPlayers().get(0);
        tmp.setTable(games.get(0).getTable());

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
        ArrayList<Resource> res;
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
        games.get(0).getTurnStates().clear();
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


        //Request to buy again another card
        interessata = games.get(0).getTable().getTop()[1][0];
        res = interessata.getPrice();
        System.out.println(res);
        tmp.getBoard().getStrongBox().addResource(res);
        System.out.println(tmp.getAllResources());
        test = new ArrayList<>();
        for(Resource resource : interessata.getPrice())
        {
            MappedResource risorsa = new MappedResource(resource,"strongbox");
            test.add(risorsa);
        }

        buyDevRequest = new BuyDevRequest(games.get(0).getGameId(),tmp.getNickName(), interessata.getCardID(), test, 1);
        games.get(0).getTurnStates().clear();
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

    @Test
    //Try to produce using two cards that have been bought
    public void testProduce()
    {
        final Socket socket = mock(Socket.class);
        GameHolder games= new GameHolder();
        ArrayList<Player> players = new ArrayList<>();
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
            when(socket.getInputStream()).thenReturn(System.in);
            players.add(new BasicPlayer("Tester1", new ClientHandler(socket, games)));
        }
        catch (IOException e) {System.out.println("IOException!");}
        Game game = new Game(players, DefaultCreator.produceDevCard(), 1, 2);
        games.add(game);

        //Now send a new request for buying and it doesn't buy it because there's no space in the same slot
        Player tmp = games.get(0).getPlayers().get(0);
        tmp.setTable(games.get(0).getTable());
        //Get the second row of the market
        tmp.addResource(0, Resource.GOLD);
        tmp.addResource(1, Resource.SERVANT);
        tmp.addResource(1, Resource.SERVANT);
        tmp.addResource(2, Resource.SHIELD);
        tmp.addResource(2, Resource.SHIELD);
        tmp.addResource(2, Resource.SHIELD);
        System.out.println("Prima di comprare la prima carta " +tmp.getAllResources());
        System.out.println("Il tavolo è così\n" + games.get(0).getTable().toString());
        DevCard interessata = games.get(0).getTable().getTop()[0][0];
        ArrayList<Resource> res;
        res = interessata.getPrice();
        System.out.println("La carta costa " +res);
        tmp.getBoard().getStrongBox().addResource(res);
        System.out.println("Aggiungo il prezzo al forziere " +tmp.getAllResources());
        ArrayList<MappedResource> test = new ArrayList<>();
        for(Resource resource : interessata.getPrice())
        {
            MappedResource risorsa = new MappedResource(resource,"strongbox");
            test.add(risorsa);
        }


        Request buyDevRequest = new BuyDevRequest(games.get(0).getGameId(),tmp.getNickName(), interessata.getCardID(), test, 1);
        games.get(0).getTurnStates().clear();
        games.get(0).notify(buyDevRequest);
        System.out.println("Carte dopo aver completato l'acquisto");
        for(int i = 0; i< 3; i++)
        {
            if (tmp.getBoard().getSlot().getFrontCards()[i] != null)
                System.out.println(tmp.getBoard().getSlot().getFrontCards()[i].getCardID());
            else
                System.out.println("null");
        }

        System.out.println("Dopo l'acquisto della prima carta il tavolo è così\n"+ games.get(0).getTable().toString());
        System.out.println("E il giocatore ha queste risorse " +tmp.getAllResources());


        //Request to buy again another card
        interessata = games.get(0).getTable().getTop()[0][0];
        res = interessata.getPrice();
        System.out.println("La seconda carta costa "+res);
        tmp.getBoard().getStrongBox().addResource(res);
        System.out.println("Aggiungo le risorse al giocatore " +tmp.getAllResources());
        test = new ArrayList<>();
        for(Resource resource : interessata.getPrice())
        {
            MappedResource risorsa = new MappedResource(resource,"strongbox");
            test.add(risorsa);
        }

        buyDevRequest = new BuyDevRequest(games.get(0).getGameId(),tmp.getNickName(), interessata.getCardID(), test, 0);
        games.get(0).getTurnStates().clear();
        games.get(0).notify(buyDevRequest);
        System.out.println("Carte dopo aver completato l'acquisto");
        for(int i = 0; i< 3; i++)
        {
            if (tmp.getBoard().getSlot().getFrontCards()[i] != null)
                System.out.println(tmp.getBoard().getSlot().getFrontCards()[i].getCardID());
            else
                System.out.println("null");
        }

        System.out.println("Dopo l'acquisto della seconda carta il tavolo è così\n"+games.get(0).getTable().toString());
        System.out.println("E il giocatore ha queste risorse "+tmp.getAllResources());

        //Create the production request containing a prod request for each card
        ArrayList<MappedResource> prodResources = new ArrayList<>();
        //Create the array with the mapped resources from get
        System.out.println("Required res for producing " );
        DevCard card = tmp.getBoard().getDevFromID(interessata.getCardID());
        if (card != null) {
            for (Resource restmp : card.getRequirements()) {
                System.out.println(restmp);
                tmp.getBoard().getStrongBox().addResource(restmp);
                MappedResource boh = new MappedResource(restmp, "strongbox");
                prodResources.add(boh);
            }
        }
        interessata.setOwner(tmp);
        System.out.println("Prima della produzione ci sono queste risorse "+tmp.getAllResources());
        ArrayList<Production> produzioni = new ArrayList<>();
        System.out.println("Il giocatore ha queste carte per produrre: " + tmp.getProductionID());
        Production nuovaProduzione = new Production(prodResources, interessata.getCardID());
        produzioni.add(nuovaProduzione);
        tmp.getBoard().getStrongBox().addResource(Resource.GOLD);
        tmp.getBoard().getStrongBox().addResource(Resource.GOLD);
        ArrayList<MappedResource> prodResources2 = new ArrayList<>();
        prodResources2.add(new MappedResource(Resource.GOLD, "strongbox"));
        prodResources2.add(new MappedResource(Resource.GOLD, "strongbox"));
        prodResources2.add(new MappedResource(Resource.STONE, "choice"));
        produzioni.add(new Production(prodResources2, "BASIC"));
        Request request = new ProduceRequest(games.get(0).getGameId(), tmp.getNickName(), produzioni);
        System.out.println("Il giocatore prima della produzione si trova in posizione "+tmp.getBoard().getFaithPath().getAdvancement());
        games.get(0).notify(request);               //Questo fallisce perché non si possomno fare due azioni nello stesso turno
        games.get(0).getTurnStates().clear();       //clear() è chiamata sui turn state del Game per permettere l'acquisto di una carta e la propduzione nello stesso turno
        games.get(0).notify(request);
        System.out.println("La carta richiede per la produzione queste risorse "+ interessata.getRequirements());
        System.out.println("Dopo la produzione ci sono queste risorse "+ tmp.getAllResources());
        System.out.println("Il giocatore dopo la produzione si trova in posizione "+tmp.getBoard().getFaithPath().getAdvancement());
        request.getClassName();
        assert request.getGameID() == 1;
    }

    @Test
    public void DiscardLeaderRequestTest() {
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

        Player tmp = games.get(0).getPlayers().get(0);
        tmp.setTable(games.get(0).getTable());
        LeaderCard leaderCard = DefaultCreator.produceLeaderCard().get(0);
        tmp.addLeaderCard(leaderCard);
        leaderCard = DefaultCreator.produceLeaderCard().get(1);
        tmp.addLeaderCard(leaderCard);
        System.out.println(tmp.getLeaderCards());
        DiscardLeaderRequest discardReq = new DiscardLeaderRequest(1, "Tester1", leaderCard.getID());
        game.notify(discardReq);
        assert tmp.getLeadersID().size() == 1;
        assert tmp.getBoard().getFaithPath().getAdvancement() == 1;
        discardReq.getClassName();
        assert discardReq.getGameID() == 1;
    }

    @Test
    public void EndTurnRequestTest() {
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

        Player tmp = games.get(0).getPlayers().get(0);
        tmp.setTable(games.get(0).getTable());
        EndTurnRequest endTurnReq = new EndTurnRequest(tmp.getNickName(), 1);
        game.notify(endTurnReq); //Non va a buon fine perché non è possibile finire il turno senza aver fatto prima un'azione
        game.getTurnStates().add(TurnState.GET_FROM_MARKET); //Per far si che al game risulti che sia stata fatta un azione
        game.notify(endTurnReq);
        endTurnReq.getClassName();
        assert endTurnReq.getGameID() == 1;

        //Ripete la stessa azione per verificare quando, a finire il turno, è l'ultmo player del giro
        tmp = games.get(0).getPlayers().get(1);
        endTurnReq = new EndTurnRequest(tmp.getNickName(), 1);
        game.notify(endTurnReq); //Non va a buon fine perché non è possibile finire il turno senza aver fatto prima un'azione
        game.getTurnStates().add(TurnState.GET_FROM_MARKET); //Per far si che al game risulti che sia stata fatta un azione
        game.notify(endTurnReq);
        endTurnReq.getClassName();
        assert endTurnReq.getGameID() == 1;
    }

    @Test
    public void InitalPlayersSetRequestTest() {
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

        Player player = game.getPlayers().get(0);
        ArrayList<MarketResource> marketRes = new ArrayList<>();
        marketRes.add(new MarketResource(Resource.GOLD, 2));
        marketRes.add(new MarketResource(Resource.GOLD, 2));
        ArrayList<String> leadersChosen = new ArrayList<>();
        leadersChosen.add(DefaultCreator.produceLeaderCard().get(0).getID());
        leadersChosen.add(DefaultCreator.produceLeaderCard().get(1).getID());
        InitialPlayersSetRequest request = new InitialPlayersSetRequest(1, player.getNickName(), marketRes, leadersChosen);
        game.notify(request);
        request.getClassName();
        request.createUpdate(player, game);
        assert request.getGameID() == 1;
        assert player.getLeaderCards().size() == 2;

        //Ripete la stessa azione per far si che tutti i giocatori nella partita abbiano fatto le proprie scelte così da far iniziare la partita
        player = game.getPlayers().get(1);
        marketRes = new ArrayList<>();
        marketRes.add(new MarketResource(Resource.GOLD, 2));
        marketRes.add(new MarketResource(Resource.GOLD, 2));
        leadersChosen = new ArrayList<>();
        leadersChosen.add(DefaultCreator.produceLeaderCard().get(2).getID());
        leadersChosen.add(DefaultCreator.produceLeaderCard().get(3).getID());
        request = new InitialPlayersSetRequest(1, player.getNickName(), marketRes, leadersChosen);
        game.notify(request);
        request.getClassName();
        request.createUpdate(player, game);
        assert request.getGameID() == 1;
        assert player.getLeaderCards().size() == 2;
        assert player.getBoard().getWareHouse().getResources().contains(Resource.GOLD);
    }

    @Test
    public void MoveRequestTest() {
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

        Player player = game.getPlayers().get(0);
        player.getBoard().getWareHouse().addResource(0, Resource.GOLD);
        //Questa prima request fallisce perchè il giocatore non ha extra deposits
        MoveRequest request = new MoveRequest(player.getNickName(), 1, 0, 4);
        game.notify(request);
        //Questa request invece ha ha successo
        request = new MoveRequest(player.getNickName(), 1, 0, 1);
        game.notify(request);
        assert request.getGameID() == 1;
        request.getClassName();
        assert player.getBoard().getWareHouse().getLevels().get(0)[0] == Resource.EMPTY;
        assert player.getBoard().getWareHouse().getLevels().get(1)[0] == Resource.GOLD;
    }

    @Test
    public void playLeaderRequestTest() {
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
        Player player = game.getPlayers().get(0);
        player.setGame(game);
        LeaderCard leaderCard = DefaultCreator.getLeaderFromID("DEP01");
        leaderCard.setPlayer(player);
        player.addLeaderCard(leaderCard);
        leaderCard = DefaultCreator.getLeaderFromID("DEP02");
        leaderCard.setPlayer(player);
        player.addLeaderCard(leaderCard);
        player.getBoard().getStrongBox().addResource(Resource.STONE);
        player.getBoard().getStrongBox().addResource(Resource.STONE);
        player.getBoard().getStrongBox().addResource(Resource.STONE);
        player.getBoard().getStrongBox().addResource(Resource.STONE);
        player.getBoard().getStrongBox().addResource(Resource.STONE);
        PlayLeaderRequest request = new PlayLeaderRequest(player.getNickName(), 1, "DEP01");
        game.notify(request);
        assert request.getGameID() == 1;
        request.getClassName();
    }

    //Non testa niente, metodo creato per coerenza coverage
    @Test
    public void PongRequestTest() {
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
        Player player = game.getPlayers().get(0);
        PongRequest request = new PongRequest();
        request.validRequest(game.getTurnStates());
        request.canBePlayed(player);
        request.getGameID();
        request.getPlayerID();
        request.createUpdate(player, game);
        request.handle(player, game);
        request.getClassName();
    }

    //Non testa niente, metodo creato per coerenza coverage
    @Test
    public void NewGameRequestTest() {
        final Socket socket = mock(Socket.class);
        GameHolder games = new GameHolder();
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
        Player player = game.getPlayers().get(0);
        NewGameRequest request = new NewGameRequest(player.getNickName(), 2);
        request.validRequest(game.getTurnStates());
        request.canBePlayed(player);
        request.getGameID();
        request.getPlayerID();
        request.createUpdate(player, game);
        request.handle(player, game);
        request.getClassName();
    }
}
