package handler.request;

import bitzero.server.entities.User;
import bitzero.server.extensions.data.DataCmd;
import cmd.test.response.TestResponseExtension;
import cmd.test.template.PlayCashGame;
import cmd.test.template.UserInfoApi;
import domain.gameplay.GameStatusObject;
import domain.lobby.RoomStatusObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by pc1 on 12/13/2018.
 */
public class TestRequestHandlerImpl extends TestRequestHandler {
    @Override
    protected void onHandleRequestUserInfo(User user, DataCmd cmd) {
        TestResponseExtension.sendUserInfo(new UserInfoApi() {
            @Override
            public int getUID() {
                return 0;
            }

            @Override
            public String getUserName() {
                return "a";
            }

            @Override
            public String getDisplayName() {
                return "b";
            }

            @Override
            public int getLevel() {
                return 0;
            }

            @Override
            public long getGold() {
                return 0;
            }

            @Override
            public long getExp() {
                return 0;
            }

            @Override
            public String getAvatarURL() {
                return "d";
            }

            @Override
            public String getDefaultAvatar() {
                return "e";
            }

            @Override
            public boolean getHasGame() {
                return false;
            }

            @Override
            public GameStatusObject getObject() {
                return new GameStatusObject();
            }


        }, user);
    }

    @Override
    protected void onHandleRequestPlayCashGame(User user, DataCmd cmd, PlayCashGame playCashGame) {

    }
}
