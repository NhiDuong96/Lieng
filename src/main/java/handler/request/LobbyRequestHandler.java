package handler.request;

import bitzero.server.core.IBZEvent;
import bitzero.server.entities.User;
import bitzero.server.extensions.BaseClientRequestHandler;
import bitzero.server.extensions.data.DataCmd;
import constant.CmdDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LobbyRequestHandler extends BaseClientRequestHandler {
    Logger logger = LoggerFactory.getLogger(getClass());

    public LobbyRequestHandler() {
        super();
    }

    @Override
    public void handleClientRequest(User user, DataCmd cmd) {
        try {
            switch (cmd.getId()) {
                case CmdDefine.GET_USER_INFO:
                    getUserInfo(user, cmd);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            logger.error(user.getId() + "_" + cmd.getId(), e);
        }
    }

    private void getUserInfo(User user, DataCmd cmd) {
    }

    public void handleServerEvent(IBZEvent ibzevent) throws Exception {
        super.handleServerEvent(ibzevent);
    }
}
