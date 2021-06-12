package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Player.Player;

/**
 * The list of all the possible actions a {@link Player} can do during his turn.
 */
public enum TurnState {
    LOBBY,
    CHECK_STATS,
    PLAY_LEADER_CARD,
    GET_FROM_MARKET,
    BUY_DEV_CARD,
    PRODUCE,
    MOVE_RESOURCE,
    DISCARD_LEADER_CARD,
    END_TURN,
}
