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

        PackUtils.getInstance().packUProfileModel(bf, this.mUserInfoApi.getUserProfile());
		bf.put((byte)(this.mUserInfoApi.getHasGame()?1:0));
		

        return packBuffer(bf);
    }
}

