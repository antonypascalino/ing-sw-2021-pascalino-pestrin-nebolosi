package it.polimi.ingsw.connection;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Convertable;
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
    public static ArrayList<LeaderCard> readLeaderCard(String input)
    {
        //Gson gson = new Gson();
        GsonBuilder builder = new GsonBuilder();
        //Register a deseralizer made for this LeaderCardInterface
        builder.registerTypeAdapter(LeaderCard.class, new ConvertableDeserializer<LeaderCard>());
        Gson gson = builder.setPrettyPrinting().create();

        //Token made for deserializing a whole arrayList
        Type listType = new TypeToken<ArrayList<LeaderCard>>(){}.getType();

        ArrayList<LeaderCard> result = gson.fromJson(input, listType );
        return result;
    }

    /**
     * Static metod used for reading a sequence of requests from a json file
     * @param input the string containing the input information for deserialize
     * @return an array list with the deserialized requests
     */
    public static ArrayList<Request> readRequest(String input)
    {
        //Gson gson = new Gson();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Request.class, new ConvertableDeserializer<Request>());
        Gson gson = builder.setPrettyPrinting().create();



        Type listType = new TypeToken<ArrayList<Request>>(){}.getType();
        ArrayList<Request> empObject = gson.fromJson(input, listType );
        return empObject;
    }
}
