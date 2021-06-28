import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import org.junit.Test;

public class BoardTest {

    @Test
    public void boardTest() {
        Player player = new BasicPlayer("Tester1");
        player.getBoard().setPlayer(player);
        player.getBoard().getPlayer();
        player.getBoard().getTempBox();
        player.getBoard().getDevFromID("FakeCard");
        player.getBoard().getSlot().placeCard(DefaultCreator.getDevFromID("devG101"),1);
        assert player.getBoard().getDevFromID("devG101") != null;

    }
}
