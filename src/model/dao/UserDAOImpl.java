package model.dao;

import bitzero.framework.dao.ModelDAOMembaseImpl;
import bitzero.framework.socialcontroller.bean.UserInfo;
import bitzero.framework.util.FrameworkUtils;
import bitzero.server.BitZeroServer;
import bitzero.server.entities.User;
import model.UProfileModel;

/**
 * Created by GSN on 10/29/2015.
 */
public class UserDAOImpl extends ModelDAOMembaseImpl {
    static UserDAOImpl instance = null;

    public static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    public UProfileModel getUProfile(int uID) {
        User user = BitZeroServer.getInstance().getUserManager().getUserById(uID);
        UProfileModel model = null;
        if (user != null) {
            if (user.containsProperty(UProfileModel.class)) {
                model = (UProfileModel) user.getProperty(UProfileModel.class);
            } else {
                model = (UProfileModel) getModel(uID, UProfileModel.class);
                if (model != null) {
                    user.setProperty(UProfileModel.class, model);
                }
            }
        } else {
            model = (UProfileModel) getModel(uID, UProfileModel.class);
        }
        if (model != null) {
            model.update();
        }
        return model;
    }

    public UProfileModel createUProfile(int uID, UserInfo userInfo) {
        UProfileModel uProfileModel = new UProfileModel(uID);
        uProfileModel.setUsername(userInfo.getUsername());
        uProfileModel.setAvatarURL(userInfo.getAvatar_url());
        uProfileModel.setDisplayname(userInfo.getDisplayname());
        uProfileModel.setLastLoginTime(FrameworkUtils.currentTimeInSecond());
        uProfileModel.save();
        return uProfileModel;
    }
}
