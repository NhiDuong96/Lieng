package handler.request;

import bitzero.framework.common.MsgService;
import bitzero.server.entities.User;
import bitzero.server.extensions.data.DataCmd;
import cmd.lobby.response.LobbyResponseExtension;
import cmd.lobby.template.PlayCashGame;
import cmd.lobby.template.UserInfoApi;
import constant.ErrorDefine;
import domain.MaintainServiceImpl;
import domain.gameplay.service.PlayerActionServiceImpl;
import domain.lobby.LobbyServiceImpl;
import model.UProfileModel;
import model.dao.UserDAOImpl;

/**
 * Created by pc1 on 12/12/2018.
 */
public class LobbyRequestHandlerImpl extends LobbyRequestHandler {
    @Override
    protected void onHandleRequestUserInfo(User user, DataCmd cmd) {
        UProfileModel uProfileModel = UserDAOImpl.getInstance().getUProfile(user.getId());
        LobbyResponseExtension.sendUserInfo(new UserInfoApi() {
            @Override
            public UProfileModel getUserProfile() {
                return uProfileModel;
            }

            @Override
            public boolean getHasGame() {
                return false;
            }
        }, user);
    }

    @Override
    protected void onHandleRequestPlayCashGame(User user, DataCmd cmd, PlayCashGame req) {
        if (MaintainServiceImpl.getInstance().isUnderMaintain()) {
            MsgService.responseCommon(user, cmd.getId(), ErrorDefine.SERVER_UNDER_MAINTAIN);
            return;
        }

        if (user.isEnteringGame()) {
            MsgService.responseCommon(user, cmd.getId(), ErrorDefine.ENTERING_GAME);
            return;
        }

        if (user.getJoinedRoom() != null || PlayerActionServiceImpl.getInstance().getPlayerByUser(user) != null) {
            MsgService.responseCommon(user, cmd.getId(), ErrorDefine.ALREADY_IN_GAME);
            return;
        }

        user.setEnteringGame(true);
        byte errorCode = ErrorDefine.FAIL;
        try {
            errorCode = LobbyServiceImpl.getInstance().playCashGame(user);
        } catch (Exception e) {
            logger.error(req.getRoomName() + ":" + req.getStructureID(), e);
        } finally {
            user.setEnteringGame(false);
        }

        if (errorCode != ErrorDefine.SUCCESS) {
            MsgService.responseCommon(user, cmd.getId(), errorCode);
        }
    }

}
