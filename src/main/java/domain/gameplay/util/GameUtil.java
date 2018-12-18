package domain.gameplay.util;


import bitzero.framework.ExtensionUtility;
import bitzero.server.BitZeroServer;
import bitzero.server.core.BZEvent;
import bitzero.server.core.BZEventParam;
import bitzero.server.entities.User;
import config.GameConfig;
import domain.gameplay.*;
import domain.gameplay.service.GameServiceImpl;
import domain.lobby.BlindLevel;
import domain.lobby.CashGameChannel;
import domain.lobby.format.AbstractGameFormat;
import domain.lobby.format.CashGameFormat;
import domain.lobby.option.PlayMode;
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

    /**
     * Determine the current state of the game
     *
     * @param game {@link Game} object to get the state of
     * @return {@link GameStatus} representing the current game state
     */
    public static GameStatus getGameStatus(CashGameImpl game) {
        if (!game.isStarted()) {
            return GameStatus.NOT_STARTED;
        }

        HandEntity hand = game.getCurrentHand();
        if (hand == null) {
            return GameStatus.SEATING;
        }

        if (hand.getCurrentToAct() == null) {
            return GameStatus.SHOW_DOWN;
        }
        return GameStatus.PRE_FLOP;
    }

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

    public static GameStructure buildGameStructure(AbstractGameFormat gameFormat, PlayMode playMode) {
        if (gameFormat instanceof CashGameFormat) {
            CashGameFormat cashGameFormat = (CashGameFormat) gameFormat;
            BlindLevel blindLevel = new BlindLevel(cashGameFormat.getBlindLevel().getAnte(), cashGameFormat.getBlindLevel().getSidepot());
            CashGameChannel cashGameChannel = GameConfig.MAP_CASH_GAME_CHANNEL.get(cashGameFormat.getChannelID());
            int playerTimeout = cashGameChannel == null ? GameConfig.TIMEOUT_PLAYER_ACTION_SEC : cashGameChannel.getFastPlayerTimeOut();
            return new GameStructure(cashGameFormat.getId(), GameType.CASH, blindLevel, cashGameFormat.getMaxPlayers(), gameFormat.getChannelID(), playerTimeout, playMode);
        } else {
            throw new IllegalArgumentException("No handle for " + gameFormat.getId());
        }
    }

}
