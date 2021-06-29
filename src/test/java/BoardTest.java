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

    @Test
    public void faithPathTest() {
        Player player = new BasicPlayer("Tester1");
        player.getBoard().getFaithPath().checkPopeSpace(1);
        player.getBoard().getFaithPath().moveForward(13);
        player.getBoard().getFaithPath().checkVictoryPoints();
        assert player.getBoard().getFaithPath().checkVaticanSection(2);
        player.getBoard().getFaithPath().moveForward(4);
        player.getBoard().getFaithPath().checkVictoryPoints();
        player.getBoard().getFaithPath().moveForward(2);
        player.getBoard().getFaithPath().checkVictoryPoints();
        player.getBoard().getFaithPath().moveForward(3);
        assert player.getBoard().getFaithPath().checkVaticanSection(2);
        player.getBoard().getFaithPath().checkVictoryPoints();
        player.getBoard().getFaithPath().moveForward(2);
        player.getBoard().getFaithPath().checkVictoryPoints();
        assert player.getBoard().getFaithPath().checkVaticanSection(3);
    }
}
