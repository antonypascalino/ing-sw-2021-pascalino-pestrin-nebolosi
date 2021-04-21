package it.polimi.ingsw.model;

/**
 * The type Resource not avaible.
 */
public class ResourceNotAvailable extends Exception
{
    /**
     * Instantiates a new Resource not avaible.
     */
    public ResourceNotAvailable() { super("Risorse terminate"); }

    /**
     * Instantiates a new Resource not avaible.
     *
     * @param message the message
     */
    public ResourceNotAvailable(String message) { super(message); }

    /**
     * Instantiates a new Resource not avaible.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ResourceNotAvailable(String message, Throwable cause) { super(message, cause); }

    /**
     * Instantiates a new Resource not avaible.
     *
     * @param cause the cause
     */
    public ResourceNotAvailable(Throwable cause) { super(cause); }

}
