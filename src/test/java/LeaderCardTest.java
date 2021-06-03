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
    public void wrapping()
    {
        PlayerAndGame info = RequestTest.DoubleMarket();
        Player tmp = info.player;
        Game game = info.game;
        LeaderCard card = DefaultCreator.produceLeaderCard().get(0);
        tmp.addLeaderCard(card);
        System.out.println(card.getClassName() + " " +card.getID());
        card.playCard();
    }
}
