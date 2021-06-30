package it.polimi.ingsw.view.selections;

import it.polimi.ingsw.controller.Request.MoveRequest;
import it.polimi.ingsw.controller.Request.Request;
import it.polimi.ingsw.model.Table.Resource;
import it.polimi.ingsw.view.data.PlayerData;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The {@link Selection} that guides the player when he chose tu sort his deposits.
 */
public class MoveSelection extends Selection {

    public void handleSelection(PlayerData data) {
        ArrayList<Integer> l = new ArrayList<>();
        ArrayList<Resource[]> levels = data.getDeposits();
        for (int i = 0; i < levels.size(); i++) {
            if (Arrays.stream(levels.get(i)).anyMatch(x -> !x.equals(Resource.EMPTY))) {
                l.add(i);
            }
        }
        if (l.isEmpty()) {
            data.getPrinter().printMessage("You can't switch any level");
            data.getMenu().menuMaker();
            return;
        }
        data.getPrinter().printMessage("Select the origin level: ");
        int origin = data.getPrinter().printIntegers(l, false, false);
        data.getPrinter().printMessage("Select the destination level: ");
        int destination = data.switchLevels(origin);
        if (destination == -1) {
            data.getMenu().menuMaker();
            return;
        }

        Request moveReq = new MoveRequest(data.getPlayerID(), data.getGameID(), origin, destination);
        data.sendRequest(moveReq);
    }
}
