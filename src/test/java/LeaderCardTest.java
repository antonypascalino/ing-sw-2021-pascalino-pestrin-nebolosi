import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.card.LeaderCard;
import org.junit.Test;

public class LeaderCardTest {
    /**
     * Tries to wrap a BasicPlayer in a new type of player after using a leadercard
     */
    @Test
    public void TestPlayExtraProd()
    {
        PlayerAndGame info = RequestTest.DoubleMarket();
        Player tmp = info.player;
        Game game = info.game;
        //it gets the first card which is a n extra prod card that requires
        LeaderCard card = DefaultCreator.produceLeaderCard().get(0);
        card.assignTo(tmp);
        tmp.getBoard().getSlot().placeCard(DefaultCreator.produceDevCard().get(42),0);
        System.out.println(card.getClassName() + " " +card.getID());
        card.playCard();
        System.out.println(game.getPlayers().get(0).getClass().getName());
    }
}
