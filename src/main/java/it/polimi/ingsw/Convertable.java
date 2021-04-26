package it.polimi.ingsw;

//Class used for converting cards from json
public interface Convertable {
    /**
     * Helper method used for deserializing a gson String containing a list of leaderCards
     * @return the class name
     */
    String getClassName();
}