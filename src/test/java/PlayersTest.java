import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.model.Colors;
import it.polimi.ingsw.model.Player.*;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Table.Table;
import it.polimi.ingsw.model.Updates.ErrorUpdate;
import it.polimi.ingsw.model.card.ChangeResource;
import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.card.ExtraDeposit;
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

    @Test
    public void TestExtraDepositPlayer() {
        Player original = new BasicPlayer("Tester1");
        Table table = new Table(DefaultCreator.produceDevCard());
        original.setTable(table);
        Player player = new ExtraDepositPlayer(original, Resource.GOLD);
        System.out.println(player.checkLevel(3)); //true
        System.out.println(player.checkLevel(5)); //false
        System.out.println(player.checkSpace(Resource.GOLD, 1));
        System.out.println(player.checkSpace(Resource.GOLD, 4));
        System.out.println(player.checkSpace(Resource.GOLD, 5));
        player.addResource(3, Resource.GOLD);
        player.removeResource(Resource.GOLD, "extradep");
        player.addResource(0, Resource.GOLD);
        player.addResource(1, Resource.STONE);
        player.switchLevels(0,1);
        player.switchLevels(1,0);
        player.switchLevels(0,3);
        player.switchLevels(3,0);
        player.removeResource(Resource.GOLD,"warehouse");
        player.getAllResources();
        player.getDeposits();
        player.checkSpace(Resource.GOLD, 3);
        player.checkSpace(Resource.STONE, 3);
        Player player1 = new ExtraDepositPlayer(player, Resource.STONE);
    }

    @Test
    public void TestExtraProdPlayer() {
        Player original = new BasicPlayer("Tester1");
        Table table = new Table(DefaultCreator.produceDevCard());
        original.setTable(table);
        ExtraProdPlayer player = new ExtraProdPlayer(original, Resource.GOLD, "PROD01");
        player.getBoard().getSlot().placeCard(DefaultCreator.produceDevCard().get(0), 0);
        player.produce("PROD01");
        player.produce(player.getBoard().getSlot().getFrontCards()[0].getCardID());
        player.getRequired();
        player.getProductionID();
        Player player1 = new ExtraProdPlayer(player, Resource.SHIELD, "PROD02");

    }

    //Non so perché non aumenta Coverage
    @Test
    public void TestAbstracPlayer() {
        Player player = new BasicPlayer("Tester1");
        player.getNickName();
        player.getVictoryPoints();
        player.getBoard();
        player.getGame();
        player.addLeaderCard(new ChangeResource(10, "BLUE","YELLOW", Resource.GOLD, "CNG01" ));
        player.addVictoryPoints(5);
        player.getLeaderCards();
        //player.produce("devG01");
        player.checkSpace(Resource.GOLD, 2);
        player.addResource(2, Resource.GOLD);
        player.removeResource(Resource.GOLD, "strongbox");
        player.getTable();
        player.checkMarketRes(new ArrayList<Resource>(), new ArrayList<Resource>());
        player.checkLevel(2);
        player.getAllResources();
        player.switchLevels(0, 1);
        player.canBuy(DefaultCreator.produceDevCard().get(0), player.getAllResources());
        player.getLeaderFromID("CNG01");
        player.getProductionID();
        player.setTable(new Table(DefaultCreator.produceDevCard()));
        player.getLeadersID();
        player.getDeposits();
    }

}
