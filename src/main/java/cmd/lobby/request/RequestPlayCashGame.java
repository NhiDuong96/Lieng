package cmd.lobby.request;

import bitzero.server.extensions.data.BaseCmd;
import bitzero.server.extensions.data.DataCmd;

import java.nio.ByteBuffer;

import cmd.lobby.template.PlayCashGame;

/**
 * Created by Minh Nhi.
 */
class RequestPlayCashGame extends BaseCmd {

    PlayCashGame mPlayCashGame;

    public RequestPlayCashGame(DataCmd data) {
        super(data);
    }

    @Override
    public void unpackData() {
        ByteBuffer bf = makeBuffer();
        this.mPlayCashGame = new PlayCashGame();
        this.mPlayCashGame.setRoomName(readString(bf));
		this.mPlayCashGame.setStructureID(readString(bf));
		this.mPlayCashGame.setBuyinChips(readLong(bf));
		this.mPlayCashGame.setPlayMode(readByte(bf));
		this.mPlayCashGame.setAutoBuyIn((readByte(bf) != 0));
		
    }

    public PlayCashGame get(){
        this.unpackData();
        return this.mPlayCashGame;
    }
}
