package handler.event;


import bitzero.server.core.BZEventParam;
import bitzero.server.core.IBZEvent;
import bitzero.server.entities.User;
import bitzero.server.exceptions.BZException;
import bitzero.server.extensions.BaseServerEventHandler;

public class DisconnectEventHandler extends BaseServerEventHandler {
    public DisconnectEventHandler() {
        super();
    }

    @Override
    public void handleServerEvent(IBZEvent ibzevent) throws BZException {
        User user = (User) ibzevent.getParameter(BZEventParam.USER);

    }
}
