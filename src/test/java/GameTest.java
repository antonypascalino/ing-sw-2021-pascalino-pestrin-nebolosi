
    import com.google.gson.Gson;
    import com.google.gson.GsonBuilder;
    import com.google.gson.reflect.TypeToken;
    import it.polimi.ingsw.connection.JsonReader;
    import it.polimi.ingsw.controller.DefaultCreator;
    import it.polimi.ingsw.controller.Game;
    import it.polimi.ingsw.model.Cards.DevCard;
    import it.polimi.ingsw.model.Cards.LeaderCard;
    import it.polimi.ingsw.model.Player.BasicPlayer;
    import it.polimi.ingsw.model.Player.Player;
    import org.junit.Test;

    import java.lang.reflect.Type;
    import java.util.ArrayList;

    public class GameTest
    {
        @Test
        public void TestDevTranslation()
        {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.setPrettyPrinting().create();
            String testDev=gson.toJson(DefaultCreator.produceDevCard());
            System.out.println(testDev);
            Type devListType = new TypeToken<ArrayList<DevCard>>(){}.getType();
            ArrayList<DevCard> tmp=gson.fromJson(testDev,devListType);
            for (DevCard dev : tmp)
            {
                System.out.println(dev.getCardID());
                System.out.println(dev.getPrice());
            }
            System.out.println("Riconvertite "+ tmp.size()+ " carte");
        }

        @Test
        public void TestLeaderTranslation()
        {
            GsonBuilder builder = new GsonBuilder();

            Gson gson = builder.setPrettyPrinting().create();
            String testLeader=gson.toJson(DefaultCreator.produceLeaderCard());
            System.out.println(testLeader);

            ArrayList<LeaderCard> tmp= JsonReader.readLeaderCard(testLeader);
            for (LeaderCard leaderCard : tmp)
            {
                System.out.println(leaderCard.getID());
                System.out.println(leaderCard.toString());
            }
            System.out.println("Riconvertite "+ tmp.size()+ " carte");
        }

        /**
         * Test the generation of a basic game and getting the front cards
         */
        @Test
        public void TestGame()
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
    }
