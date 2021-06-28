import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.model.Table.Market;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.model.Table.Table;
import it.polimi.ingsw.model.card.DevCard;
import org.junit.Test;

import java.util.ArrayList;

public class TableTest {

    @Test
    public void marketTest() {
        Market market = new Market(DefaultCreator.getGameRes());
        ArrayList<Resource> res = market.seeRow(0);
        assert res.equals(market.getRow(0));
        assert res.size() == 4;
        res = market.seeColumn(0);
        assert res.size() == 3;
        assert res.equals(market.getColumn(0));
        Resource resource = market.getFreeOne();
    }

    @Test
    public void resourceTest() {
        System.out.println(Resource.EMPTY.inLine());
        System.out.println(Resource.GOLD.inLine());
        System.out.println(Resource.FAITH.inLine());
        System.out.println(Resource.STONE.inLine());
        System.out.println(Resource.SHIELD.inLine());
        System.out.println(Resource.SERVANT.inLine());
    }

    @Test
    public void tableTest() {
        Table table = new Table(DefaultCreator.produceDevCard());
        table.getStack();
        table.getFrontIDs();
        DevCard card = table.seeDev("YELLOW", 1);
        assert card.getColor().equals("YELLOW");
        assert card.getLevel() == 1;
        assert table.getDevFromID("devY101").getCardID().equals("devY101");
        assert table.getDevFromID("Error") == null;
        table.buyDev("YELLOW", 1);
        table.buyDev("YELLOW", 1);
        table.buyDev("YELLOW", 1);
        table.buyDev("YELLOW", 1);
        assert table.seeDev("YELLOW", 1) == null;
        assert table.buyDev("YELLOW", 1) == null;




    }
}
