package cmd.lobby.response;

import bitzero.server.extensions.data.BaseMsg;
import java.nio.ByteBuffer;
import cmd.entity.PackUtils;

import cmd.lobby.template.UserInfoApi;

/**
 * Created by Minh Nhi.
 */
class ResponseUserInfo extends BaseMsg {

    UserInfoApi mUserInfoApi;

    public ResponseUserInfo(UserInfoApi mUserInfoApi) {
        super((short)1100);
        this.mUserInfoApi = mUserInfoApi;
    }

    @Override
    public byte[] createData() {
        ByteBuffer bf = makeBuffer();

        bf.putInt(this.mUserInfoApi.getUID());
		putStr(bf, this.mUserInfoApi.getUserName());
		putStr(bf, this.mUserInfoApi.getDisplayName());
		bf.putInt(this.mUserInfoApi.getLevel());
		bf.putLong(this.mUserInfoApi.getGold());
		bf.putLong(this.mUserInfoApi.getExp());
		putStr(bf, this.mUserInfoApi.getAvatarURL());
		putStr(bf, this.mUserInfoApi.getDefaultAvatar());
		bf.put((byte)(this.mUserInfoApi.getHasGame()?1:0));
		

        return packBuffer(bf);
    }
}

