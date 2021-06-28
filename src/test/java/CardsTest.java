import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.card.ExtraProd;
import it.polimi.ingsw.model.card.LeaderCard;
import org.junit.Test;

public class CardsTest {

    @Test
    public void LeaderCardsTest() {
        Player player = new BasicPlayer("Tester");
        LeaderCard card = DefaultCreator.getLeaderFromID("CNG01");
        LeaderCard card1 = DefaultCreator.getLeaderFromID("CNG02");
        card.setPlayer(player);
        card.isEnable();
        card.equals(card1);
        card.getPowerResource();

        card = DefaultCreator.getLeaderFromID("PROD01");
        card1 = DefaultCreator.getLeaderFromID("PROD02");
        card.setPlayer(player);
        card.isEnable();
        card.equals(card1);
        card.getPowerResource();
        ((ExtraProd)card).getProducedRes();


        card = DefaultCreator.getLeaderFromID("DIS01");
        card1 = DefaultCreator.getLeaderFromID("DIS02");
        card.setPlayer(player);
        card.isEnable();
        card.equals(card1);
        card.getPowerResource();

        card = DefaultCreator.getLeaderFromID("DEP01");
        card1 = DefaultCreator.getLeaderFromID("DEP02");
        card.setPlayer(player);
        card.isEnable();
        card.equals(card1);
        card.getPowerResource();
    }
}
