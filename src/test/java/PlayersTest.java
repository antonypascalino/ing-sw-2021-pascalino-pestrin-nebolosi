import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.model.Player.BasicPlayer;
import it.polimi.ingsw.model.Player.ChangeResPlayer;
import it.polimi.ingsw.model.Player.DiscountedPlayer;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Table.Table;
import it.polimi.ingsw.model.card.DevCard;
import org.junit.Test;

import java.util.ArrayList;

public class PlayersTest {

    @Test
    public void TestChangeResPlayer() {
        Player original = new BasicPlayer("Tester1");
        Table table = new Table(DefaultCreator.produceDevCard());
        original.setTable(table);
        ChangeResPlayer player = new ChangeResPlayer(original, Resource.EMPTY);
        System.out.println(player.checkMarketRes(original.getTable().market.seeRow(1), original.getTable().market.seeRow(1)));
        System.out.println(player.checkMarketRes(original.getTable().market.seeRow(2), original.getTable().market.seeRow(1)));
        System.out.println(player.getChange());
        ChangeResPlayer player1 = new ChangeResPlayer(player, Resource.GOLD);
    }

    @Test
    public void TestDiscountedPlayer() {
        Player original = new BasicPlayer("Tester1");
        Table table = new Table(DefaultCreator.produceDevCard());
        original.setTable(table);
        DevCard card = table.getTop()[0][0];
        DiscountedPlayer player = new DiscountedPlayer(original, card.getPrice().get(0));
        System.out.println("Price is: " + card.getPrice());
        player.getBoard().getStrongBox().addResource(card.getPrice());
        System.out.println("Player has these resources: " + player.getAllResources());
        System.out.println(player.canBuy(card, original.getAllResources()));
        player.getBoard().getStrongBox().removeResource(card.getPrice().get(0));
        System.out.println("Player has these resources: " + player.getAllResources());
        System.out.println(player.canBuy(card, original.getAllResources()));
        System.out.println(player.getDiscount());
        DiscountedPlayer player1 = new DiscountedPlayer(player, Resource.GOLD);
    }
}
