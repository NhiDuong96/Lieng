package cmd.game.response;

import bitzero.framework.ExtensionUtility;
import bitzero.server.entities.User;
import bitzero.server.extensions.data.BaseMsg;

import cmd.game.template.GameInfoApi;
import cmd.game.template.PlayerJoinedGameApi;
import cmd.game.template.NewHandEntityApi;
import cmd.game.template.GameActionApi;


/**
 * Created by Minh Nhi.
 */
public class GameResponseExtension {

	public static void sendGameInfo(GameInfoApi mGameInfoApi, User user){
        ExtensionUtility.getExtension().send(new ResponseGameInfo(mGameInfoApi), user);
    }

public static BaseMsg getGameInfo(GameInfoApi mGameInfoApi){
     return new ResponseGameInfo(mGameInfoApi);
    }
	public static void sendPlayerJoinedGame(PlayerJoinedGameApi mPlayerJoinedGameApi, User user){
        ExtensionUtility.getExtension().send(new ResponsePlayerJoinedGame(mPlayerJoinedGameApi), user);
    }

public static BaseMsg getPlayerJoinedGame(PlayerJoinedGameApi mPlayerJoinedGameApi){
     return new ResponsePlayerJoinedGame(mPlayerJoinedGameApi);
    }
	public static void sendNewHandEntity(NewHandEntityApi mNewHandEntityApi, User user){
        ExtensionUtility.getExtension().send(new ResponseNewHandEntity(mNewHandEntityApi), user);
    }

public static BaseMsg getNewHandEntity(NewHandEntityApi mNewHandEntityApi){
     return new ResponseNewHandEntity(mNewHandEntityApi);
    }
	public static void sendGameAction(GameActionApi mGameActionApi, User user){
        ExtensionUtility.getExtension().send(new ResponseGameAction(mGameActionApi), user);
    }

public static BaseMsg getGameAction(GameActionApi mGameActionApi){
     return new ResponseGameAction(mGameActionApi);
    }

}

