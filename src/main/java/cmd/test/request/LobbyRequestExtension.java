package cmd.test.request;

import bitzero.server.extensions.data.DataCmd;



/**
 * Created by Minh Nhi.
 */
public class LobbyRequestExtension {

    public DataCmd cmd;

    private LobbyRequestExtension(DataCmd cmd){
        this.cmd = cmd;
    }

    public static LobbyRequestExtension from(DataCmd cmd){
        return new LobbyRequestExtension(cmd);
    }


}

