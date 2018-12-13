package handler.request;

import bitzero.server.entities.User;
import cmd.test.response.LobbyResponseExtension;
import cmd.test.template.UserInfoApi;
import domain.lobby.RoomStatusObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc1 on 12/12/2018.
 */
public class LobbyRequestHandlerImpl extends LobbyRequestHandler {
    @Override
    protected void onHandleRequestUserInfo(User user) {
        LobbyResponseExtension.sendUserInfo(new UserInfoApi() {
            @Override
            public int getUID() {
                return 0;
            }

            @Override
            public String getUserName() {
                return "aaa";
            }

            @Override
            public String getDisplayName() {
                return "bbb";
            }

            @Override
            public int getLevel() {
                return 0;
            }

            @Override
            public long getGold() {
                return 100;
            }

            @Override
            public long getExp() {
                return 10;
            }

            @Override
            public String getAvatarURL() {
                return "asdas";
            }

            @Override
            public String getDefaultAvatar() {
                return "as";
            }

            @Override
            public boolean getHasGame() {
                return false;
            }

        }, user);
    }
}
