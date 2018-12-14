package cmd.lobby.request;

import bitzero.server.extensions.data.DataCmd;

import cmd.lobby.template.PlayCashGame;


/**
 * Created by Minh Nhi.
 */
public class LobbyRequestExtension {

	public static PlayCashGame getPlayCashGame(DataCmd cmd){
        return new RequestPlayCashGame(cmd).get();
    }

}

