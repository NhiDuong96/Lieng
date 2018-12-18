package handler.request;

import bitzero.framework.ExtensionUtility;
import bitzero.framework.common.MsgService;
import bitzero.server.entities.Room;
import bitzero.server.entities.User;
import bitzero.server.extensions.data.BaseMsg;
import bitzero.server.extensions.data.DataCmd;
import cmd.game.response.GameResponseExtension;
import cmd.game.template.GameAction;
import cmd.game.template.GameActionApi;
import constant.ErrorDefine;
import domain.gameplay.*;
import domain.gameplay.service.PlayerActionServiceImpl;
import domain.lobby.LobbyServiceImpl;

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
        }
    }

    public static void sendPlayerAction(Game game, PlayerAction actionID, Player player, long chips){
        System.out.println("response player action");
        GameRequestHandlerImpl.broadcastGame(game, GameResponseExtension.getGameAction(new GameActionApi() {
            @Override
            public byte getActionID() {
                return actionID.getCode();
            }

            @Override
            public byte getCurPlayerPos() {
                return (byte) player.getGamePosition();
            }

            @Override
            public long getChips() {
                return chips;
            }
        }));
    }

    @Override
    protected void onHandleRequestGameAction(User user, DataCmd cmd, GameAction req) {
        Room room = user.getJoinedRoom();
        CashGameImpl game = (CashGameImpl) LobbyServiceImpl.getInstance().getGameByRoom(room);
        if (game == null) {
            throw new IllegalStateException("Game not existed");
        }

        Player player = PlayerActionServiceImpl.getInstance().getPlayerByUser(user);

        if (player == null) {
            MsgService.responseCommon(user, cmd.getId(), ErrorDefine.PLAYER_ACTION_INVALID);
            return;
        }

        HandEntity curHand = game.getCurrentHand();

        boolean success = false;

        try {
            if (req.getActionID() == PlayerAction.BET.getCode()) {
                success = PlayerActionServiceImpl.getInstance().bet(player, curHand, req.getChips());
            } else if (req.getActionID() == PlayerAction.CALL.getCode()) {
                success = PlayerActionServiceImpl.getInstance().call(player, curHand);
            } else if (req.getActionID() == PlayerAction.CHECK.getCode()) {
                success = PlayerActionServiceImpl.getInstance().check(player, curHand);
            } else if (req.getActionID() == PlayerAction.FOLD.getCode()) {
                success = PlayerActionServiceImpl.getInstance().fold(player, curHand);
            }else if (req.getActionID() == PlayerAction.ACTIVE.getCode()) {
                return;
            } else {
                MsgService.responseCommon(user, cmd.getId(), ErrorDefine.PLAYER_ACTION_INVALID);
//                LogicLogger.getInstance().trace("ERROR", "Request player action invalid " + req.actionID);
            }
        } catch (Exception e) {
            success = false;
        }

        if (!success) {
            MsgService.responseCommon(user, cmd.getId(), ErrorDefine.PLAYER_ACTION_FAIL);
//            LogicLogger.getInstance().trace("ERROR", "Request player action FAIL" + req.actionID);
        }
    }
}
