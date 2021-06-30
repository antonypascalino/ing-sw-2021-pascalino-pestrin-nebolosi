package it.polimi.ingsw.server;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.controller.Request.Request;
import it.polimi.ingsw.Updates.Update;
import it.polimi.ingsw.model.card.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * An auxiliary class used for deserializing leaderCards and requests
 */
public class JsonReader{
    /**
     * Static method used for reading a sequence of devCard from a json file
     * @param input the string containing the input information for deserialize
     * @return an array list with the deserialized devCards
     */
    public static ArrayList<LeaderCard> readLeaderCard(String input)
    {
        //Gson gson = new Gson();
        GsonBuilder builder = new GsonBuilder();
        //Register a deserializer made for this LeaderCardInterface
        builder.registerTypeAdapter(LeaderCard.class, new ConvertibleDeserializer<LeaderCard>());
        Gson gson = builder.setPrettyPrinting().create();

        //Token made for deserializing a whole arrayList
        Type listType = new TypeToken<ArrayList<LeaderCard>>(){}.getType();

        ArrayList<LeaderCard> result = gson.fromJson(input, listType );
        return result;
    }

    /**
     * Static method used for reading a sequence of requests from a json file
     * @param input the string containing the input information for deserialize
     * @return an array list with the deserialized requests
     */
    public static ArrayList<Request> readRequest(String input)
    {
        //Gson gson = new Gson();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Request.class, new ConvertibleDeserializer<Request>());
        Gson gson = builder.setPrettyPrinting().create();



        Type listType = new TypeToken<ArrayList<Request>>(){}.getType();
        ArrayList<Request> empObject = gson.fromJson(input, listType );
        return empObject;
    }

    /**
     * Static method used for reading a requests from a json file
     * @param input the string containing the input information for deserialize
     * @return an array list with the deserialized request
     */
    public static Request readSingleRequest(String input) throws JsonSyntaxException
    {
        //Gson gson = new Gson();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Request.class, new ConvertibleDeserializer<Request>());
        Gson gson = builder.setPrettyPrinting().create();
        Request empObject = gson.fromJson(input, Request.class );
        return empObject;
    }

    public static Update readUpdate(String input) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Update.class, new ConvertibleDeserializer<Update>());
        Gson gson = builder.create();
        Update empObject = gson.fromJson(input, Update.class );
        return empObject;

    }
}
