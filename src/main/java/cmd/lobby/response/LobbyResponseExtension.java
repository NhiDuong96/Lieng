package cmd.lobby.response;

import bitzero.framework.ExtensionUtility;
import bitzero.server.entities.User;

import cmd.lobby.template.UserInfoApi;


/**
 * Created by Minh Nhi.
 */
public class LobbyResponseExtension {

	public static void sendUserInfo(UserInfoApi mUserInfoApi, User user){
        ExtensionUtility.getExtension().send(new ResponseUserInfo(mUserInfoApi), user);
    }

}

