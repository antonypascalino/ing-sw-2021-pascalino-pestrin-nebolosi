package it.polimi.ingsw.model;

/**
 * The type Resource not avaible.
 */
public class ResourceNotAvaible extends Exception
{
    /**
     * Instantiates a new Resource not avaible.
     */
    public ResourceNotAvaible() { super("Risorse terminate"); }

    /**
     * Instantiates a new Resource not avaible.
     *
     * @param message the message
     */
    public ResourceNotAvaible(String message) { super(message); }

    /**
     * Instantiates a new Resource not avaible.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ResourceNotAvaible(String message, Throwable cause) { super(message, cause); }

    /**
     * Instantiates a new Resource not avaible.
     *
     * @param cause the cause
     */
    public ResourceNotAvaible(Throwable cause) { super(cause); }

}
