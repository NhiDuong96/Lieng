package cmd.game.response;

import bitzero.framework.ExtensionUtility;
import bitzero.server.entities.User;

import bitzero.server.extensions.data.BaseMsg;
import cmd.game.template.GameInfoApi;
import cmd.game.template.PlayerJoinedGameApi;


/**
 * Created by Minh Nhi.
 */
public class GameResponseExtension {

	public static void sendGameInfo(GameInfoApi mGameInfoApi, User user){
        ExtensionUtility.getExtension().send(new ResponseGameInfo(mGameInfoApi), user);
    }
	public static void sendPlayerJoinedGame(PlayerJoinedGameApi mPlayerJoinedGameApi, User user){
        ExtensionUtility.getExtension().send(new ResponsePlayerJoinedGame(mPlayerJoinedGameApi), user);
    }

    public static BaseMsg getPlayerJoinedGameMsg(PlayerJoinedGameApi mPlayerJoinedGameApi){
        return new ResponsePlayerJoinedGame(mPlayerJoinedGameApi);
    }
}

