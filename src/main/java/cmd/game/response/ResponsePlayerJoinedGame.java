package cmd.game.response;

import bitzero.server.extensions.data.BaseMsg;
import java.nio.ByteBuffer;
import cmd.entity.PackUtils;

import cmd.game.template.PlayerJoinedGameApi;

/**
 * Created by Minh Nhi.
 */
class ResponsePlayerJoinedGame extends BaseMsg {

    PlayerJoinedGameApi mPlayerJoinedGameApi;

    public ResponsePlayerJoinedGame(PlayerJoinedGameApi mPlayerJoinedGameApi) {
        super((short)2002);
        this.mPlayerJoinedGameApi = mPlayerJoinedGameApi;
    }

    @Override
    public byte[] createData() {
        ByteBuffer bf = makeBuffer();

        PackUtils.getInstance().packPlayer(bf, this.mPlayerJoinedGameApi.getPlayer());
		bf.put((byte)(this.mPlayerJoinedGameApi.getShowLastCard()?1:0));
		

        return packBuffer(bf);
    }
}

