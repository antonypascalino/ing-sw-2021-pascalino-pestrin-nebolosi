import it.polimi.ingsw.connection.ClientHandler;
import it.polimi.ingsw.connection.GameHolder;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.model.Player.*;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Table.Table;
import it.polimi.ingsw.Updates.ErrorUpdate;
import it.polimi.ingsw.model.card.ChangeResource;
import it.polimi.ingsw.model.card.DevCard;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayersTest {

    @Test
    public void TestChangeResPlayer() {
        Player original = new BasicPlayer("Tester1");
        Table table = new Table(DefaultCreator.produceDevCard());
        original.setTable(table);
        ChangeResPlayer player = new ChangeResPlayer(original, Resource.GOLD);
        assert (player.checkMarketRes(original.getTable().market.seeRow(1), original.getTable().market.seeRow(1)));
        assert (!player.checkMarketRes(original.getTable().market.seeRow(2), original.getTable().market.seeRow(1)));
        assert (player.getChange().contains(Resource.GOLD) && player.getChange().size() == 1);
        ChangeResPlayer player1 = new ChangeResPlayer(player, Resource.STONE);
        assert (player1.getChange().contains(Resource.GOLD));
        assert (player1.getChange().contains(Resource.STONE));
    }

    @Test
    public void TestDiscountedPlayer() {
        Player original = new BasicPlayer("Tester1");
        Table table = new Table(DefaultCreator.produceDevCard());
        original.setTable(table);
        DevCard card = table.getTop()[0][0];
        DiscountedPlayer player = new DiscountedPlayer(original, card.getPrice().get(0));
        assert (card.getPrice().containsAll(player.getDiscount()));
        player.getBoard().getStrongBox().addResource(card.getPrice());
        player.getAllResources();
        assert (player.canBuy(card, original.getAllResources()));
        assert (player.getBoard().getStrongBox().removeResource(card.getPrice().get(0)));
        assert (player.canBuy(card, original.getAllResources()));
        assert player.getDiscount().contains(card.getPrice().get(0));
        DiscountedPlayer player1 = new DiscountedPlayer(player, Resource.GOLD);
        assert (player1.getDiscount().contains(Resource.GOLD));
        assert (player1.getDiscount().contains(card.getPrice().get(0)));
    }

    @Test
    public void TestExtraDepositPlayer() {
        Player original = new BasicPlayer("Tester1");
        Table table = new Table(DefaultCreator.produceDevCard());
        original.setTable(table);
        Player player = new ExtraDepositPlayer(original, Resource.GOLD);
        assert player.checkLevel(3); //true
        assert !player.checkLevel(5); //false
        assert player.checkSpace(Resource.GOLD, 1);
        assert !player.checkSpace(Resource.GOLD, 4);
        assert !player.checkSpace(Resource.GOLD, 5);
        player.addResource(3, Resource.GOLD);
        player.addResource(1, Resource.GOLD);
        assert !player.checkSwitch(6,1);
        assert player.checkSwitch(2,1);
        assert !player.checkSwitch(2,3);
        assert player.checkSwitch(3,1);
        player.removeResource(Resource.GOLD, "extradeposit");
        player.addResource(0, Resource.GOLD);
        player.addResource(1, Resource.STONE);
        player.switchLevels(0,1);
        player.switchLevels(1,0);
        player.switchLevels(0,3);
        player.switchLevels(3,0);
        player.removeResource(Resource.GOLD,"warehouse");
        player.getAllResources();
        System.out.println(player.getDeposits());
        assert player.checkSpace(Resource.GOLD, 3);
        assert !player.checkSpace(Resource.STONE, 3);
        Player player1 = new ExtraDepositPlayer(player, Resource.STONE);
        assert player1.getDeposits().size() == 5;

    }

    @Test
    public void TestExtraProdPlayer() {
        Player original = new BasicPlayer("Tester1");
        Table table = new Table(DefaultCreator.produceDevCard());
        original.setTable(table);
        ExtraProdPlayer player = new ExtraProdPlayer(original, Resource.GOLD, "PROD01");
        DevCard card = DefaultCreator.produceDevCard().get(0);
        player.getBoard().getSlot().placeCard(card, 0);
        assert player.getBoard().getSlot().getAllCards().contains(card);
        player.produce("PROD01");
        player.produce(player.getBoard().getSlot().getFrontCards()[0].getCardID());
        player.getBoard().getTempBox().moveToStrongBox();
        assert player.getBoard().getStrongBox().getResources().containsAll(player.getBoard().getSlot().getFrontCards()[0].producedResources());
        player.getProductionID();
        Player player1 = new ExtraProdPlayer(player, Resource.SHIELD, "PROD02");
        assert player1.getProductionID().contains("PROD01");
        assert player1.getProductionID().contains("PROD02");

    }

    //Non so perché non aumenta Coverage
    @Test
    public void TestAbstracPlayer() {
        DevCard toAdd = null;
        for (DevCard card : DefaultCreator.produceDevCard()) {
            if (card.getCardID().equals("devG101")) toAdd = card;
        }
        final Socket socket = mock(Socket.class);
        GameHolder games = new GameHolder();
        ArrayList<Player> players = new ArrayList<>();
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
            when(socket.getInputStream()).thenReturn(System.in);
            ClientHandler clientHandler = new ClientHandler(socket, games);
            Player original = new BasicPlayer("Tester1", clientHandler);
            players.add(original);
            Player player = new ChangeResPlayer(original, Resource.GOLD);
            player.getNickName();
            player.getVictoryPoints();
            player.getBoard();
            player.getGame();
            player.addLeaderCard(new ChangeResource(10, "BLUE","YELLOW", Resource.GOLD, "CNG01" ));
            player.addVictoryPoints(5);
            player.getLeaderCards();
            player.getBoard().getSlot().placeCard(toAdd, 0);
            player.produce("devG101");
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
            player.checkSwitch(0,1);
            player.notifyView(new ErrorUpdate("Error", "Tester1"));
            Player player1 = new DiscountedPlayer(player, Resource.GOLD);
            ArrayList<Resource> resources = new ArrayList<>();
            ArrayList<Resource> resources1 = new ArrayList<>();
            resources.add(Resource.GOLD);
            resources.add(Resource.FAITH);
            resources.add(Resource.STONE);
            resources1.add(Resource.GOLD);
            resources1.add(Resource.FAITH);
            resources1.add(Resource.STONE);
            player1.checkMarketRes(resources, resources1);
        }
        catch (IOException e) {System.out.println("IOException!");}
    }

}
