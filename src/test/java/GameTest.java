
    import com.google.gson.Gson;
    import com.google.gson.GsonBuilder;
    import com.google.gson.reflect.TypeToken;
    import it.polimi.ingsw.controller.DefaultCreator;
    import it.polimi.ingsw.model.Cards.DevCard;
    import it.polimi.ingsw.model.Resource;
    import org.junit.Test;

import java.lang.reflect.Array;
    import java.lang.reflect.Type;
    import java.util.ArrayList;

    public class GameTest
    {
        @Test
        public void TestTranslation()
        {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.setPrettyPrinting().create();
            String testDev=gson.toJson(DefaultCreator.produceDevCard());
            System.out.println(testDev);
            Type devListType = new TypeToken<ArrayList<DevCard>>(){}.getType();
            ArrayList<DevCard> tmp=gson.fromJson(testDev,devListType);
            for (DevCard dev : tmp)
            {
                System.out.println(dev.getCardId());
                System.out.println(dev.getPrice());
            }
            System.out.println("Riconvertite "+ tmp.size()+ " carte");
        }
    }
