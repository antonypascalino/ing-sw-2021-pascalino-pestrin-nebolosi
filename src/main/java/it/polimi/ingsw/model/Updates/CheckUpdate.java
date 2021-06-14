package it.polimi.ingsw.model.Updates;


import it.polimi.ingsw.Request.PongRequest;
import it.polimi.ingsw.view.data.PlayerData;

/**
 * Update used for checking the status of the client
 */
public class CheckUpdate implements Update{
    private final String className;
    private String player= "Boh";
    public CheckUpdate()
    {
        this.className = this.getClass().getName();
    }

    @Override
    public void handleUpdate(PlayerData data)
    {
        System.out.println("Sono ancora connesso");
        data.sendRequest(new PongRequest());
    }

    @Override
    public String getClassName() {
        return className;
    }
}
