package handler.event;


import bitzero.framework.ExtensionUtility;
import bitzero.framework.socialcontroller.bean.UserInfo;
import bitzero.framework.util.FrameworkUtils;
import bitzero.server.core.BZEventParam;
import bitzero.server.core.IBZEvent;
import bitzero.server.entities.User;
import bitzero.server.exceptions.BZException;
import bitzero.server.extensions.BaseServerEventHandler;
import model.UProfileModel;
import model.dao.UserDAOImpl;

public class LoginEventHandler extends BaseServerEventHandler {
    public LoginEventHandler() {
        super();
    }

    @Override
    public void handleServerEvent(IBZEvent ibzevent) throws BZException {
        User user = (User) ibzevent.getParameter(BZEventParam.USER);
        UserInfo userInfo = (UserInfo) user.getProperty(UserInfo.class);

        if(userInfo.isAdmin()){
            ExtensionUtility.instance().sendLoginOK(user);
            return;
        }

        UProfileModel uProfileModel = UserDAOImpl.getInstance().getUProfile(user.getId());
        if(uProfileModel == null){
            //create new profile for user
            UserDAOImpl.getInstance().createUProfile(user.getId(), userInfo);
        }
        else{
            //update profile for user
            //updateNewSocialData(uProfileModel, userInfo);
            uProfileModel.setLastLoginTime(FrameworkUtils.currentTimeInSecond());
            uProfileModel.save();
        }

        ExtensionUtility.instance().sendLoginOK(user);
    }
}
