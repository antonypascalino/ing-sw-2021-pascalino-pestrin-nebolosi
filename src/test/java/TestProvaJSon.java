import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.connection.JsonReader;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.model.card.DevCard;
import it.polimi.ingsw.model.card.ExtraDeposit;
import it.polimi.ingsw.model.card.ExtraProd;
import it.polimi.ingsw.model.card.LeaderCard;
import it.polimi.ingsw.model.Table.Resource;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestProvaJSon{

    @Test
    //Tries to serialize amd deserialize a couple of leadr card
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
        jsonString = gson.toJson(DefaultCreator.produceLeaderCard());
        System.out.println(jsonString);
        ArrayList<LeaderCard> risultato = JsonReader.readLeaderCard(jsonString);
        assertTrue(risultato.get(0).equals(DefaultCreator.produceLeaderCard().get(0)));
    }

    @Test
    //Tries to deserialize and deserialize all the DevCards
    public void TestDevTranslation()
    {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        String testDev=gson.toJson(DefaultCreator.produceDevCard());
        System.out.println(testDev);
        Type devListType = new TypeToken<ArrayList<DevCard>>(){}.getType();
        ArrayList<DevCard> tmp=gson.fromJson(testDev,devListType);
        for (DevCard dev : tmp)
        {
            System.out.println(dev.getCardID());
            System.out.println(dev.getPrice());
        }
        System.out.println("Riconvertite "+ tmp.size()+ " carte");
    }

    @Test
    //Tries to deserialize and deserialize all the leaderCards
    public void TestLeaderTranslation()
    {
        GsonBuilder builder = new GsonBuilder();

        Gson gson = builder.setPrettyPrinting().create();
        String testLeader=gson.toJson(DefaultCreator.produceLeaderCard());
        System.out.println(testLeader);

        ArrayList<LeaderCard> tmp= JsonReader.readLeaderCard(testLeader);
        for (LeaderCard leaderCard : tmp)
        {
            System.out.println(leaderCard.getID());
            System.out.println(leaderCard.toString());
        }
        System.out.println("Riconvertite "+ tmp.size()+ " carte");
    }
}
