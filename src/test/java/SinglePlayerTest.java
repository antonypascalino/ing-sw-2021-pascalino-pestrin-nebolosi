import it.polimi.ingsw.controller.Request.*;
import it.polimi.ingsw.connection.ClientHandler;
import it.polimi.ingsw.connection.GameHolder;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.SinglePlayer.*;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SinglePlayerTest {

    @Test
    public void GetFromMarketRequest() {
        final Socket socket = mock(Socket.class);
        GameHolder games = new GameHolder();
        ArrayList<Player> players = new ArrayList<>();
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
            when(socket.getInputStream()).thenReturn(System.in);
            Player player = new BasicPlayer("Tester1", new ClientHandler(socket, games));
            players.add(player);
        } catch (IOException e) {
            System.out.println("IOException!");
        }
        Game game = new SinglePlayerGame(players, DefaultCreator.produceDevCard(), 1);
        games.add(game);
        players.get(0).setTable(game.getTable());
        players.get(0).setGame(game);

        //Now send a new request for buying
        Player tmp = games.get(0).getPlayers().get(0);
        //Get the second row of the market
        ArrayList<Resource> resources = games.get(0).getTable().market.seeRow(2);
        System.out.println(games.get(0).getTable().market.toString());
        ArrayList<MarketResource> mappedRes = new ArrayList<MarketResource>();
        for (int i = 0; i < 4; i++) {
            Resource res = resources.get(i);
            System.out.println(res);
            MarketResource resource;
            if (i != 3) resource = new MarketResource(res, i);
            else resource = new MarketResource(res, 2);
            mappedRes.add(resource);
        }

        Request test = new MarketRequest(MarketDimension.ROW, 2, games.get(0).getGameId(), tmp.getNickName(), mappedRes);
        games.get(0).getTurnStates().add(TurnState.END_TURN);
        games.get(0).notify(test);
        System.out.println(tmp.getAllResources());
        System.out.println(games.get(0).getTable().market.toString());
        game.changePlayer(new BasicPlayer("Tester1"), new BasicPlayer("Tester2"));
    }

    @Test
    public void TestDrawToken() {
        ArrayList<Player> tmp = new ArrayList<Player>();
        Player player = new BasicPlayer("Tester1");
        tmp.add(player);
        SinglePlayerGame game = new SinglePlayerGame(tmp, DefaultCreator.produceDevCard(), 0);
        System.out.println(game.getTokenList());
        System.out.println(game.drawToken());
        System.out.println(game.getTokenList());
    }

    @Test
    public void TestSinglePlayerWins() {
        final Socket socket = mock(Socket.class);
        GameHolder games = new GameHolder();
        ArrayList<Player> players = new ArrayList<>();
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
            when(socket.getInputStream()).thenReturn(System.in);
            players.add(new BasicPlayer("Tester1", new ClientHandler(socket, games)));
        } catch (IOException e) {
            System.out.println("IOException!");
        }
        SinglePlayerGame game = new SinglePlayerGame(players, DefaultCreator.produceDevCard(), 0);
        game.playerWins();
        game.lorenzoWins();

    }

    @Test
    public void TestFPAdvancement() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new BasicPlayer("Tester1"));
        SinglePlayerGame game = new SinglePlayerGame(players, DefaultCreator.produceDevCard(), 0);
        players.get(0).getBoard().getFaithPath().moveForward(1);
        game.fpAdvancement(3, 12);
    }

    @Test
    public void TestTokens() {
        final Socket socket = mock(Socket.class);
        GameHolder games = new GameHolder();
        ArrayList<Player> players = new ArrayList<>();
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
            when(socket.getInputStream()).thenReturn(System.in);
            players.add(new BasicPlayer("Tester1", new ClientHandler(socket, games)));
        } catch (IOException e) {
            System.out.println("IOException!");
        }
        SinglePlayerGame game = new SinglePlayerGame(players, DefaultCreator.produceDevCard(), 0);
        Token token = new DiscardCards("YELLOW");
        System.out.println(token.announceAction(game));
        System.out.println(token);
        token.activateEffect(game);
        token = new Move2();
        System.out.println(token.announceAction(game));
        System.out.println(token);
        token.activateEffect(game);
        token = new Move1();
        System.out.println(token.announceAction(game));
        System.out.println(token);
        token.activateEffect(game);
    }
}