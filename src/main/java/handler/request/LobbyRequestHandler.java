package handler.request;

import bitzero.server.entities.User;
import bitzero.server.extensions.BaseClientRequestHandler;
import bitzero.server.extensions.data.DataCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmd.lobby.request.LobbyRequestExtension;

import cmd.lobby.template.PlayCashGame;


/**
 * Created by pc1 on 11/2/2018.
 */
abstract class LobbyRequestHandler extends BaseClientRequestHandler {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public LobbyRequestHandler() {
        super();
    }

    @Override
    public void handleClientRequest(User user, DataCmd cmd) {
        try {
            switch (cmd.getId()) {
                case 1100:
                    onHandleRequestUserInfo(user, cmd);
                    break;
                case 1200:
                    onHandleRequestPlayCashGame(user, cmd, LobbyRequestExtension.getPlayCashGame(cmd));
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            logger.error(user.getId() + "_" + cmd.getId(), e);
        }
    }

    protected abstract void onHandleRequestUserInfo(User user, DataCmd cmd);
    protected abstract void onHandleRequestPlayCashGame(User user, DataCmd cmd, PlayCashGame playCashGame);

}
