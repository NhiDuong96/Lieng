package cmd.authen;

import bitzero.server.extensions.data.BaseCmd;
import bitzero.server.extensions.data.DataCmd;

import java.nio.ByteBuffer;

public class RequestLogin extends BaseCmd {
    public String data = "";

    public RequestLogin(DataCmd data) {
        super(data);
    }

    public void unpackData() {
        ByteBuffer bf = makeBuffer();
        data = readString(bf);
    }
}
