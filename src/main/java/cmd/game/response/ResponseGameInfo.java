package cmd.game.response;

import bitzero.server.extensions.data.BaseMsg;
import java.nio.ByteBuffer;
import cmd.entity.PackUtils;

import cmd.game.template.GameInfoApi;

/**
 * Created by Minh Nhi.
 */
class ResponseGameInfo extends BaseMsg {

    GameInfoApi mGameInfoApi;

    public ResponseGameInfo(GameInfoApi mGameInfoApi) {
        super((short)2001);
        this.mGameInfoApi = mGameInfoApi;
    }

    @Override
    public byte[] createData() {
        ByteBuffer bf = makeBuffer();

        PackUtils.getInstance().packCashGameImpl(bf, this.mGameInfoApi.getGame());
		

        return packBuffer(bf);
    }
}

