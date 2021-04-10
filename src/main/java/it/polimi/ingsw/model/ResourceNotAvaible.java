package it.polimi.ingsw.model;

public class ResourceNotAvaible extends Exception
{
        public ResourceNotAvaible() { super("Risorse terminate"); }
        public ResourceNotAvaible(String message) { super(message); }
        public ResourceNotAvaible(String message, Throwable cause) { super(message, cause); }
        public ResourceNotAvaible(Throwable cause) { super(cause); }

}
