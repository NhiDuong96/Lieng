package model.dao;

import bitzero.framework.dao.ModelDAOMembaseImpl;
import bitzero.framework.dao.ServerDAOMembaseImpl;
import bitzero.framework.socialcontroller.bean.UserInfo;
import bitzero.framework.util.FrameworkUtils;
import bitzero.server.BitZeroServer;
import bitzero.server.entities.User;
import config.GameConfig;
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
        uProfileModel.setGold(GameConfig.INIT_USER_DATA_GOLD);
        uProfileModel.setCoinPromo(GameConfig.INIT_USER_DATA_XU);
        uProfileModel.setUsername(userInfo.getUsername());
        uProfileModel.setAvatarURL(userInfo.getAvatar_url());
//        if (userInfo.getSocial().compareTo(GameConstant.Z_ACC) == 0 || userInfo.getAvatar_url().isEmpty()) {
//            String randomAvatar = MyRandom.takeRandom(GameConfig.DEFAULT_AVATAR_SET);
//            uProfileModel.setDefaultAvatar(randomAvatar);
//        } else {
//            uProfileModel.setDefaultAvatar(userInfo.getAvatar_url());
//        }
        uProfileModel.setDisplayname(userInfo.getDisplayname());
        uProfileModel.setLastLoginTime(FrameworkUtils.currentTimeInSecond());
//        uProfileModel.setDataVersion(dVersion_uProfile);
        uProfileModel.save();
//            if (GameConfig.INIT_USER_DATA_XU > 0) {
//                ItemHandler.promoCoin(user.getId(), GameConfig.INIT_USER_DATA_XU, "", BillingPromoCampaign.USER_COMPENSATION, "New Account", GameConstant.DEFAULT_ADMIN_ID);
//            }
        // map username -> uid
//        ServerDAOMembaseImpl.getInstance().set(GameConstant.PREFIX_USERNAME_TO_UID + uProfileModel.getUsername(), uID);
//        UserInfoLogger.getInstance().info("createUProfile", uID, userInfo.getUsername());
        return uProfileModel;
    }

}
