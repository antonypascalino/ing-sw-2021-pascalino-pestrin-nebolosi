import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.client.LineClient;
import it.polimi.ingsw.controller.DefaultCreator;
import it.polimi.ingsw.model.Cards.DevCard;
import it.polimi.ingsw.model.Cards.LeaderCard;
import it.polimi.ingsw.model.Resource;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConnectionTest {

    @Test
    public void NewClientWithMessage()
    {
        //Here i need two threads : one that execute the view commands and one for communication
        LineClient client = new LineClient("127.0.0.1", 8080);
        try {
            client.startClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String serverResponse = "test";
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ArrayList<DevCard> test = DefaultCreator.produceDevCard();
        String jsonDev = gson.toJson(test);
        System.out.println(jsonDev);
        try {
             serverResponse = client.sendMessage(jsonDev);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(serverResponse,jsonDev);
    }


    @Test
    public void NewClientLeader()
    {
        //Here i need two threads : one that execute the view commands and one for communication
        LineClient client = new LineClient("127.0.0.1", 8080);
        try {
            client.startClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String serverResponse = "test";
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ArrayList<LeaderCard> test = DefaultCreator.produceLeaderCard();
        String jsonDev = gson.toJson(test);
        System.out.println(jsonDev);
        try {
            serverResponse = client.sendMessage(jsonDev);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(serverResponse,jsonDev);
    }


}
