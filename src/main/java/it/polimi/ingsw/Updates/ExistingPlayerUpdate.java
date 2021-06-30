package it.polimi.ingsw.Updates;

import it.polimi.ingsw.controller.Request.NewGameRequest;
import it.polimi.ingsw.controller.Request.Request;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.Scanner;

public class ExistingPlayerUpdate implements Update {

    public final String className;

    public ExistingPlayerUpdate()
    {
        this.className= this.getClass().getName();
    }
    @Override
    public void handleUpdate(PlayerData data) {
        Scanner scanner = new Scanner(System.in);
        data.getPrinter().printMessage("Name already in use\n\rChoose a new one");
        String nickname = scanner.nextLine();
        System.out.println("We're gonna try to find an existing game to add you\n\rin case there are none, how many players do you want in your game?");
        int maxPlayers = scanner.nextInt();
        Request request = new NewGameRequest(nickname, maxPlayers);
        data.setNickname(nickname);
        data.sendRequest(request);
    }

    @Override
    public String getClassName() {
        return null;
    }
}
