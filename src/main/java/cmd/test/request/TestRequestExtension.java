package cmd.test.request;

import bitzero.server.extensions.data.DataCmd;

import cmd.test.template.PlayCashGame;


/**
 * Created by Minh Nhi.
 */
public class TestRequestExtension {

	public static PlayCashGame getPlayCashGame(DataCmd cmd){
        return new RequestPlayCashGame(cmd).get();
    }

}

