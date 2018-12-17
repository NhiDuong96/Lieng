package handler.event;

import bitzero.server.core.IBZEventParam;

/**
 * Created by niennd on 1/20/2016.
 */
public enum ZPSeaEventParam implements IBZEventParam {
    FORCE_LEAVE_REASON,
    GAME_STATUS,
    WINNERS,
    HAND_ENTITY,
    GAME_STRUCTURE,
    NUM_PLAYER,
    SET_PLAYER_ID
}
