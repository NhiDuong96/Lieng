import bitzero.engine.sessions.ISession;
import bitzero.framework.ExtensionUtility;
import bitzero.framework.authen.dev.DevAuthService;
import bitzero.framework.socialcontroller.bean.UserInfo;
import bitzero.server.core.BZEventType;
import bitzero.server.entities.data.ISFSObject;
import bitzero.server.entities.managers.ConnectionStats;
import bitzero.server.exceptions.BZException;
import bitzero.server.extensions.BZExtension;
import bitzero.server.extensions.ExtensionLogLevel;
import bitzero.server.extensions.data.DataCmd;
import cmd.authen.RequestLogin;
import constant.CmdDefine;
import constant.ServerConstant;
import extras.webserver.RestServer;
import handler.event.DisconnectEventHandler;
import handler.event.LoginEventHandler;
import handler.event.LogoutEventHandler;
import handler.event.PaymentUpdateEventHandler;
import handler.request.LobbyRequestHandler;


public class Extension extends BZExtension {
    public Extension() {
        super();
        setName(ServerConstant.GAME_ID);
    }

    @Override
    public void init() {
        trace(ExtensionLogLevel.INFO, "Request handler register");
        addRequestHandler(CmdDefine.LOBBY_MULTI_IDS, LobbyRequestHandler.class);

        trace(ExtensionLogLevel.INFO, "Event handler register");
        addEventHandler(BZEventType.USER_LOGIN, LoginEventHandler.class);
        addEventHandler(BZEventType.USER_LOGOUT, LogoutEventHandler.class);
        addEventHandler(BZEventType.USER_DISCONNECT, DisconnectEventHandler.class);
        addEventHandler(BZEventType.PAYMENT_UPDATE, PaymentUpdateEventHandler.class);

        // init others service
        RestServer.getInstance().onExtensionInit();
    }

    @Override
    public void destroy() {
        super.destroy();
        RestServer.getInstance().onExtensionDestroy();
        trace(ExtensionLogLevel.WARN, "Server is shutting down. Kick all users  ... ");
    }

    @Override
    public void doLogin(short cmdId, ISession session, DataCmd objData) throws BZException {
        RequestLogin req = new RequestLogin(objData);
        req.unpackData();

        UserInfo uInfo = DevAuthService.getInstance().authenticate(req.data);
        ExtensionUtility.instance().canLogin(uInfo, session);
    }


    public void doLogin(ISession session, ISFSObject reqObj) throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(Integer.toString(1));
        String userName = reqObj.getUtfString("un");
        userInfo.setUsername(userName);
        ExtensionUtility.instance().canLogin(userInfo, session);
    }

    @Override
    public void monitor() {
        ConnectionStats connStats = bz.getStatsManager().getUserStats();
    }
}
