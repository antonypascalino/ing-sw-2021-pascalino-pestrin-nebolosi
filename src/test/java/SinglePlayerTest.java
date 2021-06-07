import it.polimi.ingsw.Request.*;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.controller.SinglePlayer.SinglePlayerGame;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import org.junit.Test;

import java.util.ArrayList;

public class SinglePlayerTest {

    @Test
    public void GetFromMarketRequest()
    {
        //First create a new game and add a player
        ArrayList<Game> games = new ArrayList<>();
        Request request = new NewGameRequest("Tester1", 1);
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
                Player player = new BasicPlayer(((NewGameRequest) request).getNickname());
                tmp.add(player);
                Game newGame = new SinglePlayerGame(tmp, DefaultCreator.produceDevCard(), gameId);
                player.setTable(newGame.getTable());
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
    public void TestDrawToken(){
        ArrayList<Player> tmp = new ArrayList<Player>();
        Player player = new BasicPlayer("Tester1");
        tmp.add(player);
        SinglePlayerGame game = new SinglePlayerGame(tmp, DefaultCreator.produceDevCard(), 0);
        System.out.println(game.getTokenList());
        System.out.println(game.drawToken());
        System.out.println(game.getTokenList());
    }

    @Test
    public void TestSinglePlayerWins(){
        ArrayList<Player> tmp = new ArrayList<Player>();
        Player player = new BasicPlayer("Tester1");
        tmp.add(player);
        SinglePlayerGame game = new SinglePlayerGame(tmp, DefaultCreator.produceDevCard(), 0);
        game.playerWins();
    }

    @Test
    public void TestFPAdvancement(){
        ArrayList<Player> players = new ArrayList<>();
        players.add(new BasicPlayer("Tester1"));
        SinglePlayerGame game = new SinglePlayerGame(players, DefaultCreator.produceDevCard(), 0);
        players.get(0).getBoard().getFaithPath().moveForward(1);
        game.fpAdvancement(3, 12);
    }
}
