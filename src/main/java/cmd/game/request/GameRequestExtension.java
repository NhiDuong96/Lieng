package cmd.game.request;

import bitzero.server.extensions.data.DataCmd;

import cmd.game.template.GameAction;


/**
 * Created by Minh Nhi.
 */
public class GameRequestExtension {

	public static GameAction getGameAction(DataCmd cmd){
        return new RequestGameAction(cmd).get();
    }

}

