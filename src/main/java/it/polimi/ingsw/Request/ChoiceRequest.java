package it.polimi.ingsw.Request;

import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Resource;

import java.util.ArrayList;

public class ChoiceRequest implements Request{

    ArrayList<Resource> choices;

    @Override
    public void handle(Player player) {
        for (Resource c : choices) {
            player.getBoard().getStrongBox().addResource(c);
        }
    }

    @Override
    public boolean validRequest(ArrayList<TurnState> turnStates) {
        return true;
    }

    @Override
    public boolean canBePlayed(Player player) {
        if(choices.size() != player.getBoard().getTempBox().filterChoices().size()){
            //lancia eccezione "choices and resources selected do not match"
            return false;
        }
        if(choices.contains(Resource.FAITH)){
            //lancia eccezione "you can't convert choices into faith points"
            return false;
        }

        return true;
    }

    @Override
    public TurnState nextTurnState() {
        return TurnState.CONVERT_CHOICE;
    }

    @Override
    public int getMyFPSteps() {
        return 0;
    }

    @Override
    public int getDiscardedSteps() {
        return 0;
    }

    @Override
    public String getClassName() {
        return "ChoiceRequest";
    }
}
