package it.polimi.ingsw.connection;

import com.google.gson.*;
import it.polimi.ingsw.model.Cards.DevCard;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

public class JsonReader{
    /**
     * Static metod used for reading a sequence of devCard from a json file
     * @param input the string containing the input information for deserialize
     * @return an array list with the deserialized devCards
     */
    public static ArrayList<DevCard> readDevCard(String input)
    {
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ArrayList<Resource> tmp = new ArrayList<Resource>();
        tmp.add(Resource.GOLD);
        tmp.add(Resource.FAITH);
        DevCard dev = new DevCard("Green", 2, 2, tmp, tmp, tmp) ;

        String jsonString = gson.toJson(dev);
        System.out.println(jsonString);

        DevCard empObject = gson.fromJson(jsonString, DevCard.class);
        return new ArrayList<DevCard>();
    }
}
