package it.polimi.ingsw.connection;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import it.polimi.ingsw.Convertable;
import it.polimi.ingsw.model.Cards.LeaderCard;

import java.lang.reflect.Type;

/**
 * Classe used for deserialize any type of interface that extends the convertable interface
 * @param <T> The interface that needs to be deserialized
 */
class ConvertableDeserializer<T extends Convertable> implements JsonDeserializer<T> {

    private static final String CLASSNAME = "className";

    public T deserialize(final JsonElement jsonElement, final Type type, final JsonDeserializationContext deserializationContext) throws JsonParseException
    {
        final JsonObject jsonObject = jsonElement.getAsJsonObject();
        final JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        final String className = prim.getAsString();
        final Class<T> clazz = getClassInstance(className);
        return deserializationContext.deserialize(jsonObject, clazz);
    }

    @SuppressWarnings("unchecked")
    public Class<T> getClassInstance(String className) {
        try {
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException(cnfe.getMessage());
        }
    }

}