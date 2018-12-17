package handler.request;

import bitzero.server.entities.User;
import bitzero.server.extensions.BaseClientRequestHandler;
import bitzero.server.extensions.data.DataCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmd.game.request.GameRequestExtension;



/**
 * Created by pc1 on 11/2/2018.
 */
abstract class GameRequestHandler extends BaseClientRequestHandler {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public GameRequestHandler() {
        super();
    }

    @Override
    public void handleClientRequest(User user, DataCmd cmd) {
        try {
            switch (cmd.getId()) {

                default:
                    break;
            }
        } catch (Exception e) {
            logger.error(user.getId() + "_" + cmd.getId(), e);
        }
    }


}
