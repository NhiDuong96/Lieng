package cmd.game.response;

import bitzero.server.extensions.data.BaseMsg;
import java.nio.ByteBuffer;
import cmd.entity.PackUtils;

import cmd.game.template.NewHandEntityApi;

/**
 * Created by Minh Nhi.
 */
class ResponseNewHandEntity extends BaseMsg {

    NewHandEntityApi mNewHandEntityApi;

    public ResponseNewHandEntity(NewHandEntityApi mNewHandEntityApi) {
        super((short)2003);
        this.mNewHandEntityApi = mNewHandEntityApi;
    }

    @Override
    public byte[] createData() {
        ByteBuffer bf = makeBuffer();

        bf.put(this.mNewHandEntityApi.getCurrentPos());
		putByteArray(bf, this.mNewHandEntityApi.getCards());
		

        return packBuffer(bf);
    }
}

