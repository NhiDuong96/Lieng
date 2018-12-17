package handler.event;

import bitzero.server.core.IBZEventType;

/**
 * Created by niennd on 12/10/2015.
 */
public enum ZPSeaEventType implements IBZEventType {
    LEAVE_ROOM_REGISTERED,
    FORCED_LEAVE_ROOM,
    END_HAND,
    START_GAME,
    UPDATE_SAN_BONG_TICKET,
    UPDATE_SAN_BONG_CONSTRAIN,

    UPDATE_JACK_POT,
    SHARE_JACK_POT,
}
