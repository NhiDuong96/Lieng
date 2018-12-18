package domain.lobby;

import bitzero.framework.ExtensionUtility;
import bitzero.framework.common.MsgService;
import bitzero.server.BitZeroServer;
import bitzero.server.entities.Room;
import bitzero.server.entities.User;
import bitzero.server.entities.Zone;
import bitzero.server.exceptions.BZTooManyRoomsException;
import cmd.game.response.GameResponseExtension;
import cmd.game.template.GameInfoApi;
import cmd.game.template.PlayerJoinedGameApi;
import config.GameConfig;
import constant.CmdDefine;
import constant.ErrorDefine;
import constant.ServerConstant;
import domain.gameplay.*;
import domain.gameplay.service.GameServiceImpl;
import domain.lobby.format.CashGameFormat;
import domain.lobby.option.BuyInOption;
import domain.lobby.option.PlayMode;
import handler.request.GameRequestHandlerImpl;
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
    public byte playCashGame(User user, BuyInOption opt, String structureId, String rName, PlayMode playMode) throws BZTooManyRoomsException {
        CashGameFormat reqGameStructure = GameConfig.MAP_CASH_GAME_FORMAT.get(structureId);

        CashGameFormat newRoomFormat = reqGameStructure;

        if(newRoomFormat == null) {
            logger.info("finding room");
            Room room = LobbyUtil.autoSelectRoom(user, opt, playMode);
            CashGameImpl game = (CashGameImpl) getGameByRoom(room);

            if (game != null) {
                logger.info("found room!");
                CashGameFormat cgf = GameConfig.MAP_CASH_GAME_FORMAT.get(game.getGameStructure().getId());
                byte errorCode = joinRoom(user, room, opt);
                System.out.println(errorCode);
                if (errorCode == ErrorDefine.SUCCESS) {
                    return errorCode;
                }
                // continue create game
                newRoomFormat = cgf;
            }else{
                //set BuyinOption here
                newRoomFormat = LobbyUtil.selectOptimalCashGame(user, opt, playMode);
            }
        }

        logger.info("no room, create one!");
        //1 -> create player
        Player newPlayer = GameFactory.createPlayer(user, opt);

        if(newPlayer == null){
            //player not enough gold or someone
            return ErrorDefine.NOT_ENOUGH_GOLD;
        }
        //join user to zone
        ExtensionUtility.instance().joinChannel(user, getGameZone(GameType.CASH).getId());

        //2-> create room
        Room newRoom = createRoom(getGameZone(GameType.CASH), user, "roomName", newRoomFormat.getMaxPlayers(), newRoomFormat.getMaxSpectators(), "");

        //this call very important!
        System.out.println("id game format optimal: " +newRoomFormat.getId());
        newRoom.setGroupId(newRoomFormat.getId());
        //join room to zone
        user.getZone().addRoom(newRoom);

        //3-> create game
        CashGameImpl newGame = GameFactory.createCashGame(newRoom, newRoomFormat, playMode);
        //4-> join game
        GameServiceImpl.getInstance().joinGame(newGame, newPlayer);
        GameResponseExtension.sendGameInfo(new GameInfoApi() {
            @Override
            public CashGameImpl getGame() {
                return newGame;
            }
        }, user);

        // attach game to room
        attachGameToRoom(newRoom, newGame);
        return ErrorDefine.SUCCESS;
    }

    public byte joinRoom(User user, Room room, BuyInOption opt){
        Game game = getGameByRoom(room);
        if (game == null) {
            return ErrorDefine.ROOM_NOT_EXIST;
        }

        if (user == null) {
            // bot join

            return ErrorDefine.SUCCESS;
        }

        //create player
        Player newPlayer = GameFactory.createPlayer(user, opt);
        if (newPlayer == null) {
            return ErrorDefine.NOT_ENOUGH_MIN_BUYIN;
        }

        Player joinedPlayer = GameServiceImpl.getInstance().joinGame((CashGameImpl) game, newPlayer);
        if (joinedPlayer == null) {
            return ErrorDefine.ROOM_IS_FULL;
        }

        // reconnect to old player
        if (joinedPlayer != newPlayer) {

        }

        ExtensionUtility.instance().joinChannel(user, getGameZone(GameType.CASH).getId());

        GameRequestHandlerImpl.broadcastGame(game, GameResponseExtension.getPlayerJoinedGame(new PlayerJoinedGameApi() {
            @Override
            public Player getPlayer() {
                return joinedPlayer;
            }

            @Override
            public boolean getShowLastCard() {
                return false;
            }
        }));

        room.removeUser(user);
        ExtensionUtility.instance().joinRoom(user, room);

        GameResponseExtension.sendGameInfo(new GameInfoApi() {
            @Override
            public CashGameImpl getGame() {
                return (CashGameImpl) game;
            }
        }, user);

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
