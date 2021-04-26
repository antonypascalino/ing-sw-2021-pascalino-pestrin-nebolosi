import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.connection.JsonReader;
import it.polimi.ingsw.model.Cards.ExtraDeposit;
import it.polimi.ingsw.model.Cards.ExtraProd;
import it.polimi.ingsw.model.Cards.LeaderCard;
import it.polimi.ingsw.model.Resource;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestProvaJSon{

    @Test
    public void TestJsonLeader()
    {
        ArrayList<Resource> tmp = new ArrayList<Resource>();
        tmp.add(Resource.GOLD);
        tmp.add(Resource.FAITH);
        ExtraProd card = new ExtraProd(3, "Green", Resource.GOLD, "prod01") ;
        ExtraDeposit card2 = new ExtraDeposit(2, Resource.SERVANT, Resource.STONE, "extra01");
        ArrayList<LeaderCard> nonFuzionaMaSeFunziona = new ArrayList<LeaderCard>();
        nonFuzionaMaSeFunziona.add(card);
        nonFuzionaMaSeFunziona.add(card2);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        String jsonString = gson.toJson(nonFuzionaMaSeFunziona);
        System.out.println(jsonString);
        ArrayList<LeaderCard> risultato = JsonReader.readLeaderCard(jsonString);
        assertTrue(risultato.get(0).equals(nonFuzionaMaSeFunziona.get(0)));
    }


    @Test
    public void TestJsonReq() {
        //JsonReader.readRequest("Prova");
    }
}
