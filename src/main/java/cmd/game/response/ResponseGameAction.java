package cmd.game.response;

import bitzero.server.extensions.data.BaseMsg;
import java.nio.ByteBuffer;
import cmd.entity.PackUtils;

import cmd.game.template.GameActionApi;

/**
 * Created by Minh Nhi.
 */
class ResponseGameAction extends BaseMsg {

    GameActionApi mGameActionApi;

    public ResponseGameAction(GameActionApi mGameActionApi) {
        super((short)2101);
        this.mGameActionApi = mGameActionApi;
    }

    @Override
    public byte[] createData() {
        ByteBuffer bf = makeBuffer();

        bf.put(this.mGameActionApi.getActionID());
		bf.put(this.mGameActionApi.getCurPlayerPos());
		bf.putLong(this.mGameActionApi.getChips());
		

        return packBuffer(bf);
    }
}

