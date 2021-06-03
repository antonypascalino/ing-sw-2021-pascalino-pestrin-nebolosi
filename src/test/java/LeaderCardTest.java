import it.polimi.ingsw.Request.*;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.card.LeaderCard;
import org.junit.Test;

import java.util.ArrayList;

public class LeaderCardTest {
    /**
     * Tries to wrap a BasicPlayer in a new type of player after using a leadercard
     */
    @Test
    public void TestPlayExtraProd()
    {
        //First create a new game and add a player
        ArrayList<Game> games = new ArrayList<>();
        Request request = new NewGameRequest("SickNebo", 3);
        //This code has been copied from the client handler class
        if(request instanceof NewGameRequest)
        {
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

                    Player newPlayer = new BasicPlayer(((NewGameRequest) request).getNickname());
                    tmp.add(newPlayer);
                    Game newGame = new Game(tmp,DefaultCreator.produceDevCard(),gameId,((NewGameRequest) request).getPlayers());
                    newPlayer.setGame(newGame);
                    games.add(newGame);
                    System.out.println("Player "+((NewGameRequest) request).getNickname()+ " added to the new game "+newGame.getGameId());
                }
                //If it hasn't alrady reached the maximum numner of players
                //add the new player
                else
                {
                    Player newPlayer = new BasicPlayer(((NewGameRequest) request).getNickname());
                    lastGame.addPlayer(newPlayer);
                    newPlayer.setGame(lastGame);
                    System.out.println("Player "+((NewGameRequest) request).getNickname()+ " added to game "+lastGame.getGameId());
                }
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
        Game game = games.get(0);
        //it gets the first card which is a n extra prod card that requires
        LeaderCard card = DefaultCreator.produceLeaderCard().get(0);
        card.assignTo(tmp);
        tmp.getBoard().getSlot().placeCard(DefaultCreator.produceDevCard().get(42),0);
        System.out.println(card.getClassName() + " " +card.getID());
        card.playCard();
        System.out.println(game.getPlayers().get(0).getClass().getName());
    }
}
