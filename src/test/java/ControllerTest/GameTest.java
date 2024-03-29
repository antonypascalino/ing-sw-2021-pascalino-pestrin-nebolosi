package ControllerTest;

import it.polimi.ingsw.controller.Request.NewGameRequest;
    import it.polimi.ingsw.controller.Request.Request;
    import it.polimi.ingsw.server.ClientHandler;
    import it.polimi.ingsw.server.GameHolder;
    import it.polimi.ingsw.controller.DefaultCreator;
    import it.polimi.ingsw.controller.Game;
    import it.polimi.ingsw.model.card.DevCard;
    import it.polimi.ingsw.model.Player.BasicPlayer;
    import it.polimi.ingsw.model.Player.Player;
    import org.junit.Test;

    import java.io.ByteArrayOutputStream;
    import java.io.IOException;
    import java.net.Socket;
    import java.util.ArrayList;

    import static org.junit.Assert.assertEquals;
    import static org.mockito.Mockito.mock;
    import static org.mockito.Mockito.when;

    //First test of the server
    public class GameTest
    {
        /**
         * Test the generation of a basic game and getting the front cards
         * from the table
         * After testing the correct sarialization and deserialization of the Request through the server
         */
        @Test
        public void TestRequestGame()
        {
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
                    Game newGame = new Game(tmp,DefaultCreator.produceDevCard(),gameId,((NewGameRequest) request).getPlayers());
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
            assertEquals(games.get(0).getPlayers().get(0).getNickName(), "SickNebo");

        }

        @Test
        public void TestEasyGame()
        {
            ArrayList<Player> players = new ArrayList<Player>();
            Player tmp = new BasicPlayer("SickNebo");
            players.add(tmp);
            Game test = new Game(players, DefaultCreator.produceDevCard(), 2,4);
            DevCard[][] prova = test.getTable().getTop();
            for (int i = 0; i < prova.length; i++) {
                for (int j = 0; j < prova[0].length; j++)
                    System.out.println(prova[i][j].getCardID());
            }

        }

        @Test
        public void TestNewGameUpdate1player() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new BasicPlayer("Tester1"));
        Game game = new Game(players, DefaultCreator.produceDevCard(), 1, 1);
        game.createNewGameUpdate();
        }

        @Test
        public void TestNewGameUpdate2players() {
            ArrayList<Player> players = new ArrayList<>();
            players.add(new BasicPlayer("Tester1"));
            players.add(new BasicPlayer("Tester2"));
            Game game = new Game(players, DefaultCreator.produceDevCard(), 1, 2);
            game.createNewGameUpdate();
        }

        @Test
        public void TestNewGameUpdate3players() {
            ArrayList<Player> players = new ArrayList<>();
            players.add(new BasicPlayer("Tester1"));
            players.add(new BasicPlayer("Tester2"));
            players.add(new BasicPlayer("Tester3"));
            Game game = new Game(players, DefaultCreator.produceDevCard(), 1, 3);
            game.createNewGameUpdate();
        }

        @Test
        public void TestNewGameUpdate4players() {
            ArrayList<Player> players = new ArrayList<>();
            players.add(new BasicPlayer("Tester1"));
            players.add(new BasicPlayer("Tester2"));
            players.add(new BasicPlayer("Tester3"));
            players.add(new BasicPlayer("Tester4"));
            Game game = new Game(players, DefaultCreator.produceDevCard(), 1, 4);
            game.createNewGameUpdate();
        }

        @Test
        public void TestEndGame(){
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
            players.get(0).addVictoryPoints(10);
            game.endgame();
        }

        @Test
        public void TestStartGame(){
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
            game.start();
        }

        @Test
        public void TestFPAdvancement() {
            ArrayList<Player> players = new ArrayList<>();
            players.add(new BasicPlayer("Tester1"));
            players.add(new BasicPlayer("Tester2"));
            Game game = new Game(players, DefaultCreator.produceDevCard(), 1, 2);
            players.get(0).getBoard().getFaithPath().moveForward(5);
            players.get(1).getBoard().getFaithPath().moveForward(8);
            game.fpAdvancement(3, 1);
        }

        @Test
        public void TestChangePlayer(){
            ArrayList<Player> players = new ArrayList<>();
            players.add(new BasicPlayer("Tester1"));
            players.add(new BasicPlayer("Tester2"));
            Game game = new Game(players, DefaultCreator.produceDevCard(), 1, 2);
            players.get(0).getBoard().getFaithPath().moveForward(5);
            players.get(1).getBoard().getFaithPath().moveForward(8);
            game.fpAdvancement(3, 1);
        }

        @Test
        public void genericGameTest() {
            ArrayList<Player> players = new ArrayList<>();
            players.add(new BasicPlayer("Tester1"));
            players.add(new BasicPlayer("Tester2"));
            Game game = new Game(players, DefaultCreator.produceDevCard(), 1, 3);
            game.addPlayer(new BasicPlayer("Tester3"));
            assert game.getMax() == 3;
            game.setCurrPlayerInt(1);
            assert game.getCurrPlayerInt() == 1;
            Player currPlayer = game.getCurrPlayer();
            game.setNextPlayer(currPlayer);
        }


    }
