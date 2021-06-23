import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.model.Table.Market;
import it.polimi.ingsw.model.Table.Resource;
import org.junit.Test;

import java.util.ArrayList;

public class TableTest {

    @Test
    public void marketTest() {
        Market market = new Market(DefaultCreator.getGameRes());
        ArrayList<Resource> res = market.seeRow(0);
        assert res.size() == 3;
        market.seeColumn(0);
    }
}
