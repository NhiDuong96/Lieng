package cmd.test.response;

import bitzero.framework.ExtensionUtility;
import bitzero.server.entities.User;

import cmd.test.template.UserInfoApi;


/**
 * Created by Minh Nhi.
 */
public class TestResponseExtension {

	public static void sendUserInfo(UserInfoApi mUserInfoApi, User user){
        ExtensionUtility.getExtension().send(new ResponseUserInfo(mUserInfoApi), user);
    }

}

