package cmd.game.request;

import bitzero.server.extensions.data.BaseCmd;
import bitzero.server.extensions.data.DataCmd;

import java.nio.ByteBuffer;

import cmd.game.template.GameAction;

/**
 * Created by Minh Nhi.
 */
class RequestGameAction extends BaseCmd {

    GameAction mGameAction;

    public RequestGameAction(DataCmd data) {
        super(data);
    }

    @Override
    public void unpackData() {
        ByteBuffer bf = makeBuffer();
        this.mGameAction = new GameAction();
        this.mGameAction.setActionID(readByte(bf));
		this.mGameAction.setChips(readLong(bf));
		
    }

    public GameAction get(){
        this.unpackData();
        return this.mGameAction;
    }
}
