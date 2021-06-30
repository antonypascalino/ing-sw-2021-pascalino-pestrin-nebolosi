package it.polimi.ingsw.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import it.polimi.ingsw.Convertible;

import java.lang.reflect.Type;

/**
 * Class used for deserialize any type of interface that extends the convertible interface
 * @param <T> The interface that needs to be deserialized
 */
class ConvertibleDeserializer<T extends Convertible> implements JsonDeserializer<T> {

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