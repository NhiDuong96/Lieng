package domain.gameplay.util;


import bitzero.framework.ExtensionUtility;
import bitzero.server.BitZeroServer;
import bitzero.server.core.BZEvent;
import bitzero.server.core.BZEventParam;
import bitzero.server.entities.User;
import domain.gameplay.CashGameImpl;
import domain.gameplay.Game;
import domain.gameplay.GameStatus;
import domain.gameplay.Player;
import domain.gameplay.service.GameServiceImpl;
import handler.event.LeaveGameReason;
import handler.event.ZPSeaEventParam;
import handler.event.ZPSeaEventType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Static helper methods for controlling game functions and information
 */
public class GameUtil {


    public static void forcedLeaveGame(Player p, LeaveGameReason reason) {
        try {
            User u = BitZeroServer.getInstance().getUserManager().getUserById(p.getId());
            Game game = p.getGame();
            if (u != null) {
                if (game.getRoom().containsUser(u)) {
//                    LogicLogger.getInstance().info("force leave game", p, reason);
                    ExtensionUtility.getExtension().getApi().leaveRoom(u, game.getRoom(), false, false);
                }
                Map evtParams = new HashMap();
                evtParams.put(BZEventParam.ZONE, u.getZone());
                evtParams.put(BZEventParam.ROOM, game.getRoom());
                evtParams.put(BZEventParam.USER, u);
                evtParams.put(BZEventParam.PLAYER_ID, p.getId());
                evtParams.put(ZPSeaEventParam.FORCE_LEAVE_REASON, reason);
                //fire event here

                if (reason == LeaveGameReason.LEAVING_REGISTERED) {
                    BitZeroServer.getInstance().getEventManager().dispatchEvent(new BZEvent(ZPSeaEventType.LEAVE_ROOM_REGISTERED, evtParams));
                } else {
                    BitZeroServer.getInstance().getEventManager().dispatchEvent(new BZEvent(ZPSeaEventType.FORCED_LEAVE_ROOM, evtParams));
                }
            } else {
                GameServiceImpl.getInstance().quitGame(p, reason, "force leave game");
            }
        } catch (Exception e) {
//            LogicLogger.getInstance().getLogger().error(p + "_" + reason, e);
        }
    }

//    public static void fireStartGameEvent(GameStatus gs, CashGameImpl game) {
//        Map evtParams = new HashMap();
//        evtParams.put(ZPSeaEventParam.GAME_STATUS, gs);
//        evtParams.put(ZPSeaEventParam.GAME_STRUCTURE, game.getGameStructure().clone());
//        evtParams.put(ZPSeaEventParam.NUM_PLAYER, Integer.valueOf(game.getPlayers().size()));
//        evtParams.put(ZPSeaEventParam.SET_PLAYER_ID, getSetPlayerID(game.getPlayers()));
//        evtParams.put(ZPSeaEventParam.HAND_ENTITY, game.getCurrentHand());
//        BitZeroServer.getInstance().getEventManager().dispatchEvent(new BZEvent(ZPSeaEventType.START_GAME, evtParams));
//    }
//
//    private static Set<Integer> getSetPlayerID(Set<Player> players) {
//        Set<Player> playersClone = new HashSet<>();
//        playersClone.addAll(players);
//        Set<Integer> ids = new HashSet<>();
//        for (Player p : playersClone) {
//            if (!p.isBot()) {
//                ids.add(p.getId());
//            }
//        }
//        return ids;
//    }

}
