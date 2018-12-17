package handler.request;

import bitzero.framework.ExtensionUtility;
import bitzero.server.entities.User;
import bitzero.server.extensions.data.BaseMsg;
import domain.gameplay.Game;

/**
 * Created by pc1 on 12/17/2018.
 */
public class GameRequestHandlerImpl extends GameRequestHandler {
    public static void broadcastGame(Game game, BaseMsg msg) {
        if (game.getRoom() == null || !game.getRoom().isActive()) {

        } else {
            for (User u : game.getRoom().getUserList()) {
                if (u != null && u.isConnected()) {
                    ExtensionUtility.getExtension().send(msg, u);
                }
            }
            game.totalBroadcastMsg++;
            //Debug.info(game, "TOTAL broadcast ", game.totalBroadcastMsg);
        }
    }
}
