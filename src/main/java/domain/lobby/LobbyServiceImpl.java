package domain.lobby;

import bitzero.framework.ExtensionUtility;
import bitzero.framework.common.MsgService;
import bitzero.server.BitZeroServer;
import bitzero.server.entities.Room;
import bitzero.server.entities.User;
import bitzero.server.entities.Zone;
import bitzero.server.exceptions.BZTooManyRoomsException;
import config.GameConfig;
import constant.CmdDefine;
import constant.ErrorDefine;
import constant.ServerConstant;
import domain.gameplay.Game;
import domain.gameplay.GameFactory;
import domain.gameplay.GameType;
import domain.gameplay.Player;
import domain.gameplay.service.GameServiceImpl;
import domain.lobby.format.CashGameFormat;
import domain.lobby.option.BuyInOption;
import org.slf4j.LoggerFactory;

/**
 * Created by GSN on 11/4/2015.
 */
public class LobbyServiceImpl implements LobbyService {
    static LobbyServiceImpl instance = new LobbyServiceImpl();
    org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    LobbyServiceImpl() {
    }

    public static LobbyServiceImpl getInstance() {
        return instance;
    }

    public Zone getGameZone(GameType gameType) {
        return BitZeroServer.getInstance().getZoneManager().getZoneById(gameType.ordinal());
    }

    public Room createRoom(Zone zone, User owner, String rName, int maxUser, int maxSpectators, String password) {
        return ExtensionUtility.instance().createRoom(zone, owner, rName, maxUser, maxSpectators, password);
    }

    public Game getGameByRoom(Room room) {
        if (room == null) {
            return null;
        }
        return (Game) room.getProperty(Game.class);
    }

    public void attachGameToRoom(Room room, Game game) {
        room.setProperty(Game.class, game);
    }

    @Override
    public void initGameChannels() {
        ExtensionUtility.instance().createChannel(GameType.CASH.ordinal(), "CASH GAME ZONE", ServerConstant.MAX_USERS, ServerConstant.MAX_ROOMS);
    }


    @Override
    public byte playCashGame(User user) throws BZTooManyRoomsException {
        Room room = LobbyUtil.autoSelectRoom(user);
        Game game = getGameByRoom(room);

        if(game != null){
            logger.info("find room!");
            byte errorCode = joinRoom(user, room);
            if(errorCode == ErrorDefine.SUCCESS){
                return errorCode;
            }
        }
        logger.info("no room, create one!");
        //1 -> create player
        Player newPlayer = GameFactory.createPlayer(user);

        if(newPlayer == null){
            //player not enough gold or someone
            return ErrorDefine.NOT_ENOUGH_GOLD;
        }
        //join user to zone
        ExtensionUtility.instance().joinChannel(user, getGameZone(GameType.CASH).getId());

        //2-> create room
        Room newRoom = createRoom(getGameZone(GameType.CASH), user, "roomName", 9, 100, "");

        //this call very important!
        newRoom.setGroupId("playNow");
        //join room to zone
        user.getZone().addRoom(room);

        //3-> create game
        Game newGame = GameFactory.createGame(room);
        GameServiceImpl.getInstance().joinGame(newGame, newPlayer);

        // attach game to room
        attachGameToRoom(room, newGame);
        return ErrorDefine.SUCCESS;
    }

    public byte joinRoom(User user, Room room){
        Game game = getGameByRoom(room);
        if (game == null) {
            return ErrorDefine.ROOM_NOT_EXIST;
        }

        if (user == null) {
            // bot join test

            return ErrorDefine.SUCCESS;
        }

        //create player
        Player newPlayer = GameFactory.createPlayer(user);
        if (newPlayer == null) {
            return ErrorDefine.NOT_ENOUGH_MIN_BUYIN;
        }

        Player joinedPlayer = GameServiceImpl.getInstance().joinGame(game, newPlayer);
        if (joinedPlayer == null) {
            return ErrorDefine.ROOM_IS_FULL;
        }

        // reconnect to old player
        if (joinedPlayer != newPlayer) {

        }

        ExtensionUtility.instance().joinChannel(user, getGameZone(GameType.CASH).getId());

        //response to all player in game
        //response game info for player

        room.removeUser(user);
        ExtensionUtility.instance().joinRoom(user, room);
        return ErrorDefine.SUCCESS;
    }

    @Override
    public void setReconnect(Player player, GameType gameType) {

    }

    @Override
    public void clearReconnect(int userId) {

    }

    @Override
    public boolean doReconnect(User user) {
        return false;
    }
}
