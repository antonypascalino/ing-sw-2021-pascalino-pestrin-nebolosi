package it.polimi.ingsw.connection;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Resource;

import java.lang.reflect.Type;
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
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Convertable.class, new ConvertableDeserializer<Convertable>());
        Gson gson = builder.setPrettyPrinting().create();

        ArrayList<Resource> tmp = new ArrayList<Resource>();
        tmp.add(Resource.GOLD);
        tmp.add(Resource.FAITH);
        ExtraProd card = new ExtraProd(3, "Green", Resource.GOLD) ;
        ExtraDeposit card2 = new ExtraDeposit(2, Resource.SERVANT, Resource.STONE);
        ArrayList<LeaderCard> nonFuzionaMaSeFunziona = new ArrayList<LeaderCard>();
        nonFuzionaMaSeFunziona.add(card);
        nonFuzionaMaSeFunziona.add(card2);

        String jsonString = gson.toJson(nonFuzionaMaSeFunziona);
        System.out.println(jsonString);
        Type listType = new TypeToken<ArrayList<Convertable>>(){}.getType();

        ArrayList<Convertable> empObject = gson.fromJson(jsonString, listType );
        return new ArrayList<DevCard>();
    }
}
