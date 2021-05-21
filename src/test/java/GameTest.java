
    import it.polimi.ingsw.controller.DefaultCreator;
    import it.polimi.ingsw.controller.Game;
    import it.polimi.ingsw.model.card.DevCard;
    import it.polimi.ingsw.model.Player.BasicPlayer;
    import it.polimi.ingsw.model.Player.Player;
    import org.junit.Test;

    import java.util.ArrayList;
    import java.util.Collections;

    //First test of the server
    public class GameTest
    {


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
